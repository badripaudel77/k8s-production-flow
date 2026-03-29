# Microservices K8s Setup (Without Helm)

This guide explains how to build, run, and test the Service A and Service B Spring Boot microservices using Docker, Docker Compose, and Kubernetes (without Helm).

## Quick Important Notes
- **Java 21 & Spring Boot 3.2.5** are used for both services.
- No database or third-party dependencies are required.
- Service A exposes `/message` on port 8080.
- Service B exposes `/message` on port 8081 and calls Service A internally.
- All build artifacts and Docker images are self-contained.

## Prerequisites
- Docker & Docker Compose installed
- Java 21 (for local builds)
- Maven (for local builds)
- kubectl & a running Kubernetes cluster (for K8s deployment)

## Local Build & Run
1. **Build JARs:**
   - From each service directory:
     ```
     mvn clean package -DskipTests
     ```
2. **Run Locally:**
   - Service A:
     ```
     java -jar target/service-a-0.0.1-SNAPSHOT.jar
     ```
   - Service B:
     ```
     java -jar target/service-b-0.0.1-SNAPSHOT.jar
     ```

## Docker Compose
1. **Build & Start Both Services:**
   ```
   docker compose up --build
   ```
2. **Test Endpoints:**
   - Service A: [http://localhost:8080/message](http://localhost:8080/message)
   - Service B: [http://localhost:8081/message](http://localhost:8081/message)

## Kubernetes (Without Helm)
1. **Build Docker Images:**
   - From each service directory:
     ```
     docker build -t service-a:latest .
     docker build -t service-b:latest .
     ```
2. **Tag & Push Images (if using remote registry):**
   - Example for Docker Hub (You can just use mine if you want to):
     ```
     docker tag service-a:latest <your-dockerhub-username>/service-a:latest
     docker push <your-dockerhub-username>/service-a:latest
     # Repeat for service-b
     ```
3. **Apply K8s Manifests:**
   - From the `k8s-without-helm` folder:
     ```
     kubectl apply -f .
     ```
4. **Verify Pods & Services:**
   ```
   kubectl get pods
   kubectl get svc
   ```


## Running & Accessing Apps on Kubernetes

After deploying the manifests, you can access the services in several ways:

### 1. Using `kubectl port-forward`

- Forward local ports to your service pods:
   - Service A:
      ```
      kubectl port-forward svc/service-a 8080:8080
      ```
      Access at: [http://localhost:8080/message](http://localhost:8080/message)
   - Service B:
      ```
      kubectl port-forward svc/service-b 8081:8081
      ```
      Access at: [http://localhost:8081/message](http://localhost:8081/message)

### 2. Using NodePort (if configured)

- If your Service manifests use `type: NodePort`, find the assigned port:
   ```
   kubectl get svc
   ```
   Then access the service at `http://<node-ip>:<nodeport>`.

### 3. Using LoadBalancer (Cloud Clusters)

- If using a cloud provider, `type: LoadBalancer` will expose an external IP:
   ```
   kubectl get svc
   ```
   Access the service at the EXTERNAL-IP shown.

---

## Testing
- After deployment, test endpoints as above using your browser, curl, or Postman.

---