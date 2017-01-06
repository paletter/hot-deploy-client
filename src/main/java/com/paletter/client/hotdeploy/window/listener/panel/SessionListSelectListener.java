package com.paletter.client.hotdeploy.window.listener.panel;

import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import com.paletter.client.hotdeploy.window.component.panel.BasicUploadPanel;
import com.paletter.client.hotdeploy.window.data.dao.SessionDao;
import com.paletter.client.hotdeploy.window.data.entity.Session;

public class SessionListSelectListener implements ListSelectionListener {

	private BasicUploadPanel panel;

	public SessionListSelectListener(BasicUploadPanel panel) {
		super();
		this.panel = panel;
	}

	@Override
	public void valueChanged(ListSelectionEvent e) {
		
		try {

			panel.setConsoleText("");
			
			String selectSession = panel.getSessionListComponent().getSelectedValue();
			if(selectSession != null) {
				Session session = SessionDao.querySessionByKey(panel.getXmlDBPathText().getText(), selectSession);
				if(session != null) {
					panel.getSessionText().setText(session.getSession());;
					panel.getServerHostText().setText(session.getServerHost());
					panel.getClassRootPathText().setText(session.getRootPath());
					panel.getHtmlRootPathText().setText(session.getHtmlRootPath());
					panel.getProjectText().setText(session.getProject());
					panel.getUploadPathText().setText(session.getUploadPath());
					panel.getKeyPathText().setText(session.getKeyPath());
					panel.getKeyUserText().setText(session.getKeyUser());
					panel.getKeyPwdText().setText(session.getKeyPwd());
				}
			}
		} catch (Exception e2) {
			panel.setConsoleText("Fail. catch e: " + e2.getMessage());
			e2.printStackTrace();
		}
	}
	
}
