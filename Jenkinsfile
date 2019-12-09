pipeline {
	// agent { docker { image 'maven:3.3.3' } }
	agent any

	stages {
		stage('Checkout from SCM') {
			steps {
				checkout scm
				// git url: 'https://github.com/sebastian9486/spring-boot-playground.git'
			}
		}

		stage('Maven Build') {
			steps {
				sh "mvn clean package"
			}
		}

		stage('Sonar') {
			steps {
				sh "mvn sonar:sonar"
			}
		}

		stage("Sonar Quality Gate") {
			steps {
				timeout(time: 1, unit: 'HOURS') {
					waitForQualityGate abortPipeline: true
				}
			}
		}

		stage('OWASP Dependency Check') {
			steps {
				sh 'mvn --version'
			}
		}

		stage('Build Docker image') {
			steps {
				sh 'mvn --version'
			}
		}

		stage('Upload to Docker Hub') {
			steps {
				sh 'mvn --version'
			}
		}

		stage('Deploy to AWS Staging') {
			steps {
				sh 'mvn --version'
			}
		}

		stage('Pen Test on AWS Staging') {
			steps {
				sh 'mvn --version'
			}
		}

		stage('Load Test on AWS Staging') {
			steps {
				sh 'mvn --version'
			}
		}

		stage('Git Changelog') {
			steps {
				sh 'mvn --version'
				// https://plugins.jenkins.io/git-changelog
			}
		}

		stage('Deploy to AWS Prod') {
			steps {
				sh 'mvn --version'
			}
		}

		stage('Smoke Test on AWS Prod') {
			steps {
				sh 'mvn --version'
			}
		}

		stage ('Slack notification on build') {
			steps {
				sh 'mvn --version'
			}
		}
	}
}
