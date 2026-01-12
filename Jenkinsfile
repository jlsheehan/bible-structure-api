pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                sh './mvnw package'
                withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                    sh './mvnw deploy -Dnexus-releases.username=$USERNAME -Dnexus-releases.password=$PASSWORD -Dnexus-snapshots.username=$USERNAME -Dnexus-snapshots.password=$PASSWORD'
                }
            }
        }
    }
}

