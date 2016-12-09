package com.paletter.client.hotdeploy.window.listener;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import com.paletter.client.hotdeploy.window.component.ChooseTargetClassWindow;
import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;
import com.paletter.client.hotdeploy.window.logic.ClassUploadLogic;

public class ChooseClassLabelClickListener implements MouseListener {

	private HotDeployClientWindow mainWindow;
	private ChooseTargetClassWindow chooseWindow;
	private File classFile;
	
	public ChooseClassLabelClickListener(HotDeployClientWindow mainWindow, ChooseTargetClassWindow chooseWindow, File classFile) {
		this.mainWindow = mainWindow;
		this.chooseWindow = chooseWindow;
		this.classFile = classFile;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			
			ClassUploadLogic.upload(mainWindow, classFile);
			
			chooseWindow.setVisible(false);
			
		} catch (Exception e2) {
			e2.printStackTrace();
			mainWindow.getConsoleText().setText("Fail. catch e: " + e2.getMessage());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

}
