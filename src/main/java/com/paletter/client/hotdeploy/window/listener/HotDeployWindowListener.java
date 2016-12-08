package com.paletter.client.hotdeploy.window.listener;

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

			window.getConsoleText().setText("");

			DefaultConf defaultConf = new DefaultConf();
			defaultConf.setId(DefaultConf.DEFAULT_ID);
			defaultConf.setKeyPath(window.getKeyPathText().getText());
			if(HotDeployComUtil.isNotNullOrEmpty(window.getSessionListComponent().getSelectedValue())) {
				defaultConf.setSession(window.getSessionListComponent().getSelectedValue());
			} else if(HotDeployComUtil.isNotNullOrEmpty(window.getSessionText().getText())) {
				defaultConf.setSession(window.getSessionText().getText());
			}
			
			DefaultConfDao.saveDefaultConf(window.getXmlDBPathText().getText(), defaultConf);
			
			System.exit(0);
		} catch (Exception e2) {
			e2.printStackTrace();
			window.getConsoleText().setText("Fail. catch e: " + e2.getMessage());
		}
	}

	public HotDeployClientWindow getWindow() {
		return window;
	}

	public void setWindow(HotDeployClientWindow window) {
		this.window = window;
	}
	
}
