import javax.swing.*;
import GUI.GUI;

public class AppJFrame extends JFrame{/**
	 * 
	 */
	private static final long serialVersionUID = 2861698829037104188L;

	public static void main(String[] args){// GUI with JDBC
		GUI app = new GUI(1);
		app.setVisible(true);
	}
	
}
