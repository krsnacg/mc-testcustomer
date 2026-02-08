pipeline {
    agent any

    tools {
        maven 'Maven 3.8.7' // Specify the Maven version configured in Jenkins
        
    }

    environment {
        IMAGE_NAME = 'mc-testcustomer' // Define your image name here
        IMAGE_TAG = '${BUILD_NUMBER}' // Define your image tag here
        REGISTRY = 'docker.io'
    }

    stages {
        stage('Checkout') {
            steps {
                checkout scm // This will check out the code from the repository configured in Jenkins
            }
        }
        stage('Build') {
            steps {
                sh 'mvn clean install' // Example build command, replace with your actual build steps
            }
        }
        stage('Sonar') {
            steps {
                withSonarQubeEnv(installationName: 'My SonarQube Server') { // Replace with your SonarQube server name configured in Jenkins
                    sh "mvn org.sonarsource.scanner.maven:sonar-maven-plugin:sonar -Dsonar.projectKey=mc-testcustomer -Dsonar.projectName='mc-testcustomer'"  // Example SonarQube analysis command, replace with your actual command
                }
            }
        }
        stage('Quality Gate') {
            steps {
                timeout(time: 1, unit: 'HOURS') { // Adjust timeout as needed
                    waitForQualityGate abortPipeline: true // This will abort the pipeline if the quality gate fails
                }
            }
        }
        stage('Image Build') {
            steps {
                echo 'Building Docker image...'
                sh "docker build -t ${IMAGE_NAME}:${IMAGE_TAG} ." // Example Docker build command
                sh "docker tag ${IMAGE_NAME}:${IMAGE_TAG} ${REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}" // Tag the image for your Docker registry
            }
        }
        stage('Push Image') {
            steps {
                withCredentials([
                    usernamePassword(
                        credentialsId: 'dockerhub-creds', 
                        usernameVariable: 'DOCKER_USR', 
                        passwordVariable: 'DOCKER_PWD'
                    )
                ]) {
                    sh """
                      echo "$DOCKER_PWD" | docker login -u "$DOCKER_USR" --password-stdin
                    """ // Log in to Docker registry
                    sh "docker push ${REGISTRY}/${IMAGE_NAME}:${IMAGE_TAG}" // Push the image to the registry
                }
            }
        }

        post {
            always {
                sh "docker logout ${REGISTRY}"
            }
        }
    }
}