package com.paletter.client.hotdeploy.window.component.panel;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextField;

import com.paletter.client.hotdeploy.window.listener.panel.HtmlPrefixTextKeyListener;
import com.paletter.client.hotdeploy.window.listener.panel.UploadHtmlBtnActionListener;

public class UploadHtmlPanel extends BasicUploadPanel {

	private static final long serialVersionUID = 1L;
	
	private JTextField htmlPrefixText = new JTextField();
	
	private JButton uploadBtn = new JButton("Upload");
	
	public UploadHtmlPanel() {
		
		super();
		
		loadClassNamePart();
		loadListener();
	}
	
	private void loadClassNamePart() {
		JLabel htmlPrefixLabel = new JLabel("Html/Js Prefix:");
		htmlPrefixLabel.setBounds(DEFAULT_LABEL_X, ORIG_Y, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		add(htmlPrefixLabel);
		htmlPrefixText.setBounds(DEFAULT_TEXT_X, ORIG_Y, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		add(htmlPrefixText);
		uploadBtn.setBounds(DEFAULT_TEXT_X + DEFAULT_TEXT_WIDTH + 5, ORIG_Y, 80, DEFAULT_HEIGHT);
		add(uploadBtn);
	}
	
	private void loadListener() {
		uploadBtn.addActionListener(new UploadHtmlBtnActionListener(this));
		htmlPrefixText.addKeyListener(new HtmlPrefixTextKeyListener(this));
	}

	public JTextField getHtmlPrefixText() {
		return htmlPrefixText;
	}

	public void setHtmlPrefixText(JTextField htmlPrefixText) {
		this.htmlPrefixText = htmlPrefixText;
	}

	public JButton getUploadBtn() {
		return uploadBtn;
	}

	public void setUploadBtn(JButton uploadBtn) {
		this.uploadBtn = uploadBtn;
	}

}
