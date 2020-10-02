pipeline{
    agent any

    tools {
        maven 'Maven'
        jdk 'Java8'
    }

    stages {
        stage('Build'){
            steps {
                bat 'mvn clean install -U -Dmaven.test.failure.ignore=true'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }

        }

        stage('Test'){
            steps {
                echo "Initiating mvn test!"
                mvn compile
                echo "Test Stage Completed!"
            }
        }

        stage('Deploy'){
            steps {
                echo "Done!"
            }
        }
    }

}