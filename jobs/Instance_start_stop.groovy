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
				
	
	switch(action.toUpperCase()){
				case "JVM-1" :
				echo "Starting the 1st JVM"
				StatCommand = "ps -aef|grep -i TOMCAT_INSTANCE-1 |grep -v grep"
				Process_Command = "${StatCommand}"
				
				//startApp = "/opt/TOMCAT_INSTANCE-1/bin/startup.sh"
				startresult = sh script: "${Process_Command}", returnStatus:true
				if(startresult == 0){
				echo"The JVM is started"
	
				}else{
					echo "The JVMM is not started"
							}
				break;
				case "JVM-2"
				echo "Starting the 1st JVM"
				StatCommand = "ps -aef|grep -i TOMCAT_INSTANCE-2 |grep -v grep"
				Process_Command = "${StatCommand}"
				
				//startApp = "/opt/TOMCAT_INSTANCE-1/bin/startup.sh"
				startresult = sh script: "${Process_Command}", returnStatus:true
				if(startresult == 0){
				echo"The TOMCAT_INSTANCE-2 is started"
	
				}else{
					echo "The TOMCAT_INSTANCE-2 is not started"
							}
	
	
				break;
\ 
	
					}
	
				}
			}
		}
	}
}