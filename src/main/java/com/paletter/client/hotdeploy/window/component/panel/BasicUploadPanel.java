package com.paletter.client.hotdeploy.window.component.panel;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;
import com.paletter.client.hotdeploy.window.data.dao.DefaultConfDao;
import com.paletter.client.hotdeploy.window.data.dao.SessionDao;
import com.paletter.client.hotdeploy.window.data.entity.DefaultConf;
import com.paletter.client.hotdeploy.window.data.entity.Session;
import com.paletter.client.hotdeploy.window.listener.panel.ConnTestBtnActionListener;
import com.paletter.client.hotdeploy.window.listener.panel.DeleteConfBtnActionListener;
import com.paletter.client.hotdeploy.window.listener.panel.SaveConfBtnActionListener;
import com.paletter.client.hotdeploy.window.listener.panel.SessionListSelectListener;
import com.paletter.client.hotdeploy.window.util.HotDeployComUtil;

public class BasicUploadPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	protected Integer DEFAULT_LABEL_WIDTH = 100;
	protected Integer DEFAULT_TEXT_WIDTH = 500;
	protected Integer DEFAULT_HEIGHT = 20;
	
	protected Integer DEFAULT_Y_GAP = 25;
	protected Integer ORIG_Y = 10;
	
	protected Integer DEFAULT_LABEL_X = 10;
	protected Integer DEFAULT_TEXT_X = 100;
	
	protected JTextField xmlDBPathText = new JTextField();
	protected JTextField sessionText = new JTextField();
	protected JTextField serverHostText = new JTextField();
	protected JTextField classRootPathText = new JTextField();
	protected JTextField htmlRootPathText = new JTextField();
	protected JTextField projectText = new JTextField();
	
	protected JTextField keyPathText = new JTextField();
	protected JTextField keyUserText = new JTextField();
	protected JPasswordField keyPwdText = new JPasswordField();
	protected JTextField uploadPathText = new JTextField();
	
	protected JTextField consoleText = new JTextField();
	
	protected JButton saveBtn = new JButton("Save");
	protected JButton deleteBtn = new JButton("Delete");
	protected JButton connTestBtn = new JButton("ConnTest");
	
	protected JList<String> sessionListComponent;
	
	public BasicUploadPanel() {
		
		setLayout(null);
		
		initTextCompoents();
		
		Integer gapRadix = 2;
		
		gapRadix = loadSessionConfPart(gapRadix);
		gapRadix = loadConsolePart(gapRadix);
		
		loadSessionList();
		loadListener();
	}
	
	private void initTextCompoents() {
		xmlDBPathText = new JTextField("D:/Zemp/XmlDB/resource");
		
		try {
			
			DefaultConf defaultConf = DefaultConfDao.queryDefaultConf(xmlDBPathText.getText());
			
			if(defaultConf != null && HotDeployComUtil.isNotNullOrEmpty(defaultConf.getSession())) {
				keyPathText.setText(defaultConf.getKeyPath());
				
				Session session = SessionDao.querySessionByKey(xmlDBPathText.getText(), defaultConf.getSession());
				if(session != null) {
					sessionText.setText(session.getSession());
					serverHostText.setText(session.getServerHost());
					classRootPathText.setText(session.getRootPath());
					htmlRootPathText.setText(session.getHtmlRootPath());
					projectText.setText(session.getProject());
					uploadPathText.setText(session.getUploadPath());
					keyPathText.setText(session.getKeyPath());
					keyUserText.setText(session.getKeyUser());
					keyPwdText.setText(session.getKeyPwd());
				}
			}
			
		} catch (Exception e) {
			e.printStackTrace();
			consoleText.setText("Load DefaultConf fail. catch e: " + e.getMessage());
		}
	}

	private Integer loadSessionConfPart(Integer gapRadix) {
		Integer componentY;

		JLabel xmlDBPathLabel = new JLabel("DBPath:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		xmlDBPathLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		add(xmlDBPathLabel);
		xmlDBPathText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		add(xmlDBPathText);
		
		JLabel sessionConfLabel = new JLabel("SessionConfig:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		sessionConfLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		add(sessionConfLabel);

		JLabel sessionLabel = new JLabel("Session:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		sessionLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		add(sessionLabel);
		sessionText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		add(sessionText);
		
		JLabel classRootPathLabel = new JLabel("ClassRootPath:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		classRootPathLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		add(classRootPathLabel);
		classRootPathText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		add(classRootPathText);
		
		JLabel htmlRootPathLabel = new JLabel("HtmlRootPath:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		htmlRootPathLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		add(htmlRootPathLabel);
		htmlRootPathText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		add(htmlRootPathText);
		
		JLabel projectLabel = new JLabel("Project:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		projectLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		add(projectLabel);
		projectText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		add(projectText);

		JLabel sshLabel = new JLabel("SSH:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		sshLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		add(sshLabel);

		JLabel serverHostLabel = new JLabel("ServerHost:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		serverHostLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		add(serverHostLabel);
		serverHostText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		add(serverHostText);
		
		JLabel keyPathLabel = new JLabel("KeyPath:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		keyPathLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		add(keyPathLabel);
		keyPathText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		add(keyPathText);
		
		JLabel keyUserNameLabel = new JLabel("KeyUser:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		keyUserNameLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		add(keyUserNameLabel);
		keyUserText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		add(keyUserText);
		
		JLabel keyPwdLabel = new JLabel("KeyPwd:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		keyPwdLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		add(keyPwdLabel);
		keyPwdText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		add(keyPwdText);
		
		JLabel uploadPathLabel = new JLabel("UploadPath:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		uploadPathLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		add(uploadPathLabel);
		uploadPathText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		add(uploadPathText);
		
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		saveBtn.setBounds(DEFAULT_LABEL_X, componentY, 80, DEFAULT_HEIGHT);
		add(saveBtn);
		deleteBtn.setBounds(DEFAULT_LABEL_X + 100, componentY, 80, DEFAULT_HEIGHT);
		add(deleteBtn);
		connTestBtn.setBounds(DEFAULT_LABEL_X + 200, componentY, 120, DEFAULT_HEIGHT);
		add(connTestBtn);
		
		return gapRadix;
	}

	private Integer loadConsolePart(Integer gapRadix) {
		gapRadix += 1;
		Integer componentY;
		
		JLabel consoleLabel = new JLabel("Console:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		consoleLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		add(consoleLabel);
		
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		consoleText.setBounds(DEFAULT_LABEL_X, componentY, 600, 100);
		consoleText.setEditable(false);
		add(consoleText);
		
		return gapRadix;
	}
	
	private void loadSessionList() {
		sessionListComponent = new JList<String>();
		
		reloadSessionList();
		sessionListComponent.setBounds(700, 10, 200, HotDeployClientWindow.WINDOW_HEIGHT - 100);
		add(sessionListComponent);
	}
	
	public void reloadSessionList() {
		List<String> sessionIdList = SessionDao.queryAllSessionId(xmlDBPathText.getText());
		String[] sessions = {};
		sessions = sessionIdList.toArray(sessions);
		sessionListComponent.setListData(sessions);
	}

	private void loadListener() {
		saveBtn.addActionListener(new SaveConfBtnActionListener(this));
		deleteBtn.addActionListener(new DeleteConfBtnActionListener(this));
		connTestBtn.addActionListener(new ConnTestBtnActionListener(this));
		
		sessionListComponent.addListSelectionListener(new SessionListSelectListener(this));
	}
	
	public void setConsoleText(String text) {
		consoleText.setText(text);
	}

	public JTextField getXmlDBPathText() {
		return xmlDBPathText;
	}

	public void setXmlDBPathText(JTextField xmlDBPathText) {
		this.xmlDBPathText = xmlDBPathText;
	}

	public JTextField getSessionText() {
		return sessionText;
	}

	public void setSessionText(JTextField sessionText) {
		this.sessionText = sessionText;
	}

	public JTextField getServerHostText() {
		return serverHostText;
	}

	public void setServerHostText(JTextField serverHostText) {
		this.serverHostText = serverHostText;
	}

	public JTextField getClassRootPathText() {
		return classRootPathText;
	}

	public void setClassRootPathText(JTextField rootPathText) {
		this.classRootPathText = rootPathText;
	}

	public JTextField getProjectText() {
		return projectText;
	}

	public void setProjectText(JTextField projectText) {
		this.projectText = projectText;
	}

	public JTextField getKeyPathText() {
		return keyPathText;
	}

	public void setKeyPathText(JTextField keyPathText) {
		this.keyPathText = keyPathText;
	}

	public JTextField getKeyUserText() {
		return keyUserText;
	}

	public void setKeyUserText(JTextField keyUserText) {
		this.keyUserText = keyUserText;
	}

	public JPasswordField getKeyPwdText() {
		return keyPwdText;
	}

	public void setKeyPwdText(JPasswordField keyPwdText) {
		this.keyPwdText = keyPwdText;
	}

	public JTextField getUploadPathText() {
		return uploadPathText;
	}

	public void setUploadPathText(JTextField uploadPathText) {
		this.uploadPathText = uploadPathText;
	}

	public JTextField getConsoleText() {
		return consoleText;
	}

	public void setConsoleText(JTextField consoleText) {
		this.consoleText = consoleText;
	}

	public JButton getSaveBtn() {
		return saveBtn;
	}

	public void setSaveBtn(JButton saveBtn) {
		this.saveBtn = saveBtn;
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

	public void setDeleteBtn(JButton deleteBtn) {
		this.deleteBtn = deleteBtn;
	}

	public JButton getConnTestBtn() {
		return connTestBtn;
	}

	public void setConnTestBtn(JButton connTestBtn) {
		this.connTestBtn = connTestBtn;
	}

	public JList<String> getSessionListComponent() {
		return sessionListComponent;
	}

	public void setSessionListComponent(JList<String> sessionListComponent) {
		this.sessionListComponent = sessionListComponent;
	}

	public JTextField getHtmlRootPathText() {
		return htmlRootPathText;
	}

	public void setHtmlRootPathText(JTextField htmlRootPathText) {
		this.htmlRootPathText = htmlRootPathText;
	}
	
}
