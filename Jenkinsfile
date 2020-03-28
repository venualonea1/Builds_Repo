pipeline{
agent any

stages {
  stage('Stage1: Go to Root'){
  steps{
  script{
  sh 'sudo su - ; apt-get install  -y nginx'
   sh 'sudo ufw allow 80; ps -ef|grep nginx'
  
  }
  
  }
  }
  

}

}
