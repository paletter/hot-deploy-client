package com.paletter.client.hotdeploy.window.listener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

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
				ClassUploadLogic.upload(window);
			}
			
		} catch (Exception e2) {
			e2.printStackTrace();
			window.getConsoleText().setText("Fail. catch e: " + e2.getMessage());
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		
	}
	
}
