FROM centos:6.10
# Java 8
# LS_COLORS
RUN yum update -y \
 && yum install -y java-1.8.0-openjdk \
 && echo "export LS_COLORS=\$LS_COLORS:'di=0;35:'" >> ~/.bash_profile \
 && source ~/.bash_profile

# Tomcat
WORKDIR /opt/apache-tomcat
RUN curl -O https://archive.apache.org/dist/tomcat/tomcat-9/v9.0.19/bin/apache-tomcat-9.0.19.tar.gz \
 && tar -xvzf apache-tomcat-9.0.19.tar.gz \
 && useradd -s /sbin/nologin tomcat \
 && chown -R tomcat:tomcat /opt/apache-tomcat \
 && echo "export JAVA_HOME=$(dirname $(readlink $(readlink $(which java))) | sed -e 's#/bin.*##')" >> ~/.bash_profile \
 && source ~/.bash_profile \
 && rm -rf apache-tomcat-9.0.19.tar.gz
ENV CATALINA_HOME /opt/apache-tomcat/apache-tomcat-9.0.19

# Deploy
WORKDIR /opt/apache-tomcat/apache-tomcat-9.0.19/webapps
COPY target/petclinic.war .
CMD $CATALINA_HOME/bin/catalina.sh run
