ALTER TABLE organizations
    ADD CONSTRAINT UQ_unique_name UNIQUE (name);

ALTER TABLE projects
    ADD CONSTRAINT UQ_project_name UNIQUE (organization_id, name);

ALTER TABLE sprints
    ADD CONSTRAINT UQ_sprint_name UNIQUE (project_id, name);

ALTER TABLE labels
    ADD CONSTRAINT UQ_label_name UNIQUE (project_id, name);