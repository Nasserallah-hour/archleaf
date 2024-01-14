pipeline {

  agent {

    kubernetes {

      label 'my-kubernetes-agent'

    }

  }

  

  stages {

    stage('Clone repository') {

      steps {

        git 'https://github.com/your-repo.git'

      }

    }

    

    stage('Build and push Docker image') {

      steps {

        sh 'docker build -t my-image:latest .'

        sh 'docker push my-image:latest'

      }

    }

    

    stage('Deploy to Kubernetes') {

      steps {

        kubernetesDeploy(

          configs: 'kubernetes/deployment.yaml',

          kubeconfigId: 'my-kubeconfig'

        )

      }

    }

  }

}