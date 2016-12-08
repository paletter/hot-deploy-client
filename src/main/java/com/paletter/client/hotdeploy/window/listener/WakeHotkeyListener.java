package com.paletter.client.hotdeploy.window.listener;

import com.melloware.jintellitype.HotkeyListener;
import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;

public class WakeHotkeyListener implements HotkeyListener {

	public static Integer WAKE_HOTKEY_CODE = 1;
	
	private HotDeployClientWindow window;

	public WakeHotkeyListener(HotDeployClientWindow window) {
		super();
		this.window = window;
	}

	@Override
	public void onHotKey(int identifier) {
		if(identifier == WAKE_HOTKEY_CODE) {
			window.setVisible(true);
			window.getClassNameText().requestFocus();
		}
	}
	
}
