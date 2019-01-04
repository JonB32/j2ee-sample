pipeline {
  agent any
  tools {
    maven 'Maven-3.5.4'
    jdk 'Java-1.8.0_192'
  }
  stages {
      stage('Compile') {
        steps {
          sh 'mvn clean compile'
        }
      }

      stage('Test') {
        steps {
          sh 'mvn test'
        }
        post {
          success {
            junit '**/target/surefire-reports/**/*.xml'
          }
        }
      }

      stage('Install') {
        steps {
          sh 'mvn install'
        }
      }
    }
}
