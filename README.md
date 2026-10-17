# ForgeFlow

**A Project Management Platform**

Developed by **Pham Gia Khanh**

ForgeFlow is a production-oriented project management platform designed to simulate real-world software development workflows while serving as an end-to-end engineering lab.

The project combines **backend engineering, frontend development, AI integration, cloud infrastructure, and DevOps automation** into a single application. It focuses not only on building functional features but also on applying practical engineering principles, security practices, automated delivery, and production-style deployment workflows.

## Overview

ForgeFlow helps teams organize projects, manage tasks, plan sprints, and collaborate throughout the software development lifecycle.

Beyond its core project management capabilities, the platform is designed to integrate an AI service for task generation, project analysis, and sprint planning. The infrastructure will progressively evolve toward containerized deployment, cloud hosting, Kubernetes orchestration, GitOps, and comprehensive observability.

## Features

### Project Management
- Organization and project management
- Organization and project membership management
- Role-based access control for organizations and projects
- Sprint management and task organization
- Task assignment, subtasks, and task lifecycle management
- Comments and collaboration
- Search and activity tracking

### AI Integration
- AI-powered task generation and project analysis
- AI-assisted sprint planning
- Tool-calling capabilities for AI-driven workflows
- Retrieval-Augmented Generation (RAG) for document-based context and retrieval
- Human-in-the-loop workflows with user confirmation before executing proposed actions

### DevOps & Infrastructure
- Containerized services with Docker
- Automated build, test, and deployment pipelines
- AWS-based cloud infrastructure
- Infrastructure as Code with Terraform
- Kubernetes orchestration and application deployment
- GitOps workflows with Argo CD
- Monitoring, logging, and distributed tracing

> **Development status:** Core application features and infrastructure are being implemented incrementally. See the roadmap below for the current progress.

## Architecture

ForgeFlow follows a service-oriented approach that separates the frontend, backend, and AI capabilities into distinct components.

- **Frontend:** Provides the user interface for project management and collaboration.
- **Backend API:** Handles business logic, authentication, authorization, data persistence, and application workflows.
- **AI Service:** A separate Python service responsible for AI-powered features, agent execution, tool calling, and contextual retrieval.
- **Database:** Stores application data and manages schema evolution through version-controlled migrations.
- **Infrastructure:** Supports containerization, automated delivery, cloud deployment, orchestration, and observability.

The architecture is intended to evolve alongside the project, with an emphasis on clear separation of responsibilities, maintainability, and operational reliability.

## Tech Stack

| Area | Technologies |
|---|---|
| Backend | Java, Spring Boot, Spring Security, Spring Data JPA |
| Frontend | React, Vite |
| Database & Migrations | PostgreSQL, Flyway |
| AI Service | Python, FastAPI, LLMs, AI Agents, Tool Calling, RAG |
| Containers & CI/CD | Docker, GitHub Actions |
| Cloud & IaC | AWS, Terraform |
| Orchestration & GitOps | Kubernetes, Argo CD |
| Observability | Prometheus, Grafana, Loki, OpenTelemetry |

## Engineering Focus

ForgeFlow is built to explore practical engineering challenges beyond basic CRUD development, including:

- **Software Engineering:** Modular design, RESTful API development, validation, exception handling, and maintainable code organization.
- **Security:** Authentication, authorization, role-based access control, and secure API design.
- **Data Management:** Relational data modeling, database constraints, and controlled schema migrations.
- **AI Engineering:** Integrating an independent AI service, using tools to support application workflows, and retrieving relevant context through RAG.
- **DevOps & Cloud:** Containerization, CI/CD automation, Infrastructure as Code, and cloud deployment.
- **Platform Operations:** Kubernetes orchestration, GitOps-based delivery, metrics, centralized logging, and distributed tracing.

## Roadmap

- [x] Organization management
- [x] Project management
- [x] Organization and project member management
- [x] Sprint management
- [x] Task and subtask management
- [x] Label management
- [x] TaskComment management
- [ ] TaskLabel management - In progress
- [x] Roles management
- [ ] Users management
- [ ] Authentication and authorization — In Progress
- [ ] Comments and collaboration — Upcoming
- [ ] Search and activity tracking — Upcoming
- [ ] Frontend integration — Upcoming
- [ ] AI agent for task generation and project analysis — Upcoming
- [ ] AI-assisted sprint planning — Upcoming
- [ ] RAG-based document retrieval — Upcoming
- [ ] Automated CI/CD and AWS deployment — Upcoming
- [ ] Kubernetes and GitOps deployment — Upcoming
- [ ] Observability with metrics, logs, and distributed tracing — Upcoming

## Project Status

**In Progress**

ForgeFlow is under active development. Features are delivered incrementally, with the goal of building a functional project management platform supported by a realistic, automated, and observable deployment environment.