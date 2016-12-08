package com.paletter.client.hotdeploy.window.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;
import com.paletter.client.hotdeploy.window.data.dao.SessionDao;
import com.paletter.xmldb.context.XmlDBContext;

public class DeleteConfBtnActionListener implements ActionListener {

	private HotDeployClientWindow window;

	public DeleteConfBtnActionListener(HotDeployClientWindow window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {

			window.getConsoleText().setText("");
			
			SessionDao.deleteSession(window.getXmlDBPathText().getText(), window.getSessionText().getText());
			
			window.reloadSessionList();
			
			window.getConsoleText().setText("Success. Save to " + XmlDBContext.getXmlFilePath("session.xml"));
		} catch (Exception e2) {
			e2.printStackTrace();
			window.getConsoleText().setText("Fail. catch e: " + e2.getMessage());
		}
	}
}
