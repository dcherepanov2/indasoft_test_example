package entity;

public class Employee {//just entity from data


    private int id;

    private int departament;

    private int chiefId;

    private String name;

    private int salary;
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepartament() {
        return departament;
    }

    public void setDepartament(int departament) {
        this.departament = departament;
    }

    public int getChiefId() {
        return chiefId;
    }

    public void setChiefId(int chiefId) {
        this.chiefId = chiefId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }
    
    @Override
    public String toString() {
        return  "id=" + id +
                ", departament=" + departament +
                ", chiefId=" + chiefId +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                '}';
    }
	
}
