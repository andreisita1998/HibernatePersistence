create database testA;

create table employees
(id int primary key auto_increment, 
firstname varchar(50),
age int, 
address varchar(50),
salary double
)
select* from employees