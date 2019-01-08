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

      stage('Publish to Nexus') {
        steps {
          nexusArtifactUploader artifacts: [[artifactId: 'ejb3-server-war', classifier: '', file: 'servlets/ejb3-server-client-war/target/ejb3-server-client-war-0.0.1-SNAPSHOT.war', type: 'war']],
            credentialsId: 'd2a6dca6-e8a0-4bb4-a7a2-34c1bc572c2b', groupId: 'root.project.servlets', nexusUrl: '34.73.12.137:8081', nexusVersion: 'nexus3', protocol: 'http',
            repository: 'maven-snapshots', version: '0.0.1-SNAPSHOT'
        }
      }
    }
}
