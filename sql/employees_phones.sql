INSERT INTO employees.phones (id, owner_no, p_number, area_code) VALUES (3, 10040, '18920302390', '21');
INSERT INTO employees.phones (id, owner_no, p_number, area_code) VALUES (4, 10040, '16789026547', '22');


select emp_no, employees.first_name , s.salary, s.from_date, s.to_date from employees inner join salaries s on employees.emp_no = s.emp_no where s.emp_no=10040;