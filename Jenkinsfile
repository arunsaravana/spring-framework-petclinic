@Library('akpipeline') _

pipeline {
    
   agent any
stages {
       stage('checkout') {
         steps {
 // checkout([$class: 'GitSCM', branches: [[name: '*/master']], doGenerateSubmoduleConfigurations: false, extensions: [], submoduleCfg: [], userRemoteConfigs: [[url: 'https://github.com/arunsaravana/spring-framework-petclinic.git']]])
          mycodecheckout(branch: 'master', scmUrl: 'https://github.com/arunsaravana/spring-framework-petclinic.git')
		 }
      }

   stage('build') {
         steps {
                mybuild()
       
                   }
        }
//   stage('Junit Test') {
//         steps {
//                junittest('**/target/surefire-reports/*.xml')
//                   }
//        }     
//   stage('Sonar Analysis') {
//         steps {
//                sonaranalysis()
//                   }
//        } 
   stage ('Docker build')
    {
      steps {
        
        dockbuild('arunsara', 'spring-application', 'petclinic')
      }
    }     
    }
}
