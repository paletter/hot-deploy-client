package com.paletter.client.hotdeploy.window.listener.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.paletter.client.hotdeploy.window.component.panel.BasicUploadPanel;
import com.paletter.client.hotdeploy.window.data.dao.SessionDao;
import com.paletter.xmldb.context.XmlDBContext;

public class DeleteConfBtnActionListener implements ActionListener {

	private BasicUploadPanel panel;

	public DeleteConfBtnActionListener(BasicUploadPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {

			panel.setConsoleText("");
			
			SessionDao.deleteSession(panel.getXmlDBPathText().getText(), panel.getSessionText().getText());
			
			panel.reloadSessionList();
			
			panel.setConsoleText("Success. Save to " + XmlDBContext.getXmlFilePath("session.xml"));
		} catch (Exception e2) {
			panel.setConsoleText("Fail. catch e: " + e2.getMessage());
			e2.printStackTrace();
		}
	}
}
