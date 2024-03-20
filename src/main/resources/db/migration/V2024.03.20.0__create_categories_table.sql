CREATE TABLE IF NOT EXISTS categories
(
    category_id          VARCHAR(36) PRIMARY KEY,
    category_name        VARCHAR(255) NOT NULL,
    category_title       VARCHAR(255) NOT NULL,
    category_image       VARCHAR(255),
    category_description TEXT         NOT NULL,
    tag_line             VARCHAR(255) NOT NULL,
    post_count           BIGINT       NOT NULL
);