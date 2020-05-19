def call(String region, String cluster) {
 withCredentials([[
 $class: 'AmazonWebServicesCredentialsBinding', 
 accessKeyVariable: 'AWS_ACCESS_KEY_ID', 
 credentialsId: 'awstest', 
 secretKeyVariable: 'AWS_SECRET_ACCESS_KEY']]) 
 {
              echo "Login Successfull"
              sh "aws eks --region '${region}'  update-kubeconfig --name '${cluster}'"
              sh 'sed -i s/"BUILD_NUMBER"/"$BUILD_NUMBER"/g app-deployment.yaml'
              sh 'kubectl apply -f app-deployment.yaml'
              sh 'kubectl apply -f app-service.yaml'
              }
              }
