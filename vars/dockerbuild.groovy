def call(String hublogin, String hubuser, String repo, String repotag) {
    withCredentials([usernamePassword(
            credentialsId: "$hublogin",
            usernameVariable: "Username",
            passwordVariable: "Password"
    )]) {
        sh "docker login -u '$Username' -p '$Password'"
    }
    sh "docker image build -t ${hubuser}/${repo}:${repotag}-v${env.BUILD_NUMBER} .  --no-cache"
    sh "docker image push ${hubuser}/${repo}:${repotag}-v${env.BUILD_NUMBER}"
    sh "docker image rm ${hubuser}/${repo}:${repotag}-v${env.BUILD_NUMBER}"   
}
