CREATE TABLE IF NOT EXISTS ratings
(
    rating_id    VARCHAR(36) PRIMARY KEY,
    comment_id   VARCHAR(36),
    post_id      VARCHAR(36),
    rating_value INT NOT NULL
);
