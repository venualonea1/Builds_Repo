pipeline{
agent any

stages {
  stage('Stage1: Go to Root'){
  steps{
  script{
 sh 'sudo su ; cd /home/TerraAdmin/TerraForm_Pkg/; ls -ltr;terraform init'
 //sh 'terraform init'
  sleep(10)
 sh 'sudo su ; cd /home/TerraAdmin/TerraForm_Pkg/;terraform plan'
  
  }
  
  }
  }
  

}

}
