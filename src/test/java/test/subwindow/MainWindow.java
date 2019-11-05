package test.subwindow;

import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainWindow extends JFrame {

	public MainWindow() throws HeadlessException {
		super();

		setTitle("MainWindow");
		setSize(300, 100);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel p1 = new JPanel();
		
		JButton btn = new JButton("SubWindow");
		btn.setBounds(50, 50, 80, 25);
		p1.add(btn);
		
		btn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				SubWindow subWin = new SubWindow();
				subWin.setVisible(true);
			}
		});
		
		this.add(p1);
		
		this.setVisible(true);
	}
	
	public static void main(String[] args) {
		
		MainWindow t = new MainWindow();
	}

}
