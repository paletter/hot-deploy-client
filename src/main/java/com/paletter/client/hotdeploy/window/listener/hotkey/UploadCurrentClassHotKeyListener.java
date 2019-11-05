package com.paletter.client.hotdeploy.window.listener.hotkey;

import java.io.File;
import java.util.List;

import com.melloware.jintellitype.HotkeyListener;
import com.paletter.client.hotdeploy.window.component.ChooseTargetClassWindow;
import com.paletter.client.hotdeploy.window.component.panel.UploadClassPanel;
import com.paletter.client.hotdeploy.window.logic.ClassUploadLogic;

public class UploadCurrentClassHotKeyListener implements HotkeyListener {

	public static Integer UPLOAD_CURRENT_CLASS_HOTKEY_CODE = 3;
	
	private UploadClassPanel panel;

	public UploadCurrentClassHotKeyListener(UploadClassPanel panel) {
		super();
		this.panel = panel;
	}

	@Override
	public void onHotKey(int identifier) {
		if(identifier == UPLOAD_CURRENT_CLASS_HOTKEY_CODE) {
			try {
				
				panel.setConsoleText("");
				
				List<File> targetFileList = ClassUploadLogic.findTargetFileList(panel);

				if(targetFileList.size() == 1) {
					ClassUploadLogic.upload(panel, targetFileList.get(0));
				}
				
				if(targetFileList.size() > 1) {
					new ChooseTargetClassWindow(panel, targetFileList);
				}
				
				panel.setVisible(false);
				panel.setVisible(true);
				panel.getClassNameText().requestFocus();
			} catch (Exception e2) {
				panel.setConsoleText("Fail. catch e: " + e2.getMessage());
				e2.printStackTrace();
			}
		}
	}
	
}
