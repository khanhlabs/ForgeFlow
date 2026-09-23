# ForgeFlow

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

## Core Features

### Authentication & Authorization

* User registration and login
* JWT authentication
* Refresh token flow
* Password hashing
* OAuth2 authentication
* Role-Based Access Control (RBAC)
* Project-level permissions
* Organization-level permissions
* Token expiration and rotation
* Logout and token invalidation

### Project Management

* Organizations
* Projects
* Project members
* Teams
* Issues
* Comments
* Labels
* Priorities
* Sprints
* Issue assignment
* Issue status management
* Activity history
* Audit logs

### Search

* Full-text issue search
* Search by title and description
* Filtering
* Sorting
* Pagination
* Elasticsearch-based search

### File Management

* Issue attachments
* Amazon S3 storage
* Presigned URLs
* Private object storage
* CloudFront integration

### Notifications

* In-app notifications
* Asynchronous event processing
* Email/event-based notification flow

### Dashboard

* Project statistics
* Issue statistics
* Sprint progress
* Deployment information
* Application/system metrics

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

Terraform is used to practice:

* Providers
* Resources
* Variables
* Outputs
* Modules
* State management
* Remote state
* Resource dependencies
* Environment management
* Infrastructure lifecycle

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

Example alerts:

```text
High CPU
High Memory
High Error Rate
High Latency
Pod CrashLoopBackOff
Database Connection Exhaustion
Application Unavailability
```

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

Examples:

### Unit Tests

* Service logic
* Validation
* Utility functions
* Security logic

### Integration Tests

* REST API
* PostgreSQL
* Redis
* Elasticsearch
* Authentication
* Database transactions

### End-to-End Tests

* User registration
* Login
* Project creation
* Issue lifecycle
* Deployment workflow

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

The objective is to understand:

> **What failed → Why it failed → How to diagnose it → How to fix it → How to prevent it**

---

# Learning / Engineering Checklist

The project is developed progressively instead of implementing the entire architecture at once.

## Phase 1 — Backend Fundamentals

* [ ] Spring Boot
* [ ] REST API
* [ ] PostgreSQL
* [ ] JPA
* [ ] Hibernate
* [ ] Database relationships
* [ ] Transactions
* [ ] Validation
* [ ] Exception handling
* [ ] Pagination
* [ ] Database indexing
* [ ] Flyway

## Phase 2 — Security

* [ ] Spring Security
* [ ] Authentication
* [ ] Authorization
* [ ] JWT
* [ ] Refresh tokens
* [ ] RBAC
* [ ] OAuth2
* [ ] Password security
* [ ] CORS
* [ ] CSRF
* [ ] Rate limiting

## Phase 3 — Backend Advanced

* [ ] Redis
* [ ] Caching
* [ ] Elasticsearch
* [ ] Async processing
* [ ] Message queues
* [ ] WebSocket
* [ ] S3
* [ ] Presigned URLs

## Phase 4 — Testing

* [ ] JUnit
* [ ] Mockito
* [ ] Unit testing
* [ ] Integration testing
* [ ] API testing
* [ ] E2E testing
* [ ] Testcontainers

## Phase 5 — Containerization

* [ ] Dockerfile
* [ ] Multi-stage builds
* [ ] Docker networking
* [ ] Docker volumes
* [ ] Docker Compose
* [ ] Health checks
* [ ] Container optimization

## Phase 6 — AWS

* [ ] IAM
* [ ] VPC
* [ ] Subnets
* [ ] Route tables
* [ ] Internet Gateway
* [ ] NAT Gateway
* [ ] Security Groups
* [ ] EC2
* [ ] RDS
* [ ] S3
* [ ] ECR
* [ ] ALB
* [ ] CloudFront
* [ ] Route 53
* [ ] SNS
* [ ] SQS

## Phase 7 — CI/CD

* [ ] Git workflows
* [ ] Branching strategy
* [ ] Pull Requests
* [ ] Code Review
* [ ] CI
* [ ] Automated tests
* [ ] Docker image build
* [ ] Image registry
* [ ] Security scanning
* [ ] Automated deployment
* [ ] Rollback

## Phase 8 — Kubernetes

* [ ] Pods
* [ ] Deployments
* [ ] Services
* [ ] Ingress
* [ ] ConfigMaps
* [ ] Secrets
* [ ] Namespaces
* [ ] RBAC
* [ ] Resource requests
* [ ] Resource limits
* [ ] Probes
* [ ] HPA
* [ ] Rolling updates
* [ ] Rollbacks

## Phase 9 — GitOps

* [ ] Argo CD
* [ ] Declarative configuration
* [ ] Application manifests
* [ ] App of Apps
* [ ] Auto Sync
* [ ] Health checks
* [ ] Rollback
* [ ] Environment separation

## Phase 10 — Observability

* [ ] Prometheus
* [ ] Grafana
* [ ] Loki
* [ ] Alertmanager
* [ ] OpenTelemetry
* [ ] Metrics
* [ ] Logs
* [ ] Traces
* [ ] Alerting
* [ ] Incident troubleshooting

## Phase 11 — Infrastructure & DevSecOps

* [ ] Terraform
* [ ] Terraform modules
* [ ] Terraform state
* [ ] Remote backend
* [ ] IAM least privilege
* [ ] SAST
* [ ] Dependency scanning
* [ ] Container scanning
* [ ] Kubernetes security
* [ ] Secret management

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

# Project Structure

The repository is expected to evolve toward:

```text
forgeflow/
│
├── backend/
│   ├── src/
│   ├── Dockerfile
│   └── pom.xml
│
├── frontend/
│   ├── src/
│   └── Dockerfile
│
├── infra/
│   └── terraform/
│
├── deploy/
│   ├── docker/
│   └── kubernetes/
│
├── .github/
│   └── workflows/
│
├── docs/
│   ├── architecture/
│   ├── decisions/
│   ├── troubleshooting/
│   └── runbooks/
│
└── README.md
```

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
