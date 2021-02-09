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
			timeout(300){//change the conventional times 
			userInput = input(
			id: 'Proceed1' ,message: 'Go ahead with parameters', parameters:[
			[$class: 'FileParameterDefination',name: 'FilePath', description:"This is the fileupload test"]
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
