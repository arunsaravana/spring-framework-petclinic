#!/usr/bin/env groovy

def info(build) {
  sh 'mvn clean install'
}

//def call(String name = 'human') {
//  echo "Hello, ${name}."
//}
//def info(message) {
//    echo "INFO: ${message}"
//}
