pipeline {
  agent { docker { image 'maven:3.3.3' } }
    stages {

      stage('Checkout from SCM') {
        steps {
			// checkout scm
        	git url: 'https://github.com/sebastian9486/spring-boot-playground.git'
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
			echo '...'
		}
      }

      stage('Build Docker image') {
		  steps {
			  echo '...'
  		}
      }

      stage('Upload to Docker Hub') {
		  steps {
			  echo '...'
  		}
      }

      stage('Deploy to AWS Staging') {
		  steps {
			  echo '...'
  		}
      }

      stage('Pen Test on AWS Staging') {
		  steps {
			echo '...'
  		}
      }

      stage('Load Test on AWS Staging') {
		  steps {
			  echo '...'
  		}
      }

      stage('Git Changelog') {
		  steps {
			  echo '...'
			  // https://plugins.jenkins.io/git-changelog
  		}
      }

      stage('Deploy to AWS Prod') {
		  steps {
			  echo '...'
  		}
      }

	  stage('Smoke Test on AWS Prod') {
		  steps {
			  echo '...'
  		}
      }

	  stage ('Slack notification on build') {
		  steps {
			  echo '...'
  		}
	  }

    }
}
