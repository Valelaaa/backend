ALTER TABLE profiles
    DROP CONSTRAINT IF EXISTS fk_user_id,
    DROP CONSTRAINT IF EXISTS fk_post_id,
    DROP CONSTRAINT IF EXISTS fk_comment_id,
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id),
    ADD CONSTRAINT fk_post_id FOREIGN KEY (posts_id) REFERENCES posts (post_id),
    ADD CONSTRAINT fk_comment_id FOREIGN KEY (comments_id) REFERENCES comments (comment_id);
ALTER TABLE comments
    DROP CONSTRAINT IF EXISTS fk_parent_comment_id,
    DROP CONSTRAINT IF EXISTS fk_post_id,
    DROP CONSTRAINT IF EXISTS fk_rating_id,
    DROP CONSTRAINT IF EXISTS fk_comment_author_id,
    ADD CONSTRAINT fk_parent_comment_id FOREIGN KEY (parent_comment_id) REFERENCES comments (comment_id),
    ADD CONSTRAINT fk_post_id FOREIGN KEY (post_id) REFERENCES posts (post_id),
    ADD CONSTRAINT fk_rating_id FOREIGN KEY (rating_id) REFERENCES posts (post_id),
    ADD CONSTRAINT fk_comment_author_id FOREIGN KEY (comment_author_id) REFERENCES profiles (profile_id);
ALTER TABLE posts
    DROP CONSTRAINT IF EXISTS fk_author_id,
    DROP CONSTRAINT IF EXISTS fk_rating_id,
    DROP CONSTRAINT IF EXISTS fk_comment_id,
    DROP CONSTRAINT IF EXISTS fk_category_id,
    ADD CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES profiles (profile_id),
    ADD CONSTRAINT fk_rating_id FOREIGN KEY (rating_id) REFERENCES ratings (rating_id),
    ADD CONSTRAINT fk_comment_id FOREIGN KEY (comment_id) REFERENCES comments (comment_id),
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES categories (category_id);
ALTER TABLE users_rating
    DROP CONSTRAINT IF EXISTS fk_rating_id,
    DROP CONSTRAINT IF EXISTS fk_user_id,
    ADD CONSTRAINT fk_rating_id FOREIGN KEY (rating_id) REFERENCES ratings (rating_id),
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES profiles (profile_id);