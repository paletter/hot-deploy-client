package com.paletter.client.hotdeploy.window.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;
import com.paletter.client.hotdeploy.window.upload.SFTPUtil;

public class ConnTestBtnActionListener implements ActionListener {

	private HotDeployClientWindow window;
	
	public ConnTestBtnActionListener(HotDeployClientWindow window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			
			window.getConsoleText().setText("");
			
			String serverHost = window.getServerHostText().getText();
			String keyPath = window.getKeyPathText().getText();
			String user = window.getKeyUserText().getText();
			String pwd = window.getKeyPwdText().getText();
			
			SFTPUtil.connect(user, keyPath, pwd, serverHost, 22);
			
			window.getConsoleText().setText("Connection Success");
		} catch (Exception e2) {
			e2.printStackTrace();
			window.getConsoleText().setText("Fail. catch e: " + e2.getMessage());
		}
	}

}
