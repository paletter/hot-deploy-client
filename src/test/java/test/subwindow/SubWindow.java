package test.subwindow;

import java.awt.HeadlessException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SubWindow extends JFrame {

	public SubWindow() throws HeadlessException {
		super();

		setTitle("SubWindow");
		setSize(300, 100);
	}
}
