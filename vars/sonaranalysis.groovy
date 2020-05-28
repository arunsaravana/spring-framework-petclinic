def call(String Sonarserver, String scanner, String scannerproperties) {
   //def scannerHome = tool '${Sonarserver}';
   withSonarQubeEnv('${scanner}') {
     sh "/opt/sonar-scanner/bin/sonar-scanner -Dproject.settings=${scannerproperties}"    
   }
}
