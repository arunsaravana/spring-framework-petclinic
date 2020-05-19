@Library('pipeline-library-demo') _

pipeline {
  //  environment {
  //  registry = "arunsara/spring-application"
  //  registryCredential = 'Dockerhub'
 // }     
   agent any
stages {
      stage('build') {
         steps {
 // checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/arunsaravana/spring-framework-petclinic.git']]])
          //checkout(branch: 'master', scmUrl: 'https://github.com/arunsaravana/spring-framework-petclinic.git')
            build()
		 }
      }
}
}
