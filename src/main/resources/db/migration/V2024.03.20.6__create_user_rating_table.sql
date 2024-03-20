CREATE TABLE IF NOT EXISTS users_rating(
    user_id VARCHAR(36),
    rating_id VARCHAR(36),
    rating_value INT,
    PRIMARY KEY (user_id, rating_id)
)