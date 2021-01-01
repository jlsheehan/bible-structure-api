pipeline {
    agent any
    stages {
        stage('build') {
            steps {
                sh './gradlew build'
                archiveArtifacts artifacts: 'build/libs/*.jar', followSymlinks: false, onlyIfSuccessful: true
            }
        }
    }
}

