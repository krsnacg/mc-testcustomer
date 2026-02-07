pipeline {
    agent any

    tools {
        maven 'Maven 3.8.7' // Specify the Maven version configured in Jenkins
        
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
                sh 'docker build  -t mc-testcustomer:latest .' // Example Docker build command
            }
        }
    }
}