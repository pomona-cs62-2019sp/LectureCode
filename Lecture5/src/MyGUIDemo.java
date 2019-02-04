import java.awt.FlowLayout;

import javax.swing.*;

public class MyGUIDemo extends JFrame{
	public MyGUIDemo() {
		super();
		JPanel myPanel = new 
				JPanel(new FlowLayout());
			myPanel.add(new JLabel("Demo"));
			myPanel.add(new JButton("Button"));
		setContentPane(myPanel);
    }
	
	public static void main(String[] args) {
		MyGUIDemo demo = new MyGUIDemo();
		demo.setSize(400, 400);
		demo.setVisible(true);
	}
}
