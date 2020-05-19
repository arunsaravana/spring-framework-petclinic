def call(String hubuser, String repo) {
    withCredentials([usernamePassword(
            credentialsId: "Dockerhub",
            usernameVariable: "Username",
            passwordVariable: "Password"
    )]) {
        sh "docker login -u '$Username' -p '$Password'"
    }
    sh "docker image build -t ${hubUser}/${repo}:petclinic-v${env.BUILD_NUMBER} ."
    sh "docker image push ${hubUser}/${repo}:petclinic-v${env.BUILD_NUMBER}"
    sh "docker image rm ${hubUser}/${repo}:petclinic-v${env.BUILD_NUMBER}"   
}
