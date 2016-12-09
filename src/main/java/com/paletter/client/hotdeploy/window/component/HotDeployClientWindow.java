package com.paletter.client.hotdeploy.window.component;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import com.paletter.client.hotdeploy.window.data.dao.DefaultConfDao;
import com.paletter.client.hotdeploy.window.data.dao.SessionDao;
import com.paletter.client.hotdeploy.window.data.entity.DefaultConf;
import com.paletter.client.hotdeploy.window.data.entity.Session;
import com.paletter.client.hotdeploy.window.util.HotDeployComUtil;

public class HotDeployClientWindow extends JFrame {

	private static final long serialVersionUID = -8162188152479325700L;
	
	private Integer WINDOW_WIDTH = 950;
	private Integer WINDOW_HEIGHT = 600;

	private Integer DEFAULT_LABEL_WIDTH = 100;
	private Integer DEFAULT_TEXT_WIDTH = 500;
	private Integer DEFAULT_HEIGHT = 20;
	
	private Integer DEFAULT_Y_GAP = 25;
	private Integer ORIG_Y = 10;
	
	private Integer DEFAULT_LABEL_X = 10;
	private Integer DEFAULT_TEXT_X = 100;
	
	private JTextField classNameText = new JTextField();;
	private JTextField xmlDBPathText = new JTextField();;
	private JTextField sessionText = new JTextField();;
	private JTextField serverHostText = new JTextField();;
	private JTextField rootPathText = new JTextField();;
	private JTextField projectText = new JTextField();;
	
	private JTextField keyPathText = new JTextField();;
	private JTextField keyUserText = new JTextField();;
	private JPasswordField keyPwdText = new JPasswordField();;
	private JTextField uploadPathText = new JTextField();;
	
	private JTextField consoleText = new JTextField();;
	
	private JButton uploadBtn = new JButton("Upload");
	private JButton saveBtn = new JButton("Save");
	private JButton deleteBtn = new JButton("Delete");
	private JButton connTestBtn = new JButton("ConnTest");
	
	private JList<String> sessionListComponent;
	
	public HotDeployClientWindow() throws Exception {
		
		super();
		
		setTitle("HotDeploy by Palatter");
		setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		initTextCompoents();
		
		JPanel panel = new JPanel();
		panel.setLayout(null);
		
		Integer gapRadix = 2;
		
		loadClassNamePart(panel);
		gapRadix = loadSessionConfPart(panel, gapRadix);
		gapRadix = loadConsolePart(panel, gapRadix);
		
		loadSessionList(panel);
		
		add(panel);
	}
	
