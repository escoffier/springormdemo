
USE employees;

CREATE TABLE phones (
   id           INT             NOT null,
   owner_no     INT             NOT NULL,
   p_number     CHAR(20)        NOT NULL,
   area_code    CHAR(8)         NOT NULL,
   FOREIGN KEY (owner_no)  REFERENCES employees (emp_no)    ON DELETE CASCADE,
   PRIMARY KEY (id)
);