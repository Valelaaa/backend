ALTER TABLE profiles
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES users (user_id),
    ADD CONSTRAINT fk_post_id FOREIGN KEY (posts_id) REFERENCES posts (post_id),
    ADD CONSTRAINT fk_comment_id FOREIGN KEY (comments_id) REFERENCES comments (comment_id);
ALTER TABLE comments
    ADD CONSTRAINT fk_parent_comment_id FOREIGN KEY (parent_comment_id) REFERENCES comments (comment_id),
    ADD CONSTRAINT fk_post_id FOREIGN KEY (post_id) REFERENCES posts (post_id),
    ADD CONSTRAINT fk_rating_id FOREIGN KEY (rating_id) REFERENCES posts (post_id),
    ADD CONSTRAINT fk_comment_author_id FOREIGN KEY (comment_author_id) REFERENCES profiles (profile_id);
ALTER TABLE posts
    ADD CONSTRAINT fk_author_id FOREIGN KEY (author_id) REFERENCES profiles (profile_id),
    ADD CONSTRAINT fk_rating_id FOREIGN KEY (rating_id) REFERENCES ratings (rating_id),
    ADD CONSTRAINT fk_comment_id FOREIGN KEY (comment_id) REFERENCES comments (comment_id),
    ADD CONSTRAINT fk_category_id FOREIGN KEY (category_id) REFERENCES categories (category_id);
ALTER TABLE users_rating
    ADD CONSTRAINT fk_rating_id FOREIGN KEY (rating_id) REFERENCES ratings (rating_id),
    ADD CONSTRAINT fk_user_id FOREIGN KEY (user_id) REFERENCES profiles (profile_id);