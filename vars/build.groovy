#!/usr/bin/env groovy

def call(String name = 'build') {
  sh 'mvn clean install'
}
