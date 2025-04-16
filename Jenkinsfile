pipeline {
    agent any

    tools {
        jdk 'JDK-17'
        maven 'Maven-3.9.5'
    }

    stages {
        stage('Checkout Code') {
            steps {
                git 'https://github.com/nlalit09/TaskManagementConsoleApp.git'
            }
        }

        stage('Build') {
            steps {
                sh 'mvn clean install'
            }
        }

        stage('Test') {
            steps {
                sh 'mvn test'
            }
        }

        stage('Package') {
            steps {
                sh 'mvn package'
            }
        }

        stage('Archive Artifacts') {
            steps {
                archiveArtifacts artifacts: 'target/*.jar', fingerprint: true
            }
        }
    }
}
