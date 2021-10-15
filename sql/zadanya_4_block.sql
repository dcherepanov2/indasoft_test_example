--4.1    ¬ывести список сотрудников, которые получают заработную плату ниже, чем у непосредственного руководител€.
SELECT * FROM employee e1
WHERE salary < (SELECT salary FROM employee WHERE department_id = e1.department_id AND chif_id = 1)

--4.2    ¬ывести список сотрудников, которые получают в отделе минимальную заработную плату в своем отделе.
SELECT * FROM employee e1
WHERE salary = (SELECT MIN(salary) FROM employee WHERE department_id = e1.department_id)

--4.3    ¬ывести список ID отделов, количество сотрудников в которых не превышает трех человек.
SELECT * FROM department e1
WHERE (SELECT COUNT(*)
 FROM employee WHERE department_id = e1.id) <= 3

--4.4    ¬ывести список сотрудников, не имеющих назначенного руководител€, который работал бы в том же отделе.
SELECT * FROM employee e1
WHERE NOT EXISTS(SELECT id FROM employee WHERE department_id = e1.department_id AND chif_id= 1)

--4.5    Ќайти список ID отделов с максимальной суммарной заработной платой сотрудников.

EXEC [dbo].FIVE