--4.1
SELECT e1.* FROM dbo.EMPLOYEE e1, dbo.EMPLOYEE e2 WHERE e1.SALARY > e2.SALARY AND e2.id = e1.CHIF_ID;

--4.4
SELECT  e1.*
  FROM  dbo.EMPLOYEE e1
    LEFT JOIN dbo.EMPLOYEE e_chif on e_chif.ID = e1.CHIF_ID and e_chif.DEPARTMENT_ID = e1.DEPARTMENT_ID
  WHERE e_chif.id is NULL

--4.6
 SELECT SUM(SALARY) FROM EMPLOYEE