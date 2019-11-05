package com.paletter.client.hotdeploy.window.listener.panel;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.util.Map;

import com.paletter.client.hotdeploy.window.component.panel.UploadHtmlPanel;
import com.paletter.client.hotdeploy.window.logic.HtmlUploadLogic;

public class HtmlPrefixTextKeyListener implements KeyListener {

	private UploadHtmlPanel panel;

	public HtmlPrefixTextKeyListener(UploadHtmlPanel panel) {
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

				Map<String, File> targetFileMap = HtmlUploadLogic.findTargetFileMap(panel);
				
				HtmlUploadLogic.upload(panel, targetFileMap);
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
