@Library('akpipeline') _

def loadValuesYaml(){
def propertiesfile = readYaml (file: 'template.yml')
 return propertiesfile;

 }

pipeline {
        
    agent any
stages {
    
  stage ('Prepare') {
     steps {
        script {
                propertiesfile = loadValuesYaml()
                println proertiesfile.getClass()
     }
    }
  }
    
       stage('checkout') {
         steps {
           mycodecheckout(branch: propertiesfile.scm.branch , scmUrl: propertiesfile.scm.repo)
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
            credentialsId: propertiesfile.CredId.dockercrid,
            usernameVariable: "Username",
            passwordVariable: "Password"
        )]) {
        dockerbuild(propertiesfile.dockerhub.hubuser, propertiesfile.dockerhub.hubrepo, propertiesfile.dockerhub.hubtag)
        }
      }
    }     
   stage ('Kube Deploy') {
      steps {
        withCredentials([[$class: 'AmazonWebServicesCredentialsBinding', accessKeyVariable: 'AWS_ACCESS_KEY_ID', credentialsId: propertiesfile.CredId.awsid , secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) {
        kubeupdate(propertiesfile.eks.eksregion, propertiesfile.eks.ekscluster)
        } 
      }
    }  
}
}
