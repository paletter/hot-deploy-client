package com.paletter.client.hotdeploy.window.component;

import java.io.File;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.paletter.client.hotdeploy.window.listener.ChooseClassLabelClickListener;

public class ChooseTargetClassWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private HotDeployClientWindow window;
	private Integer WINDOW_WIDTH = 600;
	
	public ChooseTargetClassWindow(HotDeployClientWindow window, List<File> targetFileList) {
		super();
		
		this.window = window;
		setTitle("ChooseTargetClass");
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		int windowHeight = 0;
		
		for(int i = 0; i < targetFileList.size(); i ++) {
			File file = targetFileList.get(i);
			
			JLabel fileLabel = new JLabel(file.getPath().substring(file.getPath().indexOf("com\\")));
			fileLabel.setBounds(10, (i + 1) * 20, 1000, 20);
			
			fileLabel.addMouseListener(new ChooseClassLabelClickListener(window, this, file));
			
			panel.add(fileLabel);
			
			windowHeight += 60;
		}
		
		setSize(WINDOW_WIDTH, windowHeight);
		
		this.add(panel);
		this.setVisible(true);
	}
}
