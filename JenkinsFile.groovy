pipeline{
agent{
	node{
		label 'shivani'
		
	}
}

stages {
  stage('Stage1: Go to Root'){
  steps{
  script{
		try{
			timeout(time: 300, unit: 'SECONDS'){//change the conventional times 
			userInput = input(
			id: 'Proceed1' ,message: 'Go ahead with parameters', parameters:[
			[$class:'FileParameterDefinition',name: 'FilePath', description:"This is the fileupload test"]
			]
			)
			}
			
		}catch(err){
			
			if("SYSTEM" == user.toString()){
				didTimeout = true
				echo "InputTime Out"
			}
		}
  
				}
			}
		}
	}

}
