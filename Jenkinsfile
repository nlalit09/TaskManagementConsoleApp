pipeline {
    agent any
    
    tools {
        maven 'Maven-3.9.5'  // Adjust this to match your Maven installation name
    }
    environment {
        IMAGE_NAME = "task-management-app"
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
        stage('Build Jar') {
            steps {
                sh 'mvn clean package -DskipTests'
            }
        }


        stage('Build Docker Image') {
            steps {
                sh "docker build -t ${IMAGE_NAME} ."
            }
        }

        stage('Push to DockerHub') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'dockerhub', usernameVariable: 'lalithanl', passwordVariable: 'Mymac@099')]) {
                    sh '''
                        echo "$DOCKER_PASS" | docker login -u "$DOCKER_USER" --password-stdin
                        docker push $IMAGE_NAME
                    '''
                }
            }
        }
    }
}
