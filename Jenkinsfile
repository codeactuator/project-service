pipeline{
    agent any

    stages {
        stage('Build'){
            steps {
                echo "Initiating mvn clean install!"
                mvn compile
                echo "Build Stage Completed!"
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