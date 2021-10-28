package repository;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;
import exception.IndasoftException;


public class EmployeesRepo {//work with data and save him in location program 

	private static List<Employee> employeesFromQuery = new ArrayList<>();
	private List<IndasoftException> listError = new ArrayList<>();
	
	public void addError(IndasoftException error) {
		listError.add(error);
	}
	
	public  List<IndasoftException> getErrorList(){
		return listError;
	}
	
	public void setErrorListNull(){
		listError = new ArrayList<IndasoftException>();
	}
	
	public boolean validation(Integer departament, Double procent){
		if(departament == null || procent == null || procent > 100) {
			listError.add(new IndasoftException("Проверьте правильность введенных значений"));
			return true;
		}
		return false;
	}
	public void connectionWithQuery(Integer id_dep, Double precent) {
		if(this.validation(id_dep, precent)!=false) {
			return;
		}
		String connectionUrl =
              "jdbc:sqlserver://DESKTOP-VC14LGR\\SQLEXPRESS.database.windows.net:1434;"
                      + "database=ERP_DEP_EMP;"
                      + "user=admin1;"
                      + "password=123;"
                      + "encrypt=true;"
                      + "trustServerCertificate=true;"
                      + "loginTimeout=30;";
		
		try {
			
			Connection connection = DriverManager.getConnection(connectionUrl);
			String query = "EXEC [dbo].[UPDATESALARYFORDEPARTMENT] " + String.valueOf(id_dep.intValue())+ 
					","+String.valueOf(precent.doubleValue());
			Statement statement = connection.createStatement(); 
			ResultSet resultSet = statement.executeQuery(query);
			while(resultSet.next()){
                Employee employee = new Employee();
                employee.setId(resultSet.getInt(1));
                employee.setDepartament(resultSet.getInt(2));
                employee.setChiefId(resultSet.getInt(3));
                employee.setName(resultSet.getString(4));
                employee.setSalary(resultSet.getInt(5));
                employee.setOldSalary(resultSet.getInt(6));
                employeesFromQuery.add(employee);
            }
			connection.close();
		} catch (SQLException e) {
			listError.add(new IndasoftException(e.getMessage()));
		}
	}
	public List<Employee> getEmployeesFromQuerySQL(){
		return employeesFromQuery;
	}

}
