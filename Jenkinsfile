pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                    sh './mvnw package deploy -Dnexus-releases.username=$USERNAME -Dnexus-releases.password=$PASSWORD -Dnexus-snapshots.username=$USERNAME -Dnexus-snapshots.password=$PASSWORD'
                }
            }
        }
    }
}

