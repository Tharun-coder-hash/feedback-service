pipeline {
    agent any

    environment {
        // Change this to your Docker Hub username
        DOCKER_IMAGE = "tharun-coder-hash/feedback-service" 
        DOCKER_CREDENTIALS_ID = "docker-hub-credentials"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                // Use 'bat' instead of 'sh' for Windows Jenkins
                bat 'mvn clean test'
                bat 'mvn package -DskipTests'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                bat "docker build -t ${DOCKER_IMAGE}:latest ."
                bat "docker tag ${DOCKER_IMAGE}:latest ${DOCKER_IMAGE}:${env.BUILD_ID}"
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                // Since your Docker Desktop K8s is already local, 
                // you might not even need withKubeConfig if it's already logged in.
                bat "kubectl apply -f k8s/deployment.yaml"
            }
        }
    }
}
