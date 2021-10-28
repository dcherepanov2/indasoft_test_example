package repository;

import java.sql.Connection; 
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import entity.Employee;

public class EmployeesRepo {//work with data and save him in location program 

	private static List<Employee> employeesFromQuery = new ArrayList<>();
	private String errorMessage = null;
	
	public String getErrorMessage() {
		return errorMessage;
	}
	
	public void setErrorMessageNull() {
		errorMessage = null;
	}
	
	public String validation(Integer departament, Double procent){
		if(departament == null || procent == null || procent > 100) {
			return "Проверьте правильность введенных значений";
		}
		return null;
	}
	public void connectionWithQuery(Integer id_dep, Double precent) {
		if(this.validation(id_dep, precent)!=null) {
			return;
		}
		String connectionUrl =
              "jdbc:sqlserver://DESKTOP-VC14LGR\\SQLEXPRESS.database.windows.net:1433;"
                      + "database=DEPARTAMENT;"
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
                employeesFromQuery.add(employee);
            }
			if(employeesFromQuery.size()==0) {
				errorMessage = "Указанного департамента не существует";
			}
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
			errorMessage = "Установить соединение с базой данных не удалось";
		}
	}
	public static List<Employee> getEmployeesFromQuerySQL(){
		return employeesFromQuery;
	}

}
