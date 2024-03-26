CREATE TABLE IF NOT EXISTS profiles_ratings(
    profile_id VARCHAR(36),
    rating_id VARCHAR(36),
    rating_value INT,
    PRIMARY KEY (profile_id, rating_id)
)