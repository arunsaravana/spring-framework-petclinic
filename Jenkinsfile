@Library('akpipeline') _
//import groovy.json.JsonSlurperClassic
import groovy.json.*

pipeline {
   def filename = 'parameters.json'
   def jsonSlurper = new JsonSlurper()
   data = jsonSlurper.parse(new File(filename))
   def branch = "data.jenkinfile.parameters.branch"
        
           agent any
stages {
       stage('checkout') {
         steps {
                echo "$branch"
           mycodecheckout(branch: 'master', scmUrl: 'https://github.com/arunsaravana/spring-framework-petclinic.git')
		 }
      }

   stage('build') {
         steps {
                mybuild()
       
                  }
        }
  stage('Junit Test') {
         steps {
                junittest('**/target/surefire-reports/*.xml')
                   }
        }     
   stage('Sonar Analysis') {
         steps {
                sonaranalysis()
                   }
        } 
   stage ('Docker build') {
      steps {
        withCredentials([usernamePassword(
            credentialsId: "Dockerhub",
            usernameVariable: "Username",
            passwordVariable: "Password"
        )]) {
        dockerbuild('arunsara', 'spring-application', 'petclinic')
        }
      }
    }     
   stage ('Kube Deploy') {
      steps {
        withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: 'awstest', secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
        kubeupdate('us-west-2', 'springbootapp')
        } 
      }
    }  
}
}
