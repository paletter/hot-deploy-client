package com.paletter.client.hotdeploy.window.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;
import com.paletter.client.hotdeploy.window.logic.ClassUploadLogic;

public class UploadBtnActionListener implements ActionListener {

	private HotDeployClientWindow window;
	
	public UploadBtnActionListener(HotDeployClientWindow window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			window.getConsoleText().setText("");
			
			ClassUploadLogic.upload(window);
			
		} catch (Exception e2) {
			e2.printStackTrace();
			window.getConsoleText().setText("Fail. catch e: " + e2.getMessage());
		}
	}
	
}
