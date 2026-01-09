pipeline {
    agent any
    environment {
        GRGIT_CREDS = credentials('Bitbucket')
        GRGIT_USER = "$GRGIT_CREDS_USR"
        GRGIT_PASS = "$GRGIT_CREDS_PSW"
    }
    stages {
        stage('Build') {
            steps {
                sh './mvnw package'
            }
        }
        stage("Release") {
            when {
                branch 'master'
            }
            steps {
                sh './mvnw deploy'
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

