pipeline{
    agent any

    stages {
        stage('Build'){
            echo "Initiating mvn clean install!"
            mvn compile
            echo "Build Stage Completed!"
        }

        stage('Test'){
            echo "Initiating mvn test!"
            mvn compile
            echo "Test Stage Completed!"
        }

        stage('Deploy'){
            echo "Done!"
        }
    }

}