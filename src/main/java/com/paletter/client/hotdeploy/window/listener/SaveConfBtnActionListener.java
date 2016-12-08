package com.paletter.client.hotdeploy.window.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;
import com.paletter.client.hotdeploy.window.data.dao.SessionDao;
import com.paletter.client.hotdeploy.window.data.entity.Session;
import com.paletter.xmldb.context.XmlDBContext;

public class SaveConfBtnActionListener implements ActionListener {

	private HotDeployClientWindow window;

	public SaveConfBtnActionListener(HotDeployClientWindow window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {

			window.getConsoleText().setText("");
			
			Session session = new Session();
			session.setSession(window.getSessionText().getText());
			session.setServerHost(window.getServerHostText().getText());
			session.setRootPath(window.getRootPathText().getText());
			session.setProject(window.getProjectText().getText());
			session.setUploadPath(window.getUploadPathText().getText());
			session.setKeyPath(window.getKeyPathText().getText());
			session.setKeyUser(window.getKeyUserText().getText());
			session.setKeyPwd(window.getKeyPwdText().getText());
			
			SessionDao.saveSession(window.getXmlDBPathText().getText(), session);
			
			window.reloadSessionList();
			
			window.getConsoleText().setText("Success. Save to " + XmlDBContext.getXmlFilePath("session.xml"));
		} catch (Exception e2) {
			e2.printStackTrace();
			window.getConsoleText().setText("Fail. catch e: " + e2.getMessage());
		}
	}
}
