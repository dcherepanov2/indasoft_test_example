--доп задание на разбиение по фио

DECLARE @Employee table(firstName varchar(200),lastName varchar(200),patronymic varchar(200))

INSERT INTO @Employee
SELECT 
		SUBSTRING(NAME,1,CHARINDEX(' ',name)),
		SUBSTRING(NAME,CHARINDEX(' ',name)+1,LEN(NAME)),
		CAST('' as varchar(200)) FROM EMPLOYEE;

UPDATE @Employee SET patronymic = SUBSTRING(lastName,CHARINDEX(' ',lastName),LEN(lastName)+1) FROM @Employee WHERE CHARINDEX(' ',lastName) != 0
UPDATE @Employee SET lastName = SUBSTRING(lastName,1,CHARINDEX(' ',lastName)) WHERE CHARINDEX(' ',lastName) != 0

SELECT * FROM @Employee