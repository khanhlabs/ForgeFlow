# ForgeFlow

**In Process — Developed by Pham Gia Khanh**

ForgeFlow is a production-style **project management platform** built as an engineering project for practicing **Software Engineering, DevOps, Cloud, Kubernetes, CI/CD, GitOps, Security, Observability, and AI Agent Engineering**.

The project is being developed from the ground up and is **currently in active development**. Features and infrastructure are introduced incrementally as the underlying concepts are implemented and understood.

> **Goal:** Build one system that can be developed, deployed, observed, secured, troubleshot, and explained through real implementation rather than memorization.

---

## What is ForgeFlow?

ForgeFlow is a collaborative project management platform inspired by tools such as Jira and GitLab.

Planned core features include:

* Organizations and projects
* Members, roles, and permissions
* Tasks, subtasks, labels, and sprints
* Comments and attachments
* Search
* Notifications
* Activity and audit information

ForgeFlow will also include an **AI Agent** capable of understanding project context and assisting with project-management workflows.

The AI Agent will be able to:

* Analyze project, task, sprint, and team data
* Generate tasks and subtasks
* Assist with sprint planning
* Analyze project progress
* Propose changes through controlled tools

AI-generated changes will follow a **human-in-the-loop** workflow:

```text
User Request
     ↓
AI Agent
     ↓
Analyze Context
     ↓
Generate Action Plan
     ↓
User Review
     ↓
Confirm
     ↓
Application Authorization
     ↓
Execute
```

The AI Agent will **not bypass application authorization or directly modify the database**.

---

## Technology Stack

### Backend

* Java
* Spring Boot
* Spring Security
* Spring Data JPA / Hibernate
* PostgreSQL
* Flyway
* Redis
* Elasticsearch
* JWT / OAuth2
* WebSocket
* JUnit / Mockito

### Frontend

* React
* Vite
* JavaScript / TypeScript

### AI

* AI Agent
* Tool Calling
* Context-aware workflows
* Human-in-the-loop confirmation

### DevOps / Cloud

* Docker / Docker Compose
* GitHub / GitLab CI
* AWS
* Terraform
* Kubernetes
* Argo CD
* Prometheus / Grafana
* Loki
* OpenTelemetry

---

## Architecture

The current architecture is intentionally simple and will evolve as the project grows.

```text
Frontend
    │
    ▼
Spring Boot API
    │
    ├── Domain Services
    │
    ├── Security
    │
    └── AI Agent
           │
           ├── Read Tools
           │
           └── Action Plan
                  │
                  ▼
            User Confirmation
                  │
                  ▼
           Domain Services
                  │
                  ▼
              Database
```

The long-term infrastructure will evolve toward a containerized, cloud-native and Kubernetes-based environment with CI/CD, GitOps, and observability.

---

## Development Status

> **Currently in active development.**

Current focus:

* Core domain and database design
* Organization and project management
* Task and sprint management
* Authentication and authorization
* Backend API implementation

Upcoming areas:

* AI Agent and tool calling
* Human-in-the-loop action execution
* Frontend implementation
* Testing
* Containerization
* CI/CD
* AWS infrastructure
* Kubernetes
* GitOps
* Observability

The order may change as the project evolves.

---

## Development Philosophy

### Build before memorizing

Understand concepts by implementing and testing them.

### Understand before abstracting

Do not introduce abstractions before understanding the problem they solve.

### Keep boundaries explicit

Domain logic, security, infrastructure, and AI should have clear responsibilities.

### AI assists, application decides

AI can analyze and propose actions, but authorization and business rules remain controlled by the application.

### Break things intentionally

Failure and troubleshooting are part of the learning process.

### Document important decisions

Architectural and infrastructure decisions should be documented together with their trade-offs.

---

## Long-Term Goal

ForgeFlow is not intended to be just another CRUD project.

The long-term goal is to build a system where I can **implement, deploy, monitor, secure, troubleshoot, and explain** the technologies involved — from backend engineering and databases to cloud infrastructure, Kubernetes, CI/CD, and AI Agent workflows.

**ForgeFlow is a work in progress.**
