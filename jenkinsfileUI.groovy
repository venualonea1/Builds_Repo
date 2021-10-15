
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
	stage("Checkout of the applicaion	"){
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
	   def userInput = input(id: 'userInput', message: 'Select Regions for Deploy:?',
                            parameters: [choice(name: 'Choices',choices: "${regions}",description:"Select the Region")])    
		
	  echo "${userInput}"
	  if(userInput.contains("deployToSandBox")){
			  echo "Deploying to Sandbox"
		  withCredentials([usernamePassword(credentialsId: 'crd3', passwordVariable: 'pass', usernameVariable: 'user')]) {
		  
		  }
    // the code here can access $pass and $user
}
		  echo "${crd3}"
		  
			  
	  }else if(userInput.contains("deployToStaging")){
	  	 withCredentials([usernamePassword(credentialsId: 'crd1', passwordVariable: 'pass', usernameVariable: 'user')]) {
		  
		  }
		  echo "${crd1}"
	  }else{
		 withCredentials([usernamePassword(credentialsId: 'crd2', passwordVariable: 'pass', usernameVariable: 'user')]) {
		  
		  }	
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
