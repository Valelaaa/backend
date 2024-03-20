CREATE TABLE IF NOT EXISTS posts
(
    post_id      VARCHAR(36) PRIMARY KEY,
    title        VARCHAR(255) NOT NULL,
    description  TEXT,
    author_id    VARCHAR(36),
    created_date TIMESTAMP    NOT NULL,
    category_id  VARCHAR(36),
    comment_id   VARCHAR(36),
    rating_id    VARCHAR(36)
);