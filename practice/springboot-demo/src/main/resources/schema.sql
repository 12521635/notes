DROP TABLE IF EXISTS note;

CREATE TABLE note (
    id         BIGINT AUTO_INCREMENT PRIMARY KEY,
    title      VARCHAR(100)  NOT NULL COMMENT '标题',
    content    TEXT          NULL     COMMENT '内容',
    deleted    TINYINT       NOT NULL DEFAULT 0 COMMENT '逻辑删除：0-未删除 1-已删除',
    created_at TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP     NOT NULL DEFAULT CURRENT_TIMESTAMP
);
