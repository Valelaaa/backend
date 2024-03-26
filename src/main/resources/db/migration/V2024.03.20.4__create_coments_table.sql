CREATE TABLE IF NOT EXISTS comments
(
    comment_id        VARCHAR(36) PRIMARY KEY,
    comment_message   TEXT,
    profile_id VARCHAR(36),
    created_date      TIMESTAMP NOT NULL,
    post_id           VARCHAR(36),
    parent_comment_id VARCHAR(36) REFERENCES comments (comment_id),
    rating_id         VARCHAR(36)
);
