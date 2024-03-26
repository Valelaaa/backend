ALTER TABLE profiles
    DROP CONSTRAINT IF EXISTS fk_profiles_user_id,
    ADD CONSTRAINT fk_profiles_user_id FOREIGN KEY (user_id) REFERENCES users (user_id);


ALTER TABLE posts
    DROP CONSTRAINT IF EXISTS fk_post_profile_id,
    DROP CONSTRAINT IF EXISTS fk_post_category_id,
    DROP CONSTRAINT IF EXISTS fk_post_rating_id,
    ADD CONSTRAINT fk_post_profile_id FOREIGN KEY (profile_id) REFERENCES profiles (profile_id),
    ADD CONSTRAINT fk_post_category_id FOREIGN KEY (category_id) REFERENCES categories (category_id),
    ADD CONSTRAINT fk_post_rating_id FOREIGN KEY (rating_id) REFERENCES ratings (rating_id);


ALTER TABLE comments
    DROP CONSTRAINT IF EXISTS fk_comment_profile_id,
    DROP CONSTRAINT IF EXISTS fk_comment_post_id,
    DROP CONSTRAINT IF EXISTS fk_comment_rating_id,

    ADD CONSTRAINT fk_comment_profile_id FOREIGN KEY (profile_id) REFERENCES profiles (profile_id),
    ADD CONSTRAINT fk_comment_post_id FOREIGN KEY (post_id) REFERENCES posts (post_id),
    ADD CONSTRAINT fk_comment_rating_id FOREIGN KEY (rating_id) REFERENCES ratings (rating_id);

ALTER TABLE profiles_ratings
    DROP CONSTRAINT IF EXISTS fk_profile_ratings_profile_id,
    DROP CONSTRAINT IF EXISTS fk_profile_ratings_rating_id,
    ADD CONSTRAINT fk_profile_ratings_profile_id FOREIGN KEY (profile_id) REFERENCES profiles (profile_id),
    ADD CONSTRAINT fk_profile_ratings_rating_id FOREIGN KEY (rating_id) REFERENCES ratings (rating_id);
