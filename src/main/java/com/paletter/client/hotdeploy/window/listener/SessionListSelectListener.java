package com.paletter.client.hotdeploy.window.listener;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;
import com.paletter.client.hotdeploy.window.data.dao.SessionDao;
import com.paletter.client.hotdeploy.window.data.entity.Session;

public class SessionListSelectListener implements ListSelectionListener {

	private HotDeployClientWindow window;

	public SessionListSelectListener(HotDeployClientWindow window) {
		super();
		this.window = window;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		try {

			window.getConsoleText().setText("");
			
			String selectSession = window.getSessionListComponent().getSelectedValue();
			if(selectSession != null) {
				Session session = SessionDao.querySessionByKey(window.getXmlDBPathText().getText(), selectSession);
				if(session != null) {
					window.getSessionText().setText(session.getSession());;
					window.getServerHostText().setText(session.getServerHost());
					window.getRootPathText().setText(session.getRootPath());
					window.getProjectText().setText(session.getProject());
					window.getUploadPathText().setText(session.getUploadPath());
					window.getKeyPathText().setText(session.getKeyPath());
					window.getKeyUserText().setText(session.getKeyUser());
					window.getKeyPwdText().setText(session.getKeyPwd());
				}
			}
		} catch (Exception e2) {
			window.getConsoleText().setText("Fail. catch e: " + e2.getMessage());
			e2.printStackTrace();
		}
	}
	
}
