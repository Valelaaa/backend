CREATE TABLE IF NOT EXISTS profiles
(
    profile_id   VARCHAR(36) PRIMARY KEY,
    user_picture VARCHAR(255),
    user_id      VARCHAR(36) NOT NULL ,
    posts_id VARCHAR(36),
    comments_id VARCHAR(36)

);