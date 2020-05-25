@Library('akpipeline') _

def loadValuesYaml(){
def properties = readYaml (file: 'template.yaml')
 return properties;
 }

pipeline {
        
    agent any
stages {
       stage('checkout') {
         steps {
           mycodecheckout(branch: properties.scm.branch , scmUrl: properties.scm.repo)
                 //echo "repo: ${github_repo}" 
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
            credentialsId: properties.CredId.dockercrid,
            usernameVariable: "Username",
            passwordVariable: "Password"
        )]) {
        dockerbuild(properties.dockerhub.hubuser, properties.dockerhub.hubrepo, properties.dockerhub.hubtag)
        }
      }
    }     
   stage ('Kube Deploy') {
      steps {
        withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: properties.CredId.awsid , secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
        kubeupdate(properties.eks.eksregion, properties.eks.ekscluster)
        } 
      }
    }  
}
}
