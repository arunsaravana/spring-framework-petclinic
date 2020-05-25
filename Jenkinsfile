@Library('akpipeline') _

pipeline {
        
        parameters {
        string(defaultValue: "master", description: '', name: 'repobranch')
        string(defaultValue: "https://github.com/arunsaravana/spring-framework-petclinic.git", description: '', name: 'repourl')        
    }


           agent any
stages {
       stage('checkout') {
         steps {
           mycodecheckout(branch: '${params.repobranch}', scmUrl: '${params.repourl}')
		 }
      }

   stage('build') {
         steps {
                mybuild()
       
                  }
        }
//  stage('Junit Test') {
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
