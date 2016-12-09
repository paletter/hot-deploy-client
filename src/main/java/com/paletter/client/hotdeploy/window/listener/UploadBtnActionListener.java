package com.paletter.client.hotdeploy.window.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import com.paletter.client.hotdeploy.window.component.ChooseTargetClassWindow;
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
			
			List<File> targetFileList = ClassUploadLogic.findTargetFileList(window);

			if(targetFileList.size() == 1) {
				ClassUploadLogic.upload(window, targetFileList.get(0));
			}
			
			if(targetFileList.size() > 1) {
				new ChooseTargetClassWindow(window, targetFileList);
			}
			
		} catch (Exception e2) {
			window.getConsoleText().setText("Fail. catch e: " + e2.getMessage());
			e2.printStackTrace();
		}
	}
	
}
