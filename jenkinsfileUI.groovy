pipeline{
agent any
 environment {
        AWS_ACCESS_KEY_ID     = credentials('aws-jenkins-user-id')
        AWS_SECRET_ACCESS_KEY = credentials('aws-jenkins-user-name')
    }
	
stages {
  stage('Checking the AWS '){
  steps{
  script{
        echo "Checking the AWS Cli Installation"
	 	sh  'aws --version'
	  	echo "Set the Region"
	  	sh 'aws configure set region us-east-2'

				}
			}
		}
    stage("listing the AWS "){
    steps{
      script{

      echo "checking the ls of appliaction"
      sh 'aws s3 ls'
      }

    }

    }
	}

}
