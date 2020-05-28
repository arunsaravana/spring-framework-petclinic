def call(String Sonarserver, String scanner,String scannerproperties) {
   def scannerHome = tool '${Sonarserver}';
   withSonarQubeEnv('${scanner}') {
     sh "${tool('${Sonarserver}')}/bin/sonar-scanner -Dproject.settings=${scannerproperties}"    

}
