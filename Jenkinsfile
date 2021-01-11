pipeline {
    agent any
    environment {
        GRGIT_CREDS = credentials('Bitbucket')
        GRGIT_USER = '$GRGIT_CREDS_USR'
        GRGIT_PASS = '$GRGIT_CREDS_PSW'
    }
    stages {
        stage('Build') {
            steps {
                sh './gradlew build'
            }
        }
        stage("Release") {
            when {
                branch 'master'
            }
            steps {
                sh './gradlew build -Preckon.scope=minor -Preckon.stage=final reckonTagPush publish'
            }
        }
        stage("Dev Release") {
            when {
                branch 'development'
            }
            steps {
                sh './gradlew build -Preckon.scope=minor -Preckon.stage=dev reckonTagPush publish'
            }
        }
    }
}

