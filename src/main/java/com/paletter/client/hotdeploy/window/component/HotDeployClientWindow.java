package com.paletter.client.hotdeploy.window.component;

import javax.swing.JFrame;
import javax.swing.JTabbedPane;

import com.melloware.jintellitype.JIntellitype;
import com.melloware.jintellitype.JIntellitype64;
import com.paletter.client.hotdeploy.window.component.panel.UploadClassPanel;
import com.paletter.client.hotdeploy.window.component.panel.UploadHtmlPanel;
import com.paletter.client.hotdeploy.window.listener.hotkey.UploadClipboardClassHotKeyListener;
import com.paletter.client.hotdeploy.window.listener.hotkey.UploadCurrentClassHotKeyListener;
import com.paletter.client.hotdeploy.window.listener.hotkey.UploadCurrentHtmlHotKeyListener;
import com.paletter.client.hotdeploy.window.listener.hotkey.WakeHotkeyListener;
import com.paletter.client.hotdeploy.window.listener.window.HotDeployWindowListener;

public class HotDeployClientWindow extends JFrame {

	private static final long serialVersionUID = -8162188152479325700L;
	
	public static Integer WINDOW_WIDTH = 950;
	public static Integer WINDOW_HEIGHT = 630;
	
	public UploadClassPanel uploadClassPanel;
	public UploadHtmlPanel uploadHtmlPanel;

	public HotDeployClientWindow() throws Exception {
		
		super();
		
		setTitle("HotDeploy by Palatter");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JTabbedPane tabbedPanel = new JTabbedPane(JTabbedPane.TOP);
		
		uploadClassPanel = new UploadClassPanel();
		tabbedPanel.add("Class", uploadClassPanel);
		
		uploadHtmlPanel = new UploadHtmlPanel();
		tabbedPanel.add("Html", uploadHtmlPanel);
		
		add(tabbedPanel);
		
		loadListener();
	}
	
	private void loadListener() {
		addWindowListener(new HotDeployWindowListener(this));
		
		JIntellitype64.getInstance().registerHotKey(WakeHotkeyListener.WAKE_HOTKEY_CODE, JIntellitype.MOD_ALT, (int) 'H');
		JIntellitype64.getInstance().addHotKeyListener(new WakeHotkeyListener(this));
		
		JIntellitype64.getInstance().registerHotKey(UploadClipboardClassHotKeyListener.UPLOAD_FROM_CLIPBOARD_HOTKEY_CODE, JIntellitype.MOD_ALT, (int) 'U');
		JIntellitype64.getInstance().addHotKeyListener(new UploadClipboardClassHotKeyListener(this.uploadClassPanel));
		
		JIntellitype64.getInstance().registerHotKey(UploadCurrentClassHotKeyListener.UPLOAD_CURRENT_CLASS_HOTKEY_CODE, JIntellitype.MOD_ALT, (int) '1');
		JIntellitype64.getInstance().addHotKeyListener(new UploadCurrentClassHotKeyListener(this.uploadClassPanel));
		
		JIntellitype64.getInstance().registerHotKey(UploadCurrentHtmlHotKeyListener.UPLOAD_CURRENT_HTML_HOTKEY_CODE, JIntellitype.MOD_ALT, (int) '2');
		JIntellitype64.getInstance().addHotKeyListener(new UploadCurrentHtmlHotKeyListener(this.uploadHtmlPanel));
	}
	
	public void setConsoleText(String text) {
		uploadClassPanel.getConsoleText().setText(text);
	}
}
