pipeline {
  agent any
  tools {
    maven 'Maven-3.5.4'
    jdk 'Java-1.8.0_192'
  }
  stages {
      stage('Compile') {
        steps {
          sh 'mvn clean compile install -DskipTests'
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
          nexusArtifactUploader {artifacts: [
              [artifactId: 'ejb3-remote-client-0-war', classifier: '', file: 'servlets/ejb3-server-client-0-war/target/ejb3-server-client-0-war-0.0.1-SNAPSHOT.war', type: 'war'],
              [artifactId: 'ejb3-remote-client-1-war', classifier: '', file: 'servlets/ejb3-server-client-1-war/target/ejb3-server-client-1-war-0.0.1-SNAPSHOT.war', type: 'war'],
              [artifactId: 'ejb3-remote-client-2-war', classifier: '', file: 'servlets/ejb3-server-client-2-war/target/ejb3-server-client-2-war-0.0.1-SNAPSHOT.war', type: 'war']
              [artifactId: 'ejb3-server-client-war', classifier: '', file: 'servlets/ejb3-server-client-war/target/ejb3-server-client-war-0.0.1-SNAPSHOT.war', type: 'war']
              [artifactId: 'ejb3-server-war', classifier: '', file: 'servlets/ejb3-server-war/target/ejb3-server-war-0.0.1-SNAPSHOT.war', type: 'war'],
              [artifactId: 'ejb3-standalone-client', classifier: '', file: 'servlets/ejb3-standalone-client/target/ejb3-standalone-client-0.0.1-SNAPSHOT.jar', type: 'jar']
            ],
            credentialsId: 'd2a6dca6-e8a0-4bb4-a7a2-34c1bc572c2b', groupId: 'root.project.servlets', nexusUrl: '34.73.12.137:8081', nexusVersion: 'nexus3', protocol: 'http',
            repository: 'maven-snapshots', version: '0.0.1-SNAPSHOT'},
          nexusArtifactUploader {artifacts: [
              [artifactId: 'ejb3-server-api', classifier: '', file: 'ejbs/ejb3-server-api/target/ejb3-server-api-0.0.1-SNAPSHOT.jar', type: 'jar'],
              [artifactId: 'ejb3-server-impl', classifier: '', file: 'ejbs/ejb3-server-impl/target/ejb3-server-impl-0.0.1-SNAPSHOT.jar', type: 'jar']
            ],
            credentialsId: 'd2a6dca6-e8a0-4bb4-a7a2-34c1bc572c2b', groupId: 'root.project.ejbs', nexusUrl: '34.73.12.137:8081', nexusVersion: 'nexus3', protocol: 'http',
            repository: 'maven-snapshots', version: '0.0.1-SNAPSHOT'},
          nexusArtifactUploader {artifacts: [
              [artifactId: 'ear', classifier: '', file: 'ear/target/ear-1.0.ear', type: 'ear'],
              [artifactId: 'jndi-lookup-util', classifier: '', file: 'jndi-lookup-util/target/jndi-lookup-util-0.0.1-SNAPSHOT.jar', type: 'jar']
            ],
            credentialsId: 'd2a6dca6-e8a0-4bb4-a7a2-34c1bc572c2b', groupId: 'root.project', nexusUrl: '34.73.12.137:8081', nexusVersion: 'nexus3', protocol: 'http',
            repository: 'maven-snapshots', version: '0.0.1-SNAPSHOT'}
        }
      }
    }
}
