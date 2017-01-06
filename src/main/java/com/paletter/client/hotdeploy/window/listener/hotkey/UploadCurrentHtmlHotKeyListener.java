package com.paletter.client.hotdeploy.window.listener.hotkey;

import java.io.File;
import java.util.Map;

import com.melloware.jintellitype.HotkeyListener;
import com.paletter.client.hotdeploy.window.component.panel.UploadHtmlPanel;
import com.paletter.client.hotdeploy.window.logic.HtmlUploadLogic;

public class UploadCurrentHtmlHotKeyListener implements HotkeyListener {

	public static Integer UPLOAD_CURRENT_HTML_HOTKEY_CODE = 4;
	
	private UploadHtmlPanel panel;

	public UploadCurrentHtmlHotKeyListener(UploadHtmlPanel panel) {
		super();
		this.panel = panel;
	}

	@Override
	public void onHotKey(int identifier) {
		if(identifier == UPLOAD_CURRENT_HTML_HOTKEY_CODE) {
			try {
				
				panel.setConsoleText("");
				
				Map<String, File> targetFileMap = HtmlUploadLogic.findTargetFileMap(panel);
				
				HtmlUploadLogic.upload(panel, targetFileMap);
				
				panel.setVisible(false);
				panel.setVisible(true);
				panel.getHtmlPrefixText().requestFocus();
			} catch (Exception e2) {
				panel.setVisible(true);
				panel.setConsoleText("Fail. catch e: " + e2.getMessage());
				e2.printStackTrace();
			}
		}
	}
	
}
