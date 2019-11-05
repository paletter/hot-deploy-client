package com.paletter.client.hotdeploy.window.listener.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import com.paletter.client.hotdeploy.window.component.ChooseTargetClassWindow;
import com.paletter.client.hotdeploy.window.logic.ClassUploadLogic;

public class ChooseAllClassBtnActionListener implements ActionListener {

	private ChooseTargetClassWindow chooseWindow;
	private List<File> classFileList;
	
	public ChooseAllClassBtnActionListener(ChooseTargetClassWindow chooseWindow, List<File> classFileList) {
		this.chooseWindow = chooseWindow;
		this.classFileList = classFileList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			
			for(File file : classFileList) {
				ClassUploadLogic.upload(chooseWindow.getUploadClassPanel(), file);
			}
			
			chooseWindow.setVisible(false);
			
			chooseWindow.getUploadClassPanel().setConsoleText("All upload Success");
		} catch (Exception e2) {
			chooseWindow.getUploadClassPanel().setConsoleText("Fail. catch e: " + e2.getMessage());
			e2.printStackTrace();
		}
	}

}