	private void initTextCompoents() {
		xmlDBPathText = new JTextField("D:/01.ProgramFiles/XmlDB/resource");
		
		try {
			
			DefaultConf defaultConf = DefaultConfDao.queryDefaultConf(xmlDBPathText.getText());
			
			if(defaultConf != null && HotDeployComUtil.isNotNullOrEmpty(defaultConf.getSession())) {
				keyPathText.setText(defaultConf.getKeyPath());
				
				Session session = SessionDao.querySessionByKey(xmlDBPathText.getText(), defaultConf.getSession());
				if(session != null) {
					sessionText.setText(session.getSession());;
					serverHostText.setText(session.getServerHost());
					rootPathText.setText(session.getRootPath());
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
	
	private void loadClassNamePart(JPanel panel) {
		JLabel classNameLabel = new JLabel("ClassName:");
		classNameLabel.setBounds(DEFAULT_LABEL_X, ORIG_Y, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		panel.add(classNameLabel);
		classNameText.setBounds(DEFAULT_TEXT_X, ORIG_Y, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		panel.add(classNameText);
		uploadBtn.setBounds(DEFAULT_TEXT_X + DEFAULT_TEXT_WIDTH + 5, ORIG_Y, 80, DEFAULT_HEIGHT);
		panel.add(uploadBtn);
	}
	
	private Integer loadSessionConfPart(JPanel panel, Integer gapRadix) {
		Integer componentY;

		JLabel xmlDBPathLabel = new JLabel("DBPath:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		xmlDBPathLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		panel.add(xmlDBPathLabel);
		xmlDBPathText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		panel.add(xmlDBPathText);
		
		JLabel sessionConfLabel = new JLabel("SessionConfig:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		sessionConfLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		panel.add(sessionConfLabel);

		JLabel sessionLabel = new JLabel("Session:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		sessionLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		panel.add(sessionLabel);
		sessionText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		panel.add(sessionText);
		
		JLabel rootPathLabel = new JLabel("RootPath:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		rootPathLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		panel.add(rootPathLabel);
		rootPathText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		panel.add(rootPathText);
		
		JLabel projectLabel = new JLabel("Project:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		projectLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		panel.add(projectLabel);
		projectText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		panel.add(projectText);

		JLabel sshLabel = new JLabel("SSH:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		sshLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		panel.add(sshLabel);

		JLabel serverHostLabel = new JLabel("ServerHost:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		serverHostLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		panel.add(serverHostLabel);
		serverHostText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		panel.add(serverHostText);
		
		JLabel keyPathLabel = new JLabel("KeyPath:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		keyPathLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		panel.add(keyPathLabel);
		keyPathText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		panel.add(keyPathText);
		
		JLabel keyUserNameLabel = new JLabel("KeyUser:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		keyUserNameLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		panel.add(keyUserNameLabel);
		keyUserText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		panel.add(keyUserText);
		
		JLabel keyPwdLabel = new JLabel("KeyPwd:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		keyPwdLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		panel.add(keyPwdLabel);
		keyPwdText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		panel.add(keyPwdText);
		
		JLabel uploadPathLabel = new JLabel("UploadPath:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		uploadPathLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		panel.add(uploadPathLabel);
		uploadPathText.setBounds(DEFAULT_TEXT_X, componentY, DEFAULT_TEXT_WIDTH, DEFAULT_HEIGHT);
		panel.add(uploadPathText);
		
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		saveBtn.setBounds(DEFAULT_LABEL_X, componentY, 80, DEFAULT_HEIGHT);
		panel.add(saveBtn);
		deleteBtn.setBounds(DEFAULT_LABEL_X + 100, componentY, 80, DEFAULT_HEIGHT);
		panel.add(deleteBtn);
		connTestBtn.setBounds(DEFAULT_LABEL_X + 200, componentY, 120, DEFAULT_HEIGHT);
		panel.add(connTestBtn);
		
		return gapRadix;
	}
	
	private Integer loadConsolePart(JPanel panel, Integer gapRadix) {
		gapRadix += 1;
		Integer componentY;
		
		JLabel consoleLabel = new JLabel("Console:");
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		consoleLabel.setBounds(DEFAULT_LABEL_X, componentY, DEFAULT_LABEL_WIDTH, DEFAULT_HEIGHT);
		panel.add(consoleLabel);
		
		componentY = ORIG_Y + DEFAULT_Y_GAP * gapRadix ++;
		consoleText.setBounds(DEFAULT_LABEL_X, componentY, 600, 100);
		consoleText.setEditable(false);
		panel.add(consoleText);
		
		return gapRadix;
	}
	
	private void loadSessionList(JPanel panel) {
		sessionListComponent = new JList<String>();
		
		reloadSessionList();
		sessionListComponent.setBounds(700, 10, 200, WINDOW_HEIGHT - 100);
		panel.add(sessionListComponent);
	}
	
	public void reloadSessionList() {
		List<String> sessionIdList = SessionDao.queryAllSessionId(xmlDBPathText.getText());
		String[] sessions = {};
		sessions = sessionIdList.toArray(sessions);
		sessionListComponent.setListData(sessions);
	}

	public JTextField getClassNameText() {
		return classNameText;
	}

	public void setClassNameText(JTextField classNameText) {
		this.classNameText = classNameText;
	}

	public JButton getUploadBtn() {
		return uploadBtn;
	}

	public void setUploadBtn(JButton uploadBtn) {
		this.uploadBtn = uploadBtn;
	}

	public JTextField getRootPathText() {
		return rootPathText;
	}

	public void setRootPathText(JTextField rootPathText) {
		this.rootPathText = rootPathText;
	}

	public JTextField getProjectText() {
		return projectText;
	}

	public void setProjectText(JTextField projectText) {
		this.projectText = projectText;
	}

	public JTextField getUploadPathText() {
		return uploadPathText;
	}

	public void setUploadPathText(JTextField uploadPathText) {
		this.uploadPathText = uploadPathText;
	}

	public JTextField getServerHostText() {
		return serverHostText;
	}

	public void setServerHostText(JTextField serverHostText) {
		this.serverHostText = serverHostText;
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

	public JTextField getKeyPathText() {
		return keyPathText;
	}

	public void setKeyPathText(JTextField keyPathText) {
		this.keyPathText = keyPathText;
	}

	public JTextField getXmlDBPathText() {
		return xmlDBPathText;
	}

	public void setXmlDBPathText(JTextField xmldbPathText) {
		this.xmlDBPathText = xmldbPathText;
	}

	public JTextField getSessionText() {
		return sessionText;
	}

	public void setSessionText(JTextField sessionText) {
		this.sessionText = sessionText;
	}

	public JList<String> getSessionListComponent() {
		return sessionListComponent;
	}

	public void setSessionListComponent(JList<String> sessionListComponent) {
		this.sessionListComponent = sessionListComponent;
	}

	public JButton getDeleteBtn() {
		return deleteBtn;
	}

	public void setDeleteBtn(JButton deleteBtn) {
		this.deleteBtn = deleteBtn;
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

	public JButton getConnTestBtn() {
		return connTestBtn;
	}

	public void setConnTestBtn(JButton connTestBtn) {
		this.connTestBtn = connTestBtn;
	}
}
