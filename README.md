# ForgeFlow (In process)  
### Develop by ***Pham Gia Khanh***
---
**ForgeFlow** is a production-style project management platform built as an end-to-end engineering lab for practicing and validating modern **Software Engineering, DevOps, Cloud, Kubernetes, CI/CD, GitOps, Security, and Observability** skills.

The project is intentionally developed from the ground up, starting from a modular backend and relational database and progressively evolving into a containerized, automated, observable, and Kubernetes-based production environment.

> **Goal:** Build one system that forces me to understand, implement, deploy, troubleshoot, and explain the technologies and engineering practices used in my Software Engineering and DevOps skillset.

---

## What is ForgeFlow?

ForgeFlow is a collaborative project and issue management platform inspired by tools such as Jira and GitLab Issues.

Users can:

* Create and manage projects
* Manage project members and roles
* Create, assign, prioritize, and track issues
* Organize work using labels and sprints
* Comment on issues
* Upload attachments
* Search issues and project content
* Receive notifications
* View project activity and audit logs
* Monitor application deployments and system health

The application is also designed to serve as a realistic environment for implementing infrastructure and DevOps workflows.

---

## Engineering Goals

ForgeFlow is not only a CRUD application.

The main objective is to progressively build the system through the following engineering layers:

```text
Application
    ↓
Backend Engineering
    ↓
Database & Data Management
    ↓
Security
    ↓
Testing
    ↓
Containerization
    ↓
Cloud Infrastructure
    ↓
CI/CD
    ↓
Kubernetes
    ↓
GitOps
    ↓
Observability
    ↓
Security & Reliability
```

Each layer is implemented, tested, deployed, and intentionally broken where possible to understand how the system behaves under real-world conditions.

---

# Technology Stack

## Backend

* Java
* Spring Boot
* Spring Web
* Spring Security
* Spring Data JPA
* Hibernate
* Bean Validation
* JWT
* OAuth2
* WebSocket
* JUnit
* Mockito

## Database & Data

* PostgreSQL
* Flyway
* Redis
* Elasticsearch

## Frontend

* React
* Vite
* JavaScript / TypeScript
* REST API
* WebSocket

## Containerization

* Docker
* Docker Compose
* Multi-stage Docker builds
* Container health checks

## CI/CD

* Git
* GitHub / GitLab
* CI pipelines
* Automated testing
* Docker image builds
* Container image scanning
* Container registry
* Automated deployment

## Cloud

* AWS IAM
* AWS VPC
* Amazon EC2
* Amazon RDS
* Amazon S3
* Amazon ECR
* Application Load Balancer
* CloudFront
* Route 53
* SNS / SQS

## Infrastructure as Code

* Terraform

## Kubernetes

* Kubernetes
* Deployments
* Services
* Ingress
* ConfigMaps
* Secrets
* RBAC
* Resource Requests / Limits
* Liveness Probes
* Readiness Probes
* Horizontal Pod Autoscaler
* Rolling Updates
* Rollbacks

## GitOps

* Argo CD
* Declarative Kubernetes manifests
* Automated synchronization
* Application health monitoring
* Deployment rollback

## Observability

* Prometheus
* Grafana
* Loki
* Alertmanager
* OpenTelemetry

## Security / DevSecOps

* Spring Security
* RBAC
* IAM least privilege
* Dependency scanning
* Container image scanning
* Secret management
* SAST
* OWASP security practices

---

# Architecture

The target architecture evolves throughout the project.

The initial version is intentionally simple:

```text
                  ┌──────────────┐
                  │   Frontend   │
                  │    React     │
                  └──────┬───────┘
                         │
                         ▼
                  ┌──────────────┐
                  │ Spring Boot  │
                  │    API       │
                  └──────┬───────┘
                         │
              ┌──────────┼──────────┐
              │          │          │
              ▼          ▼          ▼
         PostgreSQL    Redis   Elasticsearch
```

The infrastructure will progressively evolve toward:

```text
                         Internet
                            │
                     Route 53 / DNS
                            │
                     CloudFront / ALB
                            │
                    ┌───────┴────────┐
                    │                │
                    ▼                ▼
                Frontend          Ingress
                                     │
                                Kubernetes
                                     │
                      ┌──────────────┼──────────────┐
                      │              │              │
                      ▼              ▼              ▼
                  Backend         Worker       WebSocket
                      │
             ┌────────┼────────┐
             │        │        │
             ▼        ▼        ▼
        PostgreSQL  Redis  Elasticsearch
             │
             ▼
             S3
```

Observability:

```text
Application / Kubernetes
        │
        ├── Prometheus ──→ Grafana
        │
        ├── Loki ────────→ Grafana
        │
        └── OpenTelemetry → Tracing
```

---

# CI/CD & GitOps

The target deployment workflow is:

