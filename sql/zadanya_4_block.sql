--4.1    ������� ������ �����������, ������� �������� ���������� ����� ����, ��� � ����������������� ������������.
SELECT * FROM employee e1
WHERE salary < (SELECT salary FROM employee WHERE department_id = e1.department_id AND chif_id = 1)

--4.2    ������� ������ �����������, ������� �������� � ������ ����������� ���������� ����� � ����� ������.
SELECT * FROM employee e1
WHERE salary = (SELECT MIN(salary) FROM employee WHERE department_id = e1.department_id)

--4.3    ������� ������ ID �������, ���������� ����������� � ������� �� ��������� ���� �������.
SELECT * FROM department e1
WHERE (SELECT COUNT(*)
 FROM employee WHERE department_id = e1.id) <= 3

--4.4    ������� ������ �����������, �� ������� ������������ ������������, ������� ������� �� � ��� �� ������.
SELECT * FROM employee e1
WHERE NOT EXISTS(SELECT id FROM employee WHERE department_id = e1.department_id AND chif_id= 1)

--4.5    ����� ������ ID ������� � ������������ ��������� ���������� ������ �����������.

EXEC [dbo].FIVE