CREATE TYPE role_scope AS ENUM(
    'ORGANIZATION',
    'PROJECT'
);

CREATE TYPE task_status AS ENUM(
    'TODO',
    'IN_PROGRESS',
    'DONE',
    'CANCELLED'
);

CREATE TYPE task_priority AS ENUM(
    'LOW',
    'MEDIUM',
    'HIGH',
    'CRITICAL'
);

CREATE TYPE task_type AS ENUM(
    'TASK',
    'BUG',
    'FEATURE'
);

ALTER TABLE roles ALTER COLUMN scope TYPE role_scope USING scope::role_scope;
ALTER TABLE tasks ALTER COLUMN status TYPE task_status USING status::task_status;
ALTER TABLE tasks ALTER COLUMN priority TYPE task_priority USING priority::task_priority;
ALTER TABLE tasks ALTER COLUMN type TYPE task_type USING type::task_type;