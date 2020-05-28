def call(String Sonarserver, String scanner,String scannerproperties) {
   def scannerHome = tool '${Sonarserver}';
   withSonarQubeEnv('${scanner}') {
     sh "${scannerHome}/bin/sonar-scanner -Dproject.settings=${scannerproperties}"    
   }
}
