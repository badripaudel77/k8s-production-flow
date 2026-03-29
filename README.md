# What is This Repository?

This repository demonstrates a complete, production-ready microservices architecture using Spring Boot, Docker, Kubernetes, and Helm. It is designed as a hands-on example for building, containerizing, and orchestrating Java microservices in a modern cloud-native environment.

## What is Kubernetes?

Kubernetes (K8s) is an open-source platform for automating deployment, scaling, and management of containerized applications. It provides:
- **Automated container orchestration** (start, stop, restart, and scale containers)
- **Service discovery and load balancing**
- **Self-healing** (auto-restart, reschedule, replace failed containers)
- **Declarative configuration and automation**
- **Secret and configuration management**

## Why Do We Need Kubernetes?

Kubernetes enables you to:
- Run and manage applications reliably at scale
- Automate rollouts, rollbacks, and scaling
- Ensure high availability and fault tolerance
- Simplify deployment and operations across environments (dev, staging, production)
- Manage configuration and secrets securely

# Complete K8s Production

This project demonstrates a production-ready microservices architecture using Spring Boot, Docker, Kubernetes, and Helm. It includes two simple Java microservices (service-a and service-b) that communicate via REST APIs, containerized with Docker, orchestrated with Kubernetes, and managed using Helm charts.

## Architecture Overview

- **service-a**: Exposes a REST endpoint and calls service-b.
- **service-b**: Exposes a REST endpoint and returns a message.
- **Docker**: Each service is containerized using a multi-stage Dockerfile.
- **Kubernetes**: Deployments, Services, ConfigMaps, and Secrets are defined for both services.
- **Helm**: A Helm chart (`my-app-chart`) manages the deployment and configuration of both services.

### Architecture Diagram

```
+-------------------+        REST         +-------------------+
|                   | <----------------> |                   |
|   service-a Pod   |                    |   service-b Pod   |
|                   | -----------------> |                   |
+-------------------+        REST         +-------------------+
        |                                        |
        |                                        |
        v                                        v
+-------------------+                    +-------------------+
|  ConfigMap/Secret |                    |  ConfigMap/Secret |
+-------------------+                    +-------------------+
        |                                        |
        v                                        v
+-------------------+                    +-------------------+
|   Deployment      |                    |   Deployment      |
+-------------------+                    +-------------------+
        |                                        |
        v                                        v
+-------------------+                    +-------------------+
|   Service         |                    |   Service         |
+-------------------+                    +-------------------+
```

## Kubernetes Integration

- **Manifests**: All Kubernetes manifests are in the `k8s-without-helm` folder for direct `kubectl` usage.
- **Helm Chart**: The `my-app-chart` folder contains a Helm chart for templated, parameterized deployments.
- **ConfigMap & Secret**: Application configs and secrets are managed via Kubernetes ConfigMaps and Secrets, referenced in deployments.
- **Service Discovery**: Services communicate using Kubernetes DNS (e.g., `http://service-b:8080`).
- **Environment Variables**: Configs and secrets are injected as environment variables.

## How to Run

### 1. Build Docker Images

```
cd service-a
mvn clean package
docker build -t service-a:<version> .
cd ../service-b
mvn clean package
docker build -t service-b:<version> .
```

### 2. Run with Docker Compose

```
docker-compose up --build
```

### 3. Deploy to Kubernetes (without Helm)

```
kubectl apply -f k8s-without-helm/
```

### 4. Deploy with Helm

```
cd my-app-chart
helm install my-app .
```

## Customization & Configuration

- Override values in Helm using `--set`, `--set-file`, or `-f custom-values.yaml`.
- Update ConfigMaps and Secrets for environment-specific configuration.
- All secrets must be base64-encoded for Kubernetes.

## Project Structure

```
complete-k8s-production/
├── service-a/           # Spring Boot microservice A
├── service-b/           # Spring Boot microservice B
├── k8s-without-helm/    # Raw Kubernetes manifests
├── my-app-chart/        # Helm chart for both services
└── README.md            # Project overview and instructions
```

## Useful Commands

- Check pods: `kubectl get pods`
- View logs: `kubectl logs <pod-name>`
- Port-forward: `kubectl port-forward svc/service-a 8080:8080`
- Helm upgrade: `helm upgrade my-app .`
- Helm uninstall: `helm uninstall my-app`

## Notes

- No database or external dependencies are used.
- All configuration is environment-variable driven for 12-factor compliance.
- Designed for local (Minikube) and production Kubernetes clusters.

---

For more details, see the `my-app-chart/README.md` and `k8s-without-helm/README.md` files.