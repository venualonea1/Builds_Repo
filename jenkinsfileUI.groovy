
def BranchName="master"
def regions='deployToStaging\ndeployToSandBox\ndeployToProduction'
crd1='jenkins-aws-stage-key'
crd2='jenkins-aws-prod-key'
crd3='jenkins-aws-sand-key'

pipeline{
agent any
 environment {
        AWS_ACCESS_KEY_ID     = credentials('aws-jenkins-user-id')
        AWS_SECRET_ACCESS_KEY = credentials('aws-jenkins-user-name')
	
    }
	/*parameters{
	choice(name: 'CHOICE', choices: ['One', 'Two', 'Three'], description: 'Pick something')
	
	}*/
	
	
	
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
	  /* def userInput = input(id: 'userInput', message: 'Select Regions for Deploy:?',
                            parameters: [choice(name: 'Choices',choices: "${regions}",description:"Select the Region")]) */
	  
	  def userInput

timeout(time: 300, unit: 'SECONDS') {
    println 'Waiting for input'
    userInput = input id: 'DefineBucket', message: 'Want to continue?', ok: 'Yes', parameters: [string(defaultValue: '', description: '', name: 's3BucketName')]
	}
		
	  echo "${userInput}"
	  sh '''
	  		    echo "Set the Region"
	  		    aws configure set region us-east-2
			    aws s3 cp /home/ubuntu/  s3://${s3BucketName} --recursive 
		'''
	  if(userInput.contains("deployToSandBox")){
			  echo "Deploying to Sandbox"
		  

		  echo "${crd3}"
		  
			  
	  }else if(userInput.contains("deployToStaging")){
	  	 
		  echo "${crd1}"
	  }else{
			
	  echo "Deploy to Prod "	  
	  }
	 /* 
	  if(userInput.Choices("deployToSandBox")){
			  echo "Deploying to Sandbox"
			  
	  }else if(userInput.Choices("deployToStaging")){
		  	echo "Deploying to Staging"
		  }else{
		  	echo "Deploying to Production"
			echo "${crd2}"
		  
		  }*/
		  
        		    echo "Checking the AWS Cli Installation"
	 		    sh  'aws --version'
	  		    echo "Set the Region"
	  		    sh 'aws configure set region us-east-2'

				}
			}
		}
    
	}

}
