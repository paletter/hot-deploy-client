package com.paletter.client.hotdeploy.window.component;

import java.io.File;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.paletter.client.hotdeploy.window.component.panel.UploadClassPanel;
import com.paletter.client.hotdeploy.window.listener.panel.ChooseAllClassBtnActionListener;
import com.paletter.client.hotdeploy.window.listener.panel.ChooseClassLabelClickListener;

public class ChooseTargetClassWindow extends JFrame {

	private static final long serialVersionUID = 1L;

	private UploadClassPanel uploadClassPanel;
	private Integer WINDOW_WIDTH = 600;
	
	public ChooseTargetClassWindow(UploadClassPanel uploadClassPanel, List<File> targetFileList) {
		super();
		
		this.uploadClassPanel = uploadClassPanel;
		setTitle("ChooseTargetClass");
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		int windowHeight = 0;
		
		for(int i = 0; i < targetFileList.size(); i ++) {
			File file = targetFileList.get(i);
			
			JLabel fileLabel = new JLabel(file.getPath().substring(file.getPath().indexOf("com\\")));
			fileLabel.setBounds(10, (i + 1) * 20, 1000, 20);
			
			fileLabel.addMouseListener(new ChooseClassLabelClickListener(this, file));
			
			panel.add(fileLabel);
			
			windowHeight += 60;
		}
		
		JButton chooseAllBtn = new JButton("Upload All");
		chooseAllBtn.setBounds(10, (targetFileList.size() + 1) * 20 + 10, 120, 20);
		chooseAllBtn.addActionListener(new ChooseAllClassBtnActionListener(this, targetFileList));
		
		panel.add(chooseAllBtn);
		
		windowHeight += 30;
		
		setSize(WINDOW_WIDTH, windowHeight);
		
		this.add(panel);
		this.setVisible(true);
	}

	public UploadClassPanel getUploadClassPanel() {
		return uploadClassPanel;
	}

	public void setUploadClassPanel(UploadClassPanel uploadClassPanel) {
		this.uploadClassPanel = uploadClassPanel;
	}
	
}
