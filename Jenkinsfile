pipeline {
    agent any

    environment {
        BROWSERSTACK_USERNAME = credentials('BROWSERSTACK_USERNAME')
        BROWSERSTACK_ACCESS_KEY = credentials('BROWSERSTACK_ACCESS_KEY')
        USER_LOGIN = credentials('USER_LOGIN')
        USER_PASSWORD = credentials('USER_PASSWORD')
    }

    stages {
        stage('Test') {
            steps {
                sh './gradlew clean test'
            }
        }
    }
}