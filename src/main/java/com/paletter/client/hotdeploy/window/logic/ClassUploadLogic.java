package com.paletter.client.hotdeploy.window.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.paletter.client.hotdeploy.window.component.HotDeployClientWindow;
import com.paletter.client.hotdeploy.window.upload.SFTPUtil;

public class ClassUploadLogic {

	public static List<File> findTargetFileList(HotDeployClientWindow window) throws Exception {

		String inputClassName = window.getClassNameText().getText();
		String rootPath = window.getRootPathText().getText();
		String project = window.getProjectText().getText();
		
		File projectFileDir = findProjectFileDir(rootPath, project);
		if(projectFileDir == null) {
			window.getConsoleText().setText("Fail. projectFileDir is null");
		}
		
		List<File> targetFileList = new ArrayList<File>();
		findClassFile(projectFileDir.getPath(), inputClassName, targetFileList);
		
		if(targetFileList.size() == 0) {
			window.getConsoleText().setText("Fail. origClassFile is null");
		}
		
		return targetFileList;
	}
	
	public static void upload(HotDeployClientWindow window, File targetFile) throws Exception {
		String serverHost = window.getServerHostText().getText();
		String uploadPath = window.getUploadPathText().getText();
		String keyPath = window.getKeyPathText().getText();
		String user = window.getKeyUserText().getText();
		String pwd = window.getKeyPwdText().getText();
		
		SFTPUtil.connect(user, keyPath, pwd, serverHost, 22);
		String origClassFilePath = targetFile.getPath().replace("\\", "/");
		SFTPUtil.upload(uploadPath + origClassFilePath.substring(origClassFilePath.indexOf("/WEB-INF"), origClassFilePath.lastIndexOf("/") + 1), origClassFilePath.substring(origClassFilePath.lastIndexOf("/") + 1), targetFile);
		
		window.getConsoleText().setText("SUCCESS. Upload file: " + targetFile.getPath().substring(targetFile.getPath().indexOf("com\\")));
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
	

	private static void findClassFile(String dir, String className, List<File> targetFileList) {
		File[] files = new File(dir).listFiles();
		
		if(className.indexOf(".class") < 0) {
			className = className + ".class";
		}
		
		for(File file : files) {
			
			if(file.isDirectory()) {
				findClassFile(file.getPath(), className, targetFileList);
			}
			
			if(file.isFile()) {
				
				if(file.getName().equals(className)) {
					targetFileList.add(file);
				}
			}
		}
	}
}
