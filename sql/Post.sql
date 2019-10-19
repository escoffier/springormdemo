DROP DATABASE IF EXISTS postsdb;
CREATE DATABASE IF NOT EXISTS postsdb;
USE postsdb;

SELECT 'CREATING DATABASE STRUCTURE' as 'INFO';

DROP TABLE IF EXISTS posts,
    postcomments;

/*!50503 set default_storage_engine = InnoDB */;
/*!50503 select CONCAT('storage engine: ', @@default_storage_engine) as INFO */;

CREATE TABLE posts (
    id       INT            not null,
    title    VARCHAR(256)   not null ,
    PRIMARY KEY (id)
);


CREATE TABLE postcomments (
    id         INT              NOT NULL AUTO_INCREMENT,
    review     VARCHAR(256)     NOT NULL ,
    post_id    INT              NOT NULL ,
    FOREIGN KEY (post_id) REFERENCES posts (id) ON DELETE CASCADE ,
    PRIMARY KEY (id)
);
