package test;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class TestTabbedPanel extends JFrame {

	public static void main(String[] args) {
		
		TestTabbedPanel p = new TestTabbedPanel();
	}
	
	public TestTabbedPanel() {
		setSize(300, 200);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tp = new JTabbedPane(JTabbedPane.TOP);
		
		JPanel p1 = new JPanel();
		JLabel l1 = new JLabel("James");
		l1.setBounds(50, 50, 80, 25);
		p1.add(l1);
		JTextField t1 = new JTextField();
		t1.setBounds(100, 20, 165, 20);
		p1.add(t1);
		
		
		JPanel p2 = new JPanel();
		
		tp.add(p1, "Panel1");
		tp.add(p2, "Panel2");
		
		this.add(tp);
	}
}
