@Library('akpipeline') _

pipeline {
    
   agent any
stages {
       stage('checkout') {
         steps {
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
   stage ('Docker build') {
      steps {
        dockerbuild('arunsara', 'spring-application', 'petclinic')
      }
    }     
   stage ('Kube Deploy') {
      steps {
        kubeupdate('awstest','us-west-2', 'springbootapp')
      }
    }  
}
}
