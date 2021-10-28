package GUI;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import entity.Employee;
import exception.IndasoftException;
import repository.EmployeesRepo;

public class GUI extends JFrame{
	
	private Container container;
	private static final long serialVersionUID = 1L;
	private static EmployeesRepo employeeRepo = new EmployeesRepo();
	private static List<Employee> employees = new ArrayList<>();
	private JLabel jLabel = new JLabel("Введите Id департамента: "); 
	private JLabel jLabel1 = new JLabel("Введите процент повышения: ");
	private int exceptionCount = 0;
	
	private JTextField text = new JTextField("",5);
	private JTextField text1 = new JTextField("",5);
	
	private JButton jButton = new JButton("Выполнить запрос");

	public static void printError() {
			if(employeeRepo.getErrorList().size()!=0) {
				for(IndasoftException e: employeeRepo.getErrorList()) {
					JOptionPane.showMessageDialog(null,e.getErrorMessage(),"Ошибка",JOptionPane.CANCEL_OPTION);
				}
			}
		employeeRepo.setErrorListNull();
	}
	
	public GUI(int windowNumber) {
		super("Indasoft swing");
		switch(windowNumber) {
		case 1:{
			this.setSize(600,600);
			setExtendedState(MAXIMIZED_BOTH);
			this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			container = this.getContentPane();
			setLayout(new FlowLayout());
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
		class ButtonEventListener implements ActionListener{

			@Override
			public void actionPerformed(ActionEvent arg0) {
				
				GUI newWindow = new GUI(2);
				try {
					Integer.parseInt(text.getText());
					Double.parseDouble(text1.getText());
				}catch(Exception e) {
					employeeRepo.addError(new IndasoftException("Проверьте правильность введенных значений"));
				}
				
				employeeRepo.connectionWithQuery(Integer.parseInt(text.getText()),Double.parseDouble(text1.getText()));
				exceptionCount = employeeRepo.getErrorList().size();
				printError();
				if(exceptionCount != 0) {
					exceptionCount = 0;
					return;}
				employees = employeeRepo.getEmployeesFromQuerySQL();
				EmployeeTableModel appJFrame = new EmployeeTableModel();
			
				JTable jTable = new JTable(appJFrame);
				JScrollPane employeeTable = new JScrollPane(jTable);
				employeeTable.setPreferredSize(new Dimension(400,400));

				for(Employee emp:employees) {
					String []str = new String[6];
					str[0] = String.valueOf(emp.getId());
					str[1] =  String.valueOf(emp.getDepartament());
					str[2] = String.valueOf(emp.getChiefId());
					str[3] = emp.getName();
					str[4] = String.valueOf(emp.getSalary());
					str[5] = String.valueOf(emp.getOldSalary());
					appJFrame.addDate(str);
				}
				
				newWindow.add(employeeTable,new GridBagConstraints(0,0,1,1,1,1,
							GridBagConstraints.NORTH,GridBagConstraints.BOTH,new Insets(1,1,1,1),0,0));
				newWindow.setVisible(true);
			}
			
		}	
	
	}

