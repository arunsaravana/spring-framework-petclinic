@Library('akpipeline') _

pipeline {
        
        parameters {
        string(defaultValue: "master", description: '', name: 'repobranch')
        string(defaultValue: "https://github.com/arunsaravana/spring-framework-petclinic.git", description: '', name: 'repourl')        
        string(defaultValue: "**/target/surefire-reports/*.xml", description: '', name: 'testpath') 
        string(defaultValue: "arunsara", description: '', name: 'hubuser')
        string(defaultValue: "spring-application", description: '', name: 'hubrepo')
        string(defaultValue: "petclinic", description: '', name: 'hubtag')
        string(defaultValue: "us-west-2", description: '', name: 'region')
        string(defaultValue: "springbootapp", description: '', name: 'clutername')
        string(defaultValue: "Dockerhub", description: '', name: 'hubid')
        string(defaultValue: "awstest", description: '', name: 'awsid')
    }


           agent any
stages {
       stage('checkout') {
         steps {
           mycodecheckout(branch: "${params.repobranch}", scmUrl: "${params.repourl}")
		 }
      }

   stage('build') {
         steps {
                mybuild()
       
                  }
        }
//  stage('Junit Test') {
//         steps {
//                junittest("${params.testpath}")
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
            credentialsId: "${params.hubid}",
            usernameVariable: "Username",
            passwordVariable: "Password"
        )]) {
        dockerbuild("${params.hubuser}", "${params.hubrepo}", "${params.hubtag}")
        }
      }
    }     
   stage ('Kube Deploy') {
      steps {
        withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: "${params.awsid}", secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
        kubeupdate("${params.region}", "${params.clutername}")
        } 
      }
    }  
}
}
