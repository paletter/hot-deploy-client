package com.paletter.client.hotdeploy.window.listener.panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;

import com.paletter.client.hotdeploy.window.component.ChooseTargetClassWindow;
import com.paletter.client.hotdeploy.window.component.panel.UploadClassPanel;
import com.paletter.client.hotdeploy.window.logic.ClassUploadLogic;

public class ClassNameTextKeyListener implements KeyListener {

	private UploadClassPanel panel;

	public ClassNameTextKeyListener(UploadClassPanel panel) {
		super();
		this.panel = panel;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		try {
			
			panel.setConsoleText("");
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {

				List<File> targetFileList = ClassUploadLogic.findTargetFileList(panel);

				if(targetFileList.size() == 1) {
					ClassUploadLogic.upload(panel, targetFileList.get(0));
				}
				
				if(targetFileList.size() > 1) {
					new ChooseTargetClassWindow(panel, targetFileList);
				}
			}
			
		} catch (Exception e2) {
			panel.setConsoleText("Fail. catch e: " + e2.getMessage());
			e2.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
