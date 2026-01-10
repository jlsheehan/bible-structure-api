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
                branch 'master'
            }
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                    sh './mvnw deploy -Dnexus.username=$USERNAME -Dnexus.password=$PASSOWRD'
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

