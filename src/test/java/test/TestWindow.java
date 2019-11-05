package test;

import java.awt.BorderLayout;
import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class TestWindow extends JFrame {

	public TestWindow() throws HeadlessException {
		super();

//		setLocation(300, 300);
//		setSize(1500, 1000);
		setSize(300, 300);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//		setLayout(new BorderLayout());
		
		JPanel p1 = new JPanel();
		
//		p1.setLayout(null);
		
		JLabel l1 = new JLabel("James");
		l1.setBounds(50, 50, 80, 25);
		p1.add(l1);
		JTextField t1 = new JTextField();
		t1.setBounds(100, 20, 165, 20);
		p1.add(t1);
		
		this.add(p1);
		
//		JPanel p2 = new JPanel();
//		
//		p2.setLayout(null);
		
//		JLabel l2 = new JLabel("James");
//		l2.setBounds(150, 150, 80, 25);
//		p2.add(l2);
//		JTextField t2 = new JTextField();
//		t2.setBounds(200, 120, 165, 20);
//		p2.add(t2);
		
//		this.add(p2);
		
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		TestWindow t = new TestWindow();
	}

}
