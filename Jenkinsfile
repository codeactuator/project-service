import io.netty.handler.codec.MessageAggregationException

pipeline{

    agent any

    tools {
        maven 'Maven'
        jdk 'Java8'
    }

    environment {
        WELCOME_GREETINGS = "Hello Folks!"
        SUCCESS_MESSAGE = "Successfully Done!"
        POST_SUCCESS_MESSAGE = "Preparing to run the application..."
    }


    stages {
        stage('Build'){
            steps {
                echo "${WELCOME_GREETINGS}"
                echo "Running Build Stage"
                bat 'mvn clean install -Dmaven.test.skip=true'
                echo "Build Stage Done!"
            }
            post {
                success {
                    junit 'target/surefire-reports/**/*.xml'
                }
            }

        }

        stage('Test'){
            environment {
                MESSAGE_TEST = "Mix of UNIT and Integration Testing Execution!"
            }

            steps {
                echo "Running Test Stage"
                echo "$MESSAGE_TEST"
                bat 'mvn test -Dmaven.test.failure.ignore=true'
                echo "Test Stage Done!"
            }
        }

        stage('Deploy'){
            steps {
                echo "Running Deploy Stage"
                bat 'mvn deploy -Dmaven.test.skip=true';
                echo "Deploye Stage Done!"
                echo "Pipeline: $SUCCESS_MESSAGE"
            }
        }
    }

    post {

        always {
            echo "${POST_SUCCESS_MESSAGE}"
            bat 'mvn spring-boot:run'
        }

        success {
            echo "Build Successfully Deployed!"
        }

        failure {
            echo "Build got some error, please look into the build log!"
        }

        chaneged {
            //Only run the steps in post if the current Pipeline’s or stage’s run has a different completion status from its previous run.
            echo "changed in POST"
        }

        fixed {
            //Only run the steps in post if the current Pipeline’s or stage’s run is successful and the previous run failed or was unstable.
            echo "fixed in POST"
        }

        regression {
            //Only run the steps in post if the current Pipeline’s or stage’s run’s status is failure, unstable, or aborted and the previous run was successful.
            echo "regression in POST"
        }

        aborted {
            //Only run the steps in post if the current Pipeline’s or stage’s run has an "aborted" status, usually due to the Pipeline being manually aborted. This is typically denoted by gray in the web UI.
            echo "aborted in POST"
        }

        unstable {
            //Only run the steps in post if the current Pipeline’s or stage’s run has an "unstable" status, usually caused by test failures, code violations, etc. This is typically denoted by yellow in the web UI.
            echo "unstable in POST"
        }

        unsuccessful {
            //Only run the steps in post if the current Pipeline’s or stage’s run has not a "success" status. This is typically denoted in the web UI depending on the status previously mentioned.
            echo "unsuccessful in POST"
        }

        cleanup {
            //Run the steps in this post condition after every other post condition has been evaluated, regardless of the Pipeline or stage’s status.
            echo "cleanup in POST"
        }
    }

}