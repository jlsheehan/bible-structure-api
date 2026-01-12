pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './mvnw package'
            }
        }
        stage("Release") {
            when {
                tag ''
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                    sh './mvnw deploy -Dnexus-releases.username=$USERNAME -Dnexus-releases.password=$PASSOWRD'
                }
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

