package com.paletter.client.hotdeploy.window.listener.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.paletter.client.hotdeploy.window.component.panel.BasicUploadPanel;
import com.paletter.client.hotdeploy.window.util.SFTPUtil;

public class ConnTestBtnActionListener implements ActionListener {

	private BasicUploadPanel panel;
	
	public ConnTestBtnActionListener(BasicUploadPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			
			panel.setConsoleText("");
			
			String serverHost = panel.getServerHostText().getText();
			String keyPath = panel.getKeyPathText().getText();
			String user = panel.getKeyUserText().getText();
			String pwd = panel.getKeyPwdText().getText();
			
			SFTPUtil.connect(user, keyPath, pwd, serverHost, 22);
			
			panel.setConsoleText("Connection Success");
		} catch (Exception e2) {
			panel.setConsoleText("Fail. catch e: " + e2.getMessage());
			e2.printStackTrace();
		}
	}

}
