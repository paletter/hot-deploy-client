package com.paletter.client.hotdeploy.window.listener.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import com.paletter.client.hotdeploy.window.component.ChooseTargetClassWindow;
import com.paletter.client.hotdeploy.window.component.panel.UploadClassPanel;
import com.paletter.client.hotdeploy.window.logic.ClassUploadLogic;

public class UploadClassBtnActionListener implements ActionListener {

	private UploadClassPanel panel;
	
	public UploadClassBtnActionListener(UploadClassPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			panel.setConsoleText("");
			
			List<File> targetFileList = ClassUploadLogic.findTargetFileList(panel);

			if(targetFileList.size() == 1) {
				ClassUploadLogic.upload(panel, targetFileList.get(0));
			}
			
			if(targetFileList.size() > 1) {
				new ChooseTargetClassWindow(panel, targetFileList);
			}
			
		} catch (Exception e2) {
			panel.setConsoleText("Fail. catch e: " + e2.getMessage());
			e2.printStackTrace();
		}
	}
	
}