```text
Developer
    │
    ▼
Git Push
    │
    ▼
CI Pipeline
    │
    ├── Build
    ├── Unit Tests
    ├── Integration Tests
    ├── Security Scanning
    └── Docker Build
            │
            ▼
         ECR
            │
            ▼
      Update GitOps
        Repository
            │
            ▼
          Argo CD
            │
            ▼
       Kubernetes
            │
            ▼
        Production
```

The goal is to make deployments:

* Automated
* Reproducible
* Version-controlled
* Observable
* Rollbackable

---

# Infrastructure as Code

AWS infrastructure is progressively managed using Terraform.

Target infrastructure includes:

```text
AWS
├── VPC
│   ├── Public Subnets
│   └── Private Subnets
│
├── IAM
├── Security Groups
├── ECR
├── RDS
├── S3
├── ALB
├── CloudFront
├── Route 53
└── Kubernetes Infrastructure
```

---

# Observability

The application is designed around the three major observability signals:

```text
Metrics
   │
   └── Prometheus → Grafana

Logs
   │
   └── Loki → Grafana

Traces
   │
   └── OpenTelemetry
```

Important metrics include:

* Request rate
* Error rate
* HTTP status codes
* P95/P99 latency
* JVM memory
* Garbage collection
* CPU usage
* Memory usage
* Database connection pool
* Kubernetes pod restarts
* Container resource usage

---

# Security

Security is treated as an engineering requirement rather than an additional feature.

Areas covered include:

* Authentication
* Authorization
* RBAC
* Password hashing
* JWT security
* Refresh token security
* Input validation
* SQL injection prevention
* XSS prevention
* CSRF considerations
* CORS configuration
* Rate limiting
* IAM least privilege
* Secret management
* Dependency scanning
* Container image scanning
* Secure Docker images
* Kubernetes security
* Network isolation

---

# Testing Strategy

Testing is implemented at multiple levels.

```text
                 Testing
                    │
       ┌────────────┼────────────┐
       │            │            │
       ▼            ▼            ▼
     Unit       Integration    E2E
     Tests        Tests        Tests
```

---

# Failure & Troubleshooting Lab

One of the main purposes of ForgeFlow is to practice troubleshooting rather than only implementing successful scenarios.

Examples of intentional failure scenarios:

```text
Application
├── Database unavailable
├── Redis unavailable
├── Elasticsearch unavailable
├── Invalid JWT
├── Expired token
└── High API latency

Docker
├── Container health check failure
├── Network connectivity issue
└── Environment variable misconfiguration

Kubernetes
├── CrashLoopBackOff
├── ImagePullBackOff
├── Pending Pod
├── Failed readiness probe
├── Failed liveness probe
├── Resource exhaustion
└── Service connectivity failure

AWS
├── Security Group misconfiguration
├── IAM permission failure
├── Private subnet connectivity
├── S3 access failure
└── Load balancer routing failure

CI/CD
├── Failed tests
├── Docker build failure
├── Image push failure
├── Deployment failure
└── Rollback
```

---

# Development Philosophy

ForgeFlow follows a few principles:

### 1. Build before memorizing

Instead of only reading documentation, concepts should be implemented and tested in the system.

### 2. Understand before abstracting

Avoid introducing infrastructure or abstractions before understanding the underlying problem.

### 3. Automate repetitive operations

Manual deployment should progressively be replaced by reproducible automation.

### 4. Everything important should be observable

If something can fail in production, there should eventually be a way to detect and diagnose it.

### 5. Break the system intentionally

Failure scenarios are part of the learning process.

### 6. Document engineering decisions

Important architectural and infrastructure decisions should be documented together with their trade-offs.

---

# Status

> **Early Development**

The project is intentionally developed incrementally.

Features, infrastructure, and technologies will be introduced only when their underlying concepts have been studied and understood.

---

# Long-Term Goal

The final goal is not simply to have a feature-complete application.

The goal is to be able to answer, demonstrate, and troubleshoot questions such as:

* How does Spring Security authenticate a request?
* How does JWT authentication work?
* How does JPA translate an object relationship into SQL?
* How do you identify and fix an N+1 query?
* How does a private EC2 instance access S3?
* How does a Docker container communicate with another container?
* What happens when a Kubernetes Pod crashes?
* How does Kubernetes perform a rolling update?
* How does Argo CD detect drift?
* How does a CI/CD pipeline safely deploy a new version?
* How do you monitor application latency?
* How do you investigate a sudden increase in HTTP 5xx errors?
* How do you troubleshoot a Pod stuck in `Pending`?
* How do you troubleshoot `ImagePullBackOff`?
* How does Terraform track infrastructure state?
* How should AWS IAM permissions be designed?
* What happens when Redis, PostgreSQL, or Elasticsearch becomes unavailable?

**ForgeFlow is a learning environment built to answer those questions through implementation and experimentation rather than memorization.**
