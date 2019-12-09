pipeline {
  agent { docker { image 'maven:3.3.3' } }
    stages {

      stage('Checkout from SCM') {
        // checkout scm
        git url: 'https://github.com/sebastian9486/spring-boot-playground.git'
      }

      stage('Maven Build') {
        sh "mvn clean package"
      }

	stage('Sonar') {
        sh "mvn sonar:sonar"
      }

      stage("Sonar Quality Gate") {
        steps {
          timeout(time: 1, unit: 'HOURS') {
            waitForQualityGate abortPipeline: true
          }
        }
      }

      stage('OWASP Dependency Check') {
        // Jenkins plugin: https://plugins.jenkins.io/dependency-check-jenkins-plugin
      }

      stage('Build Docker image') {
      }

      stage('Upload to Docker Hub') {
      }

      stage('Deploy to AWS Staging') {
      }

      stage('Pen Test on AWS Staging') {
      }

      stage('Load Test on AWS Staging') {
      }

      stage('Git Changelog') {
        // https://plugins.jenkins.io/git-changelog
      }

      stage('Deploy to AWS Prod') {
      }

	  stage('Smoke Test on AWS Prod') {
      }

	  stage ('Slack notification on build') {
	  }

    }
}
