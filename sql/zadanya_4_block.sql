--4.1    Âûâåñòè ñïèñîê ñîòðóäíèêîâ, êîòîðûå ïîëó÷àþò çàðàáîòíóþ ïëàòó íèæå, ÷åì ó íåïîñðåäñòâåííîãî ðóêîâîäèòåëÿ.
SELECT * FROM employee e1
WHERE salary < (SELECT salary FROM employee WHERE department_id = e1.department_id AND chif_id = 1)

--4.2    Âûâåñòè ñïèñîê ñîòðóäíèêîâ, êîòîðûå ïîëó÷àþò â îòäåëå ìèíèìàëüíóþ çàðàáîòíóþ ïëàòó â ñâîåì îòäåëå.
SELECT * FROM employee e1
WHERE salary = (SELECT MIN(salary) FROM employee WHERE department_id = e1.department_id)

--4.3    Âûâåñòè ñïèñîê ID îòäåëîâ, êîëè÷åñòâî ñîòðóäíèêîâ â êîòîðûõ íå ïðåâûøàåò òðåõ ÷åëîâåê.
SELECT * FROM department e1
WHERE (SELECT COUNT(*)
 FROM employee WHERE department_id = e1.id) <= 3

--4.4    Âûâåñòè ñïèñîê ñîòðóäíèêîâ, íå èìåþùèõ íàçíà÷åííîãî ðóêîâîäèòåëÿ, êîòîðûé ðàáîòàë áû â òîì æå îòäåëå.
SELECT * FROM employee e1
WHERE NOT EXISTS(SELECT id FROM employee WHERE department_id = e1.department_id AND chif_id= 1)

--4.5    Íàéòè ñïèñîê ID îòäåëîâ ñ ìàêñèìàëüíîé ñóììàðíîé çàðàáîòíîé ïëàòîé ñîòðóäíèêîâ.
With dept_info as (
SELECT dd.id, dd.name, (SELECT Sum(salary) from Employee e where e.department_id = dd.id) as zp
  FROM department dd
)
SELECT di.id, di.zp
  FROM dept_info di
  WHERE di.zp in (SELECT max(di1.zp) FROM dept_info di1) 
