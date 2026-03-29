# My App Helm Chart

This Helm chart deploys Service A and Service B microservices to Kubernetes.

## Quick Important Notes
- Uses Java 21 & Spring Boot 3.2.5 images.
- No database or third-party dependencies required.
- Service A exposes `/message` on port 8080.
- Service B exposes `/message` on port 8081 and calls Service A internally.
- All configuration, secrets, and image tags are managed via `values.yaml`.

## Prerequisites
- Helm 3.x installed
- Kubernetes cluster (Minikube, Kind, or cloud)
- Docker (for building custom images, if needed)

## Setup & Installation
1. **Review and edit `values.yaml`**
   - Set image tags, secrets, ports, and config values as needed.
2. **Lint the chart:**
   ```
   helm lint .
   ```
3. **Dry run to preview resources:**
   ```
   helm install my-app . --dry-run --debug
   ```
4. **Install the chart:**
   ```
   helm install my-app .
   ```
   Or upgrade:
   ```
   helm upgrade my-app .
   ```

## Accessing the Services
- **Minikube:**
  ```
  minikube service service-a
  minikube service service-b
  ```
- **Port-forward:**
  ```
  kubectl port-forward svc/service-a 8080:8080
  kubectl port-forward svc/service-b 8081:8081
  ```
- **NodePort/LoadBalancer:**
  - Use `kubectl get svc` to find the external port/IP.

## Testing
- Service A: [http://localhost:8080/message](http://localhost:8080/message)
- Service B: [http://localhost:8081/message](http://localhost:8081/message)

## Managing the Release
- **View resources:**
  ```
  helm get manifest my-app
  kubectl get all
  ```

---

## Helm Commands Quick Reference

- **Install:**
  ```
  helm install my-app .
  ```
- **Upgrade:**
  ```
  helm upgrade my-app .
  ```
- **Uninstall:**
  ```
  helm uninstall my-app
  ```
- **Uninstall:**
  ```
  helm uninstall my-app
  ```

## Security Note
- Do not store real secrets in `values.yaml` for production. Use external secret management or CI/CD injection.