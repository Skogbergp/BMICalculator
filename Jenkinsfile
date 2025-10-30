pipeline {
    agent any
    environment
    {
                PATH = "C:\\Program Files\\Docker\\Docker\\resources\\bin;${env.PATH}"

                // Define Docker Hub credentials ID
                DOCKERHUB_CREDENTIALS_ID = 'DockerHub'
                // Define Docker Hub repository name
                DOCKERHUB_REPO = 'hatrik/bmicalculator'
                // Define Docker image tag
                DOCKER_IMAGE_TAG = 'latest'
    }
    tools{
        maven "maven 3"
    }
    stages
    {
        stage('Checkout')
        {
            steps
            {
                git branch:"main", url:'https://github.com/Skogbergp/BMICalculator'
            }
        }
        stage('Build')
        {
            steps
            {
                bat 'mvn clean'
                bat 'mvn install'
            }
        }
        stage('Test')
        {
            steps
            {
                bat 'mvn test'
            }
        }
        stage('Code Coverage')
        {
            steps
            {
                bat 'mvn jacoco:report'
            }
        }
        stage('Publish Test Results')
        {
            steps
            {
                junit '**/target/surefire-reports/*.xml'
            }
        }
        stage('Publish Coverage Report')
        {
            steps
            {
                jacoco()
            }
        }
        stage('Build Docker Image')
        {
            steps
            {
                    bat 'docker build -t %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG% .'
            }
        }

        stage('Push Docker Image to Docker Hub')
        {
            steps
            {
                withCredentials([usernamePassword(credentialsId: "${DOCKERHUB_CREDENTIALS_ID}", usernameVariable: 'DOCKER_USER', passwordVariable: 'DOCKER_PASS')])
                 {
                    bat '''
                        docker login -u %DOCKER_USER% -p %DOCKER_PASS%
                        docker push %DOCKERHUB_REPO%:%DOCKER_IMAGE_TAG%
                    '''
                }
            }
        }
    }
}