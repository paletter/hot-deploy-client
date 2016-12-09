package com.paletter.client.hotdeploy.window;

import com.melloware.jintellitype.JIntellitype;
import com.melloware.jintellitype.JIntellitype64;
import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;
import com.paletter.client.hotdeploy.window.listener.ClassNameTextKeyListener;
import com.paletter.client.hotdeploy.window.listener.ConnTestBtnActionListener;
import com.paletter.client.hotdeploy.window.listener.DeleteConfBtnActionListener;
import com.paletter.client.hotdeploy.window.listener.HotDeployWindowListener;
import com.paletter.client.hotdeploy.window.listener.SaveConfBtnActionListener;
import com.paletter.client.hotdeploy.window.listener.SessionListSelectListener;
import com.paletter.client.hotdeploy.window.listener.UploadBtnActionListener;
import com.paletter.client.hotdeploy.window.listener.UploadClipboardHotKeyListener;
import com.paletter.client.hotdeploy.window.listener.WakeHotkeyListener;


public class ShowWindow {
	
	public static void main(String[] args) {
		
		try {
			
			HotDeployClientWindow window = new HotDeployClientWindow();
			
			window.getUploadBtn().addActionListener(new UploadBtnActionListener(window));
			window.getSaveBtn().addActionListener(new SaveConfBtnActionListener(window));
			window.getDeleteBtn().addActionListener(new DeleteConfBtnActionListener(window));
			window.getConnTestBtn().addActionListener(new ConnTestBtnActionListener(window));
			
			window.getSessionListComponent().addListSelectionListener(new SessionListSelectListener(window));
			
			window.getClassNameText().addKeyListener(new ClassNameTextKeyListener(window));
			
			window.addWindowListener(new HotDeployWindowListener(window));
			
			JIntellitype64.getInstance().registerHotKey(WakeHotkeyListener.WAKE_HOTKEY_CODE, JIntellitype.MOD_ALT, (int) 'H');
			JIntellitype64.getInstance().addHotKeyListener(new WakeHotkeyListener(window));
			
			JIntellitype64.getInstance().registerHotKey(UploadClipboardHotKeyListener.UPLOAD_FROM_CLIPBOARD_HOTKEY_CODE, JIntellitype.MOD_ALT, (int) 'U');
			JIntellitype64.getInstance().addHotKeyListener(new UploadClipboardHotKeyListener(window));
			
			window.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
