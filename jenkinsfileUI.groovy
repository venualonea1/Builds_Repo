pipeline{
agent any

stages {
  stage('Checking the AWS '){
  steps{
  script{
        echo "Checking the AWS Cli Installation"
	  
          sh 'curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64-2.0.30.zip" -o "awscliv2.zip"'
sh 'unzip awscliv2.zip'
sh 'sudo ./aws/install'



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
