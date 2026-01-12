pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './mvnw package'
            }
        }
        stage("Release") {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                    sh './mvnw deploy -Dnexus-releases.username=$USERNAME -Dnexus-releases.password=$PASSOWRD -Dnexus-snapshots.username=$USERNAME -Dnexus-snapshots.password=$PASSOWRD'
                }
            }
        }
    }
}

