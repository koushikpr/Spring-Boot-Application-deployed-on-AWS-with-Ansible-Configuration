# Containerized Spring Boot Application with Kubernetes Deployment and Ansible Playbook Files for Deploying on AWS EC2 Instance and Google Kubernetes Engine


This Project Demonstrates how you can Containerize your Java Spring Boot Application using Docker, Create a Kubernetes Deployment and Host it in AWS or GCP.

Pre - requisites for this Project are 
 - Docker Desktop with Enabled Kubernetes Extensions 
 - MiniKube ( Optional ) 
 - Apache Maven 
 - AWS Account / GCP Account
 - Git 


---

1. Build your Spring Boot Application Using Maven Build 

- Run >>  mvn --clean install package
- A jar file will be created in the src/main/java/target folder

---

2. Container building for Spring Application
- Provide the path for the Jar file in the target variable of the Dockerfile
- Build the Container using Docker Compose >> docker-compose build -v variables.env
- Tag the Image in the format of <docker_username>/<name for ur image> >> docker tag <image-id>  <docker_username>/<name for ur image>
- Push the Image to DockerHub >> docker push <image tag>
  
---

3. Container Orchestration Using Kubernetes
- Create a namespace for your deployment ( Optional )
- The YAML files provided Includes both Deployment and Service
- Enable kubernetes >> kubectl start
- Add the Deployment and Service from YAML >> kubectl apply -f local_kubernetes.yml && kubectl apply -f mongodb_local.yml
- Verify the status of Deployments >> kubectl get pods
- Verify the Service end points >> kubectl get svc
