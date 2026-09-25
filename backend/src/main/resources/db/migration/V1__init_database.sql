CREATE TABLE users(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    full_name VARCHAR(50) NOT NULL ,
    email VARCHAR(254) UNIQUE NOT NULL ,
    password_hash VARCHAR(255) NOT NULL ,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE roles(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    name VARCHAR(20) NOT NULL ,
    scope VARCHAR(20) NOT NULL ,
    UNIQUE (name, scope)
);

CREATE TABLE organizations(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    name VARCHAR(150) NOT NULL ,
    created_at timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE organization_members(
    organization_id BIGINT NOT NULL ,
    user_id BIGINT NOT NULL ,
    role_id BIGINT NOT NULL ,
    PRIMARY KEY (organization_id, user_id),
    FOREIGN KEY (organization_id) REFERENCES organizations(id),
    FOREIGN KEY (user_id) REFERENCES users(id) ,
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE projects(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    organization_id BIGINT NOT NULL ,
    name VARCHAR(100) NOT NULL ,
    FOREIGN KEY (organization_id) REFERENCES organizations(id)
);

CREATE TABLE project_members(
    project_id BIGINT NOT NULL ,
    user_id BIGINT NOT NULL ,
    role_id BIGINT NOT NULL ,
    PRIMARY KEY (project_id, user_id),
    FOREIGN KEY (project_id) REFERENCES projects(id),
    FOREIGN KEY (user_id) REFERENCES users(id),
    FOREIGN KEY (role_id) REFERENCES roles(id)
);

CREATE TABLE sprints(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    name VARCHAR(100) NOT NULL ,
    project_id BIGINT NOT NULL ,
    start_date DATE NOT NULL ,
    end_date DATE NOT NULL  ,
    UNIQUE (id, project_id),
    FOREIGN KEY (project_id) REFERENCES projects(id)
);

CREATE TABLE tasks(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    title VARCHAR(50) NOT NULL ,
    description TEXT,
    status VARCHAR(20) NOT NULL ,
    priority VARCHAR(20) NOT NULL ,
    type VARCHAR(20) NOT NULL ,
    sprint_id BIGINT ,
    project_id BIGINT NOT NULL ,
    created_by BIGINT NOT NULL ,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    UNIQUE (id, project_id),
    FOREIGN KEY (project_id) REFERENCES projects(id),
    FOREIGN KEY (created_by) REFERENCES users(id) ,
    FOREIGN KEY (sprint_id, project_id) REFERENCES sprints(id, project_id)
);

CREATE TABLE task_assignees(
    task_id BIGINT NOT NULL ,
    user_id BIGINT NOT NULL ,
    project_id BIGINT NOT NULL ,
    PRIMARY KEY (task_id, user_id),
    FOREIGN KEY (project_id, user_id) REFERENCES project_members(project_id, user_id),
    FOREIGN KEY (task_id, project_id) REFERENCES tasks(id, project_id)
);

CREATE TABLE task_comments(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    task_id BIGINT NOT NULL ,
    user_id BIGINT NOT NULL ,
    content TEXT NOT NULL ,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP ,
    FOREIGN KEY (task_id) REFERENCES tasks(id) ,
    FOREIGN KEY (user_id) REFERENCES users(id)
);

CREATE TABLE labels(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    name VARCHAR(50) NOT NULL ,
    project_id BIGINT NOT NULL ,
    UNIQUE (project_id, name),
    UNIQUE (id, project_id),
    FOREIGN KEY (project_id) REFERENCES projects(id)
);

CREATE TABLE task_labels(
    task_id BIGINT NOT NULL ,
    label_id BIGINT NOT NULL ,
    project_id BIGINT NOT NULL ,
    PRIMARY KEY (task_id, label_id),
    FOREIGN KEY (task_id, project_id) REFERENCES tasks(ID, project_id) ,
    FOREIGN KEY (label_id, project_id) REFERENCES labels(id, project_id)
);

CREATE TABLE attachments(
    id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY ,
    task_id BIGINT NOT NULL ,
    file_name VARCHAR(100) NOT NULL ,
    object_key VARCHAR(500) UNIQUE NOT NULL ,
    content_type VARCHAR(100) NOT NULL ,
    file_size BIGINT NOT NULL ,
    uploaded_by BIGINT NOT NULL ,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    FOREIGN KEY (task_id) REFERENCES tasks(id),
    FOREIGN KEY (uploaded_by) REFERENCES users(id)
);