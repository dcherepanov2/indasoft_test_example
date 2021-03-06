import java.awt.Container;
import java.util.ArrayList;
import java.util.List;

import javax.swing.*;

import GUI.EmployeeTableModel;

import java.awt.*;
import java.awt.event.*;
import entity.Employee;
import repository.EmployeesRepo;

public class AppJFrame extends JFrame{// GUI with JDBC

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static EmployeesRepo employeeRepo = new EmployeesRepo();
	private static List<Employee> employees = new ArrayList<>();
	private JLabel jLabel = new JLabel("??????? Id ????????????: "); 
	private JLabel jLabel1 = new JLabel("??????? ??????? ?????????: ");
	
	private JTextField text = new JTextField("",5);
	private JTextField text1 = new JTextField("",5);
	
	private JButton jButton = new JButton("????????? ??????");
	private Container container;
	
	class ButtonEventListener implements ActionListener{

		@Override
		public void actionPerformed(ActionEvent arg0) {
			AppJFrame newWindow = new AppJFrame(2);
			try {
				employeeRepo.setErrorMessageNull();
				employeeRepo.connectionWithQuery(Integer.parseInt(text.getText()),Double.parseDouble(text1.getText()));
			}catch(Exception e) {
				JOptionPane.showMessageDialog(null,"????????? ???????????? ????????? ????????","??????",JOptionPane.PLAIN_MESSAGE);
				return;
			}
			employees = employeeRepo.getEmployeesFromQuerySQL();
			
			EmployeeTableModel appJFrame = new EmployeeTableModel();
			
			JTable jTable = new JTable(appJFrame);
			JScrollPane employeeTable = new JScrollPane(jTable);
			employeeTable.setPreferredSize(new Dimension(400,400));
			
			for(Employee emp:employees) {
				String []str = new String[5];
				str[0] = String.valueOf(emp.getId());
				str[1] =  String.valueOf(emp.getDepartament());
				str[2] = String.valueOf(emp.getChiefId());
				str[3] = emp.getName();
				str[4] = String.valueOf(emp.getSalary());
				appJFrame.addDate(str);
			}
			
			newWindow.add(employeeTable,new GridBagConstraints(0,0,1,1,1,1,
						GridBagConstraints.NORTH,GridBagConstraints.BOTH,new Insets(1,1,1,1),0,0));
			newWindow.setVisible(true);
		}
		
	}
	public AppJFrame(int windowNumber) {
		super("Indasoft swing");
		switch(windowNumber) {
		case 1:{
			this.setSize(600,600);
			setExtendedState(MAXIMIZED_BOTH);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			container = this.getContentPane();
			setLayout(new FlowLayout());//new GridLayout(5,employees.size(),2,2
			container.add(jLabel);
			container.add(text);
			container.add(jLabel1);
			container.add(text1);
			jButton.addActionListener(new ButtonEventListener());
			container.add(jButton);
		}
		case 2:{
	        this.setSize(new Dimension(400,400));
	        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	        this.setLocationRelativeTo(null);
	        this.setLayout(new GridBagLayout());
		}
		
	}
}
	public static void main(String[] args) {
		AppJFrame app = new AppJFrame(1);
		app.setVisible(true);
	}
}
