CREATE TABLE tb_users (
    id uuid PRIMARY KEY,
    name VARCHAR(32),
    email VARCHAR(60),
    password VARCHAR(60),
    user_type SMALLINT
)