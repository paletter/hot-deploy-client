package com.paletter.client.hotdeploy.window.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.List;

import com.paletter.client.hotdeploy.window.component.ChooseTargetClassWindow;
import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;
import com.paletter.client.hotdeploy.window.logic.ClassUploadLogic;

public class ClassNameTextKeyListener implements KeyListener {

	private HotDeployClientWindow window;

	public ClassNameTextKeyListener(HotDeployClientWindow window) {
		super();
		this.window = window;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		try {
			
			window.getConsoleText().setText("");
			
			if(e.getKeyCode() == KeyEvent.VK_ENTER) {

				List<File> targetFileList = ClassUploadLogic.findTargetFileList(window);

				if(targetFileList.size() == 1) {
					ClassUploadLogic.upload(window, targetFileList.get(0));
				}
				
				if(targetFileList.size() > 1) {
					new ChooseTargetClassWindow(window, targetFileList);
				}
			}
			
		} catch (Exception e2) {
			window.getConsoleText().setText("Fail. catch e: " + e2.getMessage());
			e2.printStackTrace();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
