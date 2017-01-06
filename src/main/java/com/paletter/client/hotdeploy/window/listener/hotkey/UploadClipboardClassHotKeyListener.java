package com.paletter.client.hotdeploy.window.listener.hotkey;

import java.io.File;
import java.util.List;

import com.melloware.jintellitype.HotkeyListener;
import com.paletter.client.hotdeploy.window.component.ChooseTargetClassWindow;
import com.paletter.client.hotdeploy.window.component.panel.UploadClassPanel;
import com.paletter.client.hotdeploy.window.logic.ClassUploadLogic;
import com.paletter.client.hotdeploy.window.util.ClipboardUtil;
import com.paletter.client.hotdeploy.window.util.HotDeployComUtil;

public class UploadClipboardClassHotKeyListener implements HotkeyListener {

	public static Integer UPLOAD_FROM_CLIPBOARD_HOTKEY_CODE = 2;
	
	private UploadClassPanel panel;

	public UploadClipboardClassHotKeyListener(UploadClassPanel panel) {
		super();
		this.panel = panel;
	}

	@Override
	public void onHotKey(int identifier) {
		if(identifier == UPLOAD_FROM_CLIPBOARD_HOTKEY_CODE) {
			try {
				
				String clipboardText = ClipboardUtil.getClipboardText();
				
				if(HotDeployComUtil.isNotNullOrEmpty(clipboardText)) {
				
					panel.setConsoleText("");
					panel.getClassNameText().setText(clipboardText);
					
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
				}
				
			} catch (Exception e2) {
				panel.setConsoleText("Fail. catch e: " + e2.getMessage());
				e2.printStackTrace();
			}
		}
	}
	
}
