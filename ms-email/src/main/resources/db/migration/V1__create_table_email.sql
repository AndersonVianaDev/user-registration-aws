CREATE TABLE tb_emails (
    id uuid PRIMARY KEY,
    id_user uuid,
    email_to VARCHAR(60),
    subject VARCHAR(255),
    text VARCHAR,
    send_date_email TIMESTAMP,
    status_email SMALLINT

)