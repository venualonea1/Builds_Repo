action="${env.Action}"


pipeline{
	agent{
		node{
			label 'master'
		}
	}
	stages{
		stage('Application start'){
		steps{
			script{
	
		//instanceProp=readProperties file: "./properties/instance.properties"
				echo "where is my name"
	
	switch(action.toUpperCase()){
				case "JVM-1" :
				echo "Starting the 1st JVM"
				startApp = "/opt/TOMCAT_INSTANCE-1/bin/startup.sh"
				startresult = sh script: "${startApp}", returnStatus:true
				if(startresult == 0){
				echo"The JVM is started"
	
				}else{
					echo "The JVMM is not started"
							}
	
	
	
				break;
	
	
					}
	
				}
			}
		}
	}
}