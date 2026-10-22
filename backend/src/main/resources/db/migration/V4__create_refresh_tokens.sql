
CREATE TABLE refresh_tokens (
                                id BIGINT GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
                                user_id BIGINT NOT NULL,
                                token_hash VARCHAR(64) NOT NULL UNIQUE,
                                family_id UUID NOT NULL,
                                expires_at TIMESTAMPTZ NOT NULL,
                                created_at TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
                                revoked_at TIMESTAMPTZ,
                                replaced_by_id BIGINT,

                                CONSTRAINT fk_refresh_tokens_user
                                    FOREIGN KEY (user_id)
                                        REFERENCES users(id)
                                        ON DELETE CASCADE,

                                CONSTRAINT fk_refresh_tokens_replaced_by
                                    FOREIGN KEY (replaced_by_id)
                                        REFERENCES refresh_tokens(id)
                                        ON DELETE SET NULL,

                                CONSTRAINT chk_refresh_tokens_not_self_replaced
                                    CHECK (replaced_by_id IS NULL OR replaced_by_id <> id)
);

CREATE INDEX idx_refresh_tokens_user_id
    ON refresh_tokens(user_id);

CREATE INDEX idx_refresh_tokens_family_id
    ON refresh_tokens(family_id);

CREATE INDEX idx_refresh_tokens_expires_at
    ON refresh_tokens(expires_at);
