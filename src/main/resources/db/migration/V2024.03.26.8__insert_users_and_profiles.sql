INSERT INTO users (user_id, nickname, first_name, last_name, email, phone_number, password)
SELECT '123e4567-e89b-12d3-a456-426614174000', 'testuser', 'John', 'Doe', 'testuser@example.com', '+1234567890', 'password'
WHERE NOT EXISTS (
    SELECT 1 FROM users WHERE user_id = '123e4567-e89b-12d3-a456-426614174000'
);

INSERT INTO profiles (profile_id, user_picture, user_id)
SELECT '111e4567-e89b-12d3-a456-426614174000', 'https://example.com/user_picture.jpg', '123e4567-e89b-12d3-a456-426614174000'
WHERE NOT EXISTS (
    SELECT 1 FROM profiles WHERE user_id = '123e4567-e89b-12d3-a456-426614174000'
);
