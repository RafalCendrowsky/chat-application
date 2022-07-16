CREATE TABLE IF NOT EXISTS "message" (
    "message_id"   VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
    "user_id"      VARCHAR(60),
    "content"      VARCHAR      NOT NULL,
    "content_type" VARCHAR(128) NOT NULL,
    "sent"         TIMESTAMP    NOT NULL
);

CREATE TABLE IF NOT EXISTS "user" (
    "user_id"      VARCHAR(60)  DEFAULT RANDOM_UUID() PRIMARY KEY,
    "last_seen_id" VARCHAR(60),
    "username"     VARCHAR(60)  NOT NULL
);