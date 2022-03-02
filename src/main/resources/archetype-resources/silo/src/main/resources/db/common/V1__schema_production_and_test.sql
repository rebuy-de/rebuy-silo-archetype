CREATE SCHEMA IF NOT EXISTS ${databaseSchema};

DROP TABLE IF EXISTS ${databaseSchema}.messages;

CREATE TABLE ${databaseSchema}.messages
(
    id           BIGSERIAL PRIMARY KEY,
    headers      JSONB,
    payload      JSONB,
    created_at   TIMESTAMP WITH TIME ZONE,
    send_at      TIMESTAMP WITH TIME ZONE,
    confirmed_at TIMESTAMP WITH TIME ZONE
);

DROP TABLE IF EXISTS ${databaseSchema}.messages_consumed;

CREATE TABLE ${databaseSchema}.messages_consumed
(
    message_identification VARCHAR PRIMARY KEY,
    consumed_at            TIMESTAMP WITH TIME ZONE NOT NULL
);

DROP INDEX IF EXISTS ${databaseSchema}.ix_messages_created_at;

CREATE INDEX ix_messages_created_at
    ON ${databaseSchema}.messages (created_at)
    WHERE send_at IS NULL;
