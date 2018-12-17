action="${env.Action}"


pipeline{
	agent{
		node{
			label 'master'
		}
	}
	stages{
		stage('PM2 Status Check'){
		steps{
			script{
			Check_Command=" pm2 status"
				CheckStatus= sh script: "${Check_Command}" ,returnStatus: true
				
				if(CheckStatus==0){
				echo "showing the PM2 Status"
				}else{
				"Nothing is defined"
				}
				
				
			
						}
	
				}
			}
		}
	}
