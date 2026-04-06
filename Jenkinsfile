pipeline {
    agent any

    environment {
        DOCKER_IMAGE = "my-docker-registry/feedback-service"
        DOCKER_CREDENTIALS_ID = "docker-hub-credentials"
        KUBECONFIG_CREDENTIALS_ID = "k8s-config"
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm
            }
        }

        stage('Build & Test') {
            steps {
                sh 'mvn clean test'
                sh 'mvn package -DskipTests'
            }
            post {
                always {
                    junit 'target/surefire-reports/*.xml'
                }
            }
        }

        stage('Build Docker Image') {
            steps {
                script {
                    dockerImage = docker.build("${DOCKER_IMAGE}:${env.BUILD_NUMBER}")
                }
            }
        }

        stage('Push Docker Image') {
            steps {
                script {
                    docker.withRegistry('https://index.docker.io/v1/', DOCKER_CREDENTIALS_ID) {
                        dockerImage.push()
                        dockerImage.push('latest')
                    }
                }
            }
        }

        stage('Deploy to Kubernetes') {
            steps {
                withKubeConfig([credentialsId: KUBECONFIG_CREDENTIALS_ID]) {
                    sh """
                    # Update the image tag in deployment.yaml dynamically
                    sed -i "s|my-docker-registry/feedback-service:latest|${DOCKER_IMAGE}:${env.BUILD_NUMBER}|g" k8s/deployment.yaml
                    kubectl apply -f k8s/deployment.yaml
                    """
                }
            }
        }
    }
}
