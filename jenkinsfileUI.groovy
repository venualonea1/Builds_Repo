pipeline{
agent any

stages {
  stage('Checking the AWS '){
  steps{
  script{
        echo "Checking the AWS Cli Installation"
	 	sh  'aws --version'
	  	withCredentials([usernamePassword(credentialsId: 'AWSS3', passwordVariable: 'S3Uname', usernameVariable: 'S3Uname')]) {
			sh 'aws s3 ls'
    				
}

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
