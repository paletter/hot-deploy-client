package com.paletter.client.hotdeploy.window.listener;

import java.io.File;
import java.util.List;

import com.melloware.jintellitype.HotkeyListener;
import com.paletter.client.hotdeploy.window.component.ChooseTargetClassWindow;
import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;
import com.paletter.client.hotdeploy.window.logic.ClassUploadLogic;
import com.paletter.client.hotdeploy.window.util.ClipboardUtil;
import com.paletter.client.hotdeploy.window.util.HotDeployComUtil;

public class UploadClipboardHotKeyListener implements HotkeyListener {

	public static Integer UPLOAD_FROM_CLIPBOARD_HOTKEY_CODE = 2;
	
	private HotDeployClientWindow window;

	public UploadClipboardHotKeyListener(HotDeployClientWindow window) {
		super();
		this.window = window;
	}

	@Override
	public void onHotKey(int identifier) {
		if(identifier == UPLOAD_FROM_CLIPBOARD_HOTKEY_CODE) {
			try {
				
				String clipboardText = ClipboardUtil.getClipboardText();
				
				if(HotDeployComUtil.isNotNullOrEmpty(clipboardText)) {
				
					window.getConsoleText().setText("");
					window.getClassNameText().setText(clipboardText);
					
					List<File> targetFileList = ClassUploadLogic.findTargetFileList(window);
	
					if(targetFileList.size() == 1) {
						ClassUploadLogic.upload(window, targetFileList.get(0));
					}
					
					if(targetFileList.size() > 1) {
						new ChooseTargetClassWindow(window, targetFileList);
					}
					
					window.setVisible(false);
					window.setVisible(true);
					window.getClassNameText().requestFocus();
				}
				
			} catch (Exception e2) {
				window.getConsoleText().setText("Fail. catch e: " + e2.getMessage());
				e2.printStackTrace();
			}
		}
	}
	
}
