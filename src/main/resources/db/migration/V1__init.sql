CREATE TABLE posts(
    id BIGSERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    created TIMESTAMPTZ NOT NULL DEFAULT CURRENT_TIMESTAMP,
    likes INTEGER NOT NULL DEFAULT 0,
    UNIQUE (title)
);

INSERT INTO posts(title, content,created, likes) VALUES
                            ('Firs post', 'This is content of the first post', CURRENT_TIMESTAMP,10),
                            ('Second post', 'This is content of the second post', CURRENT_TIMESTAMP,3);