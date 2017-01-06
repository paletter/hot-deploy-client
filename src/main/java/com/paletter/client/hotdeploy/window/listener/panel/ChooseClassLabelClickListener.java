package com.paletter.client.hotdeploy.window.listener.panel;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;

import com.paletter.client.hotdeploy.window.component.ChooseTargetClassWindow;
import com.paletter.client.hotdeploy.window.logic.ClassUploadLogic;

public class ChooseClassLabelClickListener implements MouseListener {

	private ChooseTargetClassWindow chooseWindow;
	private File classFile;
	
	public ChooseClassLabelClickListener(ChooseTargetClassWindow chooseWindow, File classFile) {
		this.chooseWindow = chooseWindow;
		this.classFile = classFile;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			
			ClassUploadLogic.upload(chooseWindow.getUploadClassPanel(), classFile);
			
			chooseWindow.setVisible(false);
			
		} catch (Exception e2) {
			chooseWindow.getUploadClassPanel().setConsoleText("Fail. catch e: " + e2.getMessage());
			e2.printStackTrace();
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
