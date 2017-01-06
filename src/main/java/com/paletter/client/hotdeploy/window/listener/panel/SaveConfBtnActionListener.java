package com.paletter.client.hotdeploy.window.listener.panel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.paletter.client.hotdeploy.window.component.panel.BasicUploadPanel;
import com.paletter.client.hotdeploy.window.data.dao.SessionDao;
import com.paletter.client.hotdeploy.window.data.entity.Session;
import com.paletter.xmldb.context.XmlDBContext;

public class SaveConfBtnActionListener implements ActionListener {

	private BasicUploadPanel panel;

	public SaveConfBtnActionListener(BasicUploadPanel panel) {
		this.panel = panel;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {

			panel.setConsoleText("");
			
			Session session = new Session();
			session.setSession(panel.getSessionText().getText());
			session.setServerHost(panel.getServerHostText().getText());
			session.setRootPath(panel.getClassRootPathText().getText());
			session.setHtmlRootPath(panel.getHtmlRootPathText().getText());
			session.setProject(panel.getProjectText().getText());
			session.setUploadPath(panel.getUploadPathText().getText());
			session.setKeyPath(panel.getKeyPathText().getText());
			session.setKeyUser(panel.getKeyUserText().getText());
			session.setKeyPwd(panel.getKeyPwdText().getText());
			
			SessionDao.saveSession(panel.getXmlDBPathText().getText(), session);
			
			panel.reloadSessionList();
			
			panel.setConsoleText("Success. Save to " + XmlDBContext.getXmlFilePath("session.xml"));
		} catch (Exception e2) {
			panel.setConsoleText("Fail. catch e: " + e2.getMessage());
			e2.printStackTrace();
		}
	}
}
