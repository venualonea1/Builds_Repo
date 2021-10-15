
def BranchName="master"
def regions='deployToStaging\ndeployToSandBox\ndeployToProduction'

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
  stage('Checking the AWS '){
  steps{
  script{
	   def userInput = input(
                            id: 'userInput', message: 'Select Regions for Deploy:?',
                            parameters: [choice(name: 'Choices',choices: "${regions}",description:"Select the Region")])
	  		    
	
		  if(userInput.get("deployToSandBox")){
			  echo "Deploying to Sandbox"
			  
		  }else if(userInput.get("deployToStaging")){
		  	echo "Deploying to Staging"
		  }else{
		  	echo "Deploying to Production"
		  
		  }
		  
        		    echo "Checking the AWS Cli Installation"
	 		    sh  'aws --version'
	  		    echo "Set the Region"
	  		    sh 'aws configure set region us-east-2'

				}
			}
		}
    stage("Checkout of the applicaion	"){
    steps{
      script{
	      checkout([$class: 'GitSCM', branches: [[name: 'master']], userRemoteConfigs: [[credentialsId: 'github-venu-cred', url: 'https://github.com/venualonea1/calculator.git']]])
	      sh 'npm install && npm build'
      }

    }

    }
	}

}
