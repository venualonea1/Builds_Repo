def BranchName="master"
def regions='deployToStaging\ndeployToSandBox\ndeployToProduction'
crd1='jenkins-aws-stage-key'
crd2='jenkins-aws-prod-key'
crd3='jenkins-aws-sand-key'
def userInput
//def call(string Credentials,String Credential, String deployToStaging,String deployToProdcution,String deployToStaging)
pipeline{
agent any
 environment {
        AWS_ACCESS_KEY_ID     = credentials('aws-jenkins-user-id')
        AWS_SECRET_ACCESS_KEY = credentials('aws-jenkins-user-name')
	
    }	
stages {
	stage("Checkout of the applicaion"){
    steps{
      script{
	      checkout([$class: 'GitSCM', branches: [[name: 'master']], userRemoteConfigs: [[credentialsId: 'github-venu-cred', url: 'https://github.com/venualonea1/calculator.git']]])
	      sh 'npm install && npm build'
      }

    }

    }
  stage('Checking the AWS '){
  steps{
  script{
	  timeout(time: 40, unit: 'SECONDS') {
	  userInput =input(id: 'userInput', message: 'Select Regions for Deploy:?',
                            parameters: [choice(name: 'Choices',choices: "${regions}",description:"Select the Region")]) 
	  
	  }
/*
timeout(time: 300, unit: 'SECONDS') {
    println 'Waiting for input'
    userInput = input id: 'DefineBucket', message: 'Want to continue?', ok: 'Yes', parameters: [string(defaultValue: '', description: '', name: 's3BucketName')]
	}*/
		
	  echo "${userInput}"
	  
	  
	
	  if(userInput.contains("deployToSandBox")){
			  echo "Deploying to Sandbox"
			  echo "${crd3}"
		  
	  }else if(userInput.contains("deployToStaging")){
		  echo "${crd1}"
	  }else if(userInput.contains("deployToProduction")){
	  echo "Deploy to Prod "	  
	  }else{
		  echo  "No Choice Parameter Selected "
	  	 currentBuild.result = 'FAILURE'
	  }
	
		  
        		    echo "Checking the AWS Cli Installation"
	 		    sh  'aws --version'
	  		    echo "Set the Region"
	  		    sh 'aws configure set region us-east-2'

				}
			}
		}
    
	}

}
