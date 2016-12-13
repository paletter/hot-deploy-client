package com.paletter.client.hotdeploy.window.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.List;

import com.paletter.client.hotdeploy.window.component.ChooseTargetClassWindow;
import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;
import com.paletter.client.hotdeploy.window.logic.ClassUploadLogic;

public class ChooseAllClassBtnActionListener implements ActionListener {

	private HotDeployClientWindow mainWindow;
	private ChooseTargetClassWindow chooseWindow;
	private List<File> classFileList;
	
	public ChooseAllClassBtnActionListener(HotDeployClientWindow mainWindow, ChooseTargetClassWindow chooseWindow, List<File> classFileList) {
		this.mainWindow = mainWindow;
		this.chooseWindow = chooseWindow;
		this.classFileList = classFileList;
	}

	@Override
	public void actionPerformed(ActionEvent e) {

		try {
			
			for(File file : classFileList) {
				ClassUploadLogic.upload(mainWindow, file);
			}
			
			chooseWindow.setVisible(false);
			
			mainWindow.getConsoleText().setText("All upload Success");
		} catch (Exception e2) {
			mainWindow.getConsoleText().setText("Fail. catch e: " + e2.getMessage());
			e2.printStackTrace();
		}
	}

}
