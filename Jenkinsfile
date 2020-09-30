pipeline{
    agent any

    stages {
        stage('Build'){
            steps {
                echo "Initiating mvn clean install!"
                sh 'mvn install -Dmaven.test.failure.ignore=true'
                echo "Build Stage Completed!"
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