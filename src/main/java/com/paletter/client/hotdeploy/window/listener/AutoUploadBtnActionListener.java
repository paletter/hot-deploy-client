package com.paletter.client.hotdeploy.window.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;
import com.paletter.client.hotdeploy.window.upload.SFTPUtil;

public class AutoUploadBtnActionListener implements ActionListener {

	private HotDeployClientWindow window;
	
	public AutoUploadBtnActionListener(HotDeployClientWindow window) {
		this.window = window;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		try {
			
			window.getConsoleText().setText("");
			
			String rootPath = window.getRootPathText().getText();
			String project = window.getProjectText().getText();
			
			File projectFileDir = findProjectFileDir(rootPath, project);
			List<File> origClassFileList = findNewestClassFileList(projectFileDir.getPath());
			
			String msg = "";
			for(File origClassFile : origClassFileList) {
				String serverHost = window.getServerHostText().getText();
				String uploadPath = window.getUploadPathText().getText();
				String keyPath = window.getKeyPathText().getText();
				String user = window.getKeyUserText().getText();
				String pwd = window.getKeyPwdText().getText();
				
				SFTPUtil.connect(user, keyPath, pwd, serverHost, 22);
				String origClassFilePath = origClassFile.getPath().replace("\\", "/");
				SFTPUtil.upload(uploadPath + origClassFilePath.substring(origClassFilePath.indexOf("/WEB-INF"), origClassFilePath.lastIndexOf("/") + 1), origClassFilePath.substring(origClassFilePath.lastIndexOf("/") + 1), origClassFile);
				
				msg += origClassFile.getName() + ",";
			}
			
			window.getConsoleText().setText("Upload files: " + msg);
			
		} catch (Exception e2) {
			e2.printStackTrace();
			window.getConsoleText().setText("Fail. catch e: " + e2.getMessage());
		}
	}
	
	private File findProjectFileDir(String dir, String project) {
		File[] files = new File(dir).listFiles();
		
		for(File file : files) {
			
			if(file.isDirectory()) {
				if(file.getName().equals(project) && file.getPath().indexOf("wtpwebapps") > 0) {
					return file;
				}
				
				File targetFile = findProjectFileDir(file.getPath(), project);
				if(targetFile != null) {
					return targetFile;
				}
			}
		}
		
		return null;
	}
	
	private List<File> findNewestClassFileList(String dir) {
		List<File> newestFileList = new ArrayList<>();
		
		File[] files = new File(dir).listFiles();
		for(File file : files) {
			
			if(file.isDirectory()) {
				List<File> tmpFileList = findNewestClassFileList(file.getPath());
				newestFileList.addAll(tmpFileList);
			}
			
			if(file.isFile()) {
				if(file.getName().indexOf(".class") > 0) {
					if((System.currentTimeMillis() - file.lastModified()) / 1000 <= 30) {
						newestFileList.add(file);
					}
				}
			}
		}
		
		return newestFileList;
	}
}
