import io.netty.handler.codec.MessageAggregationException

pipeline{

    agent any

    tools {
        maven 'Maven'
        jdk 'Java8'
    }

    environment {
        WELCOME_GREETINGS = 'Hello Folks!'
        SUCCESS_MESSAGE = 'Successfully Done!'
        POST_SUCCESS_MESSAGE = 'Preparing to run the application...'
    }


    stages {
        stage('Build'){
            steps {
                echo '${WELCOME_GREETINGS}'
                echo 'Running Build Stage'
                bat 'mvn clean install -Dmaven.test.skip=true'
                echo 'Build Stage Done!'
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }

        }

        stage('Test'){
            environment {
                MESSAGE_TEST = 'Mix of UNIT and Integration Testing Execution!'
            }

            steps {
                echo 'Running Test Stage'
                echo '$MESSAGE_TEST'
                bat 'mvn test -Dmaven.test.failure.ignore=true'
                echo 'Test Stage Done!'
            }
        }

        stage('Deploy'){
            steps {
                echo 'Running Deploy Stage'
                bat 'mvn deploy -Dmaven.test.skip=true';
                echo 'Deploy Stage Done!'
                echo 'Pipeline: $SUCCESS_MESSAGE'
            }
        }
    }

}