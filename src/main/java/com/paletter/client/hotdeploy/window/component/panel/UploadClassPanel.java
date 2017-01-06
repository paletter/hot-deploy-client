package com.paletter.client.hotdeploy.window.component.panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.paletter.client.hotdeploy.window.listener.panel.ClassNameTextKeyListener;
import com.paletter.client.hotdeploy.window.listener.panel.UploadClassBtnActionListener;

public class UploadClassPanel extends BasicUploadPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField classNameText = new JTextField();
	
	private JButton uploadBtn = new JButton("Upload");
	
	public UploadClassPanel() {
		
		super();
		
		loadClassNamePart();
		loadListener();
	}
	
	private void loadClassNamePart() {
		JLabel classNameLabel = new JLabel("ClassName:");
		classNameLabel.setBounds(DEFAULT_LABEL_X, ORIG_Y, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		add(classNameLabel);
		classNameText.setBounds(DEFAULT_TEXT_X, ORIG_Y, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		add(classNameText);
		uploadBtn.setBounds(DEFAULT_TEXT_X + DEFAULT_TEXT_WIDTH + 5, ORIG_Y, 80, DEFAULT_HEIGHT);
		add(uploadBtn);
	}
	
	private void loadListener() {
		uploadBtn.addActionListener(new UploadClassBtnActionListener(this));
		classNameText.addKeyListener(new ClassNameTextKeyListener(this));
	}

	public JTextField getClassNameText() {
		return classNameText;
	}

	public void setClassNameText(JTextField classNameText) {
		this.classNameText = classNameText;
	}

	public JButton getUploadBtn() {
		return uploadBtn;
	}

	public void setUploadBtn(JButton uploadBtn) {
		this.uploadBtn = uploadBtn;
	}
	
}
