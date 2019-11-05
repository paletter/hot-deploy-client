package com.paletter.client.hotdeploy.window.listener.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.Map;

import com.paletter.client.hotdeploy.window.component.panel.UploadHtmlPanel;
import com.paletter.client.hotdeploy.window.logic.HtmlUploadLogic;

public class UploadHtmlBtnActionListener implements ActionListener {

	private UploadHtmlPanel panel;
	
	public UploadHtmlBtnActionListener(UploadHtmlPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			panel.setConsoleText("");
			
			Map<String, File> targetFileMap = HtmlUploadLogic.findTargetFileMap(panel);
			
			HtmlUploadLogic.upload(panel, targetFileMap);

		} catch (Exception e2) {
			panel.setConsoleText("Fail. catch e: " + e2.getMessage());
			e2.printStackTrace();
		}
	}
	
}
