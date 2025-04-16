pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9.5'  // Adjust this to match your Maven installation name
    }
    
    stages {
        stage('Checkout SCM') {
            steps {
                script {
                    // Ensures that the 'main' branch is checked out
                    checkout([$class: 'GitSCM', 
                              branches: [[name: '*/main']]])
                }
            }
        }
        
        stage('Build') {
            steps {
                script {
                    // Your build steps, for example:
                    sh 'mvn clean install'
                }
            }
        }

        stage('Test') {
            steps {
                script {
                    // Your test steps, for example:
                    sh 'mvn test'
                }
            }
        }

        stage('Package') {
            steps {
                script {
                    // Your packaging steps, for example:
                    sh 'mvn package'
                }
            }
        }

        stage('Archive Artifacts') {
            steps {
                script {
                    // Archive build artifacts, if any
                    archiveArtifacts '**/target/*.jar'
                }
            }
        }
    }
}
