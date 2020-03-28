pipeline{
agent any

stages {
  stage('Stage1: Go to Root'){
  steps{
  script{
 sh 'sudo su ; cd /home/TerraAdmin/TerraForm_Pkg'
 sh 'ls -ltr;pwd'
 sh 'terraform init'
  sleep(10)
 sh 'terraform plan'
  
  }
  
  }
  }
  

}

}
