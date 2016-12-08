package com.paletter.client.hotdeploy.window.logic;

import java.io.File;

import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;
import com.paletter.client.hotdeploy.window.upload.SFTPUtil;

public class ClassUploadLogic {

	public static void upload(HotDeployClientWindow window) throws Exception {

		String inputClassName = window.getClassNameText().getText();
		String rootPath = window.getRootPathText().getText();
		String project = window.getProjectText().getText();
		
		File projectFileDir = findProjectFileDir(rootPath, project);
		if(projectFileDir == null) {
			window.getConsoleText().setText("Fail. projectFileDir is null");
		}
		
		File origClassFile = findClassFile(projectFileDir.getPath(), inputClassName);
		
		if(origClassFile == null) {
			window.getConsoleText().setText("Fail. origClassFile is null");
		}
		
		if(origClassFile != null) {
			String serverHost = window.getServerHostText().getText();
			String uploadPath = window.getUploadPathText().getText();
			String keyPath = window.getKeyPathText().getText();
			String user = window.getKeyUserText().getText();
			String pwd = window.getKeyPwdText().getText();
			
			SFTPUtil.connect(user, keyPath, pwd, serverHost, 22);
			String origClassFilePath = origClassFile.getPath().replace("\\", "/");
			SFTPUtil.upload(uploadPath + origClassFilePath.substring(origClassFilePath.indexOf("/WEB-INF"), origClassFilePath.lastIndexOf("/") + 1), origClassFilePath.substring(origClassFilePath.lastIndexOf("/") + 1), origClassFile);
			
			window.getConsoleText().setText("SUCCESS. Upload file: " + origClassFile.getName());
		}
	}
	
	private static File findProjectFileDir(String dir, String project) {
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
	
	private static File findClassFile(String dir, String className) {
		File[] files = new File(dir).listFiles();
		
		if(className.indexOf(".class") < 0) {
			className = className + ".class";
		}
		
		for(File file : files) {
			
			if(file.isDirectory()) {
				File targetFile = findClassFile(file.getPath(), className);
				if(targetFile != null) {
					return targetFile;
				}
			}
			
			if(file.isFile()) {
				
				if(file.getName().equals(className)) {
					return file;
				}
			}
		}
		
		return null;
	}
}
