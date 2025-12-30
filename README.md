# spring-cicd-demo

Mini projet **Spring Boot (Java 8)** conçu pour démontrer un pipeline **CI/CD** avec **GitHub Actions** et **Docker Hub**.

## Prérequis
- Java 8 (local)
- Maven (local)
- Docker (local, optionnel si tu veux builder/pusher en local)
- Un compte GitHub + un compte Docker Hub

## Structure
```

.
├─ src/                         # Code Spring Boot
├─ pom.xml                      # Build Maven
├─ Dockerfile                   # Image runtime (copie target/*.jar)
└─ .github/workflows/ci-cd.yml  # Pipeline CI/CD

````

## Lancer en local (sans Docker)
```bash
mvn -B clean test
mvn -B spring-boot:run
````

Endpoints :

* `GET http://localhost:8080/api/hello`
* `GET http://localhost:8080/api/healthz`
* `GET http://localhost:8080/actuator/health`

## Tests

```bash
mvn -B test
```

## Docker (local)

### Build du JAR puis build de l’image

```bash
mvn -B -DskipTests clean package
docker build -t <dockerhub_user>/spring-cicd-demo:latest .
```

### Run

```bash
docker run --rm -p 8080:8080 <dockerhub_user>/spring-cicd-demo:latest
```

## CI/CD (GitHub Actions)

Le workflow est dans : `.github/workflows/ci-cd.yml`

### CI (Continuous Integration)

Déclenchement :

* à chaque **pull_request**
* à chaque **push** sur `main`

Étapes :

* Setup Java 8
* `mvn clean test`
* `mvn package`
* Upload du `.jar` en artifact

### CD (Docker Build & Push)

Déclenchement :

* uniquement sur **push `main`**
* après succès du job CI

Étapes :

* Build du `.jar`
* Login Docker Hub (via secrets)
* Build & Push de l’image Docker avec tags :

  * `latest`
  * `${{ github.sha }}`

## Secrets requis (obligatoire pour le CD)

Dans le repo GitHub :
`Settings → Secrets and variables → Actions → New repository secret`

* `DOCKERHUB_USERNAME` : ton username Docker Hub
* `DOCKERHUB_TOKEN` : un **Personal Access Token** Docker Hub

## Scénario de démo (pour l’exposé)

1. Push sur une branche / PR → CI tourne (tests + build).
2. Casse volontairement le test (ex: modifier le message attendu) → CI échoue → CD ne s’exécute pas.
3. Corrige → CI repasse au vert → CD pousse l’image sur Docker Hub.

## Déploiement (optionnel)

Sur une machine avec Docker :

```bash
docker pull <dockerhub_user>/spring-cicd-demo:latest
docker rm -f spring-cicd-demo || true
docker run -d --name spring-cicd-demo -p 8080:8080 <dockerhub_user>/spring-cicd-demo:latest
```

```
::contentReference[oaicite:0]{index=0}
```

