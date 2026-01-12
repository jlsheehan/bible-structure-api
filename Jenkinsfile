pipeline {
    agent any

    stages {
        stage('Build') {
            steps {
                withCredentials([usernamePassword(credentialsId: 'nexus', passwordVariable: 'PASSWORD', usernameVariable: 'USERNAME')]) {
                    sh './mvnw package deploy -Dnexus.username=$USERNAME -Dnexus.password=$PASSWORD'
                }
            }
        }
    }
}

