def call(String hubuser, String repo,String repotag) {
    withCredentials([usernamePassword(
            credentialsId: "Dockerhub",
            usernameVariable: "Username",
            passwordVariable: "Password"
    )]) {
        sh "docker login -u '$Username' -p '$Password'"
    }
    sh "docker image build -t ${hubUser}/${repo}:${repotag}-v${env.BUILD_NUMBER} ."
    sh "docker image push ${hubUser}/${repo}:${repotag}-v${env.BUILD_NUMBER}"
    sh "docker image rm ${hubUser}/${repo}:${repotag}-v${env.BUILD_NUMBER}"   
}
