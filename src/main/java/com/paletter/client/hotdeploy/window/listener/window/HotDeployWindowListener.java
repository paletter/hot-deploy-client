package com.paletter.client.hotdeploy.window.listener.window;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;
import com.paletter.client.hotdeploy.window.data.dao.DefaultConfDao;
import com.paletter.client.hotdeploy.window.data.entity.DefaultConf;
import com.paletter.client.hotdeploy.window.util.HotDeployComUtil;

public class HotDeployWindowListener extends WindowAdapter {

	private HotDeployClientWindow window;

	public HotDeployWindowListener(HotDeployClientWindow window) {
		super();
		this.window = window;
	}
	
	@Override
	public void windowClosing(WindowEvent e) {
		
		try {

			window.setConsoleText("");

			DefaultConf defaultConf = new DefaultConf();
			defaultConf.setId(DefaultConf.DEFAULT_ID);
			defaultConf.setKeyPath(window.uploadClassPanel.getKeyPathText().getText());
			if(HotDeployComUtil.isNotNullOrEmpty(window.uploadClassPanel.getSessionListComponent().getSelectedValue())) {
				defaultConf.setSession(window.uploadClassPanel.getSessionListComponent().getSelectedValue());
			} else if(HotDeployComUtil.isNotNullOrEmpty(window.uploadClassPanel.getSessionText().getText())) {
				defaultConf.setSession(window.uploadClassPanel.getSessionText().getText());
			}
			
			DefaultConfDao.saveDefaultConf(window.uploadClassPanel.getXmlDBPathText().getText(), defaultConf);
			
			System.exit(0);
		} catch (Exception e2) {
			window.setConsoleText("Fail. catch e: " + e2.getMessage());
			e2.printStackTrace();
		}
	}

	public HotDeployClientWindow getWindow() {
		return window;
	}

	public void setWindow(HotDeployClientWindow window) {
		this.window = window;
	}
	
}
