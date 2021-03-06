package com.paletter.client.hotdeploy.window.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.paletter.client.hotdeploy.window.component.panel.UploadClassPanel;
import com.paletter.client.hotdeploy.window.util.SFTPUtil;

public class ClassUploadLogic {

	public static List<File> findTargetFileList(UploadClassPanel panel) throws Exception {

		String inputClassName = panel.getClassNameText().getText();
		String rootPath = panel.getClassRootPathText().getText();
		
		File projectFileDir = new File(rootPath);
		if(!projectFileDir.isDirectory()) {
			throw new Exception("Fail. projectFileDir is not directory");
		}
		
		List<File> targetFileList = new ArrayList<File>();
		findClassFile(projectFileDir.getPath(), inputClassName, targetFileList);
		
		if(targetFileList.size() == 0) {
			throw new Exception("Fail. origClassFile is null");
		}
		
		return targetFileList;
	}
	
	public static void upload(UploadClassPanel panel, File targetFile) throws Exception {
		String serverHost = panel.getServerHostText().getText();
		String uploadPath = panel.getUploadPathText().getText();
		String keyPath = panel.getKeyPathText().getText();
		String user = panel.getKeyUserText().getText();
		String pwd = panel.getKeyPwdText().getText();
		
		SFTPUtil.connect(user, keyPath, pwd, serverHost, 22);
		String origClassFilePath = targetFile.getPath().replace("\\", "/");
		SFTPUtil.upload(uploadPath + origClassFilePath.substring(origClassFilePath.indexOf("/WEB-INF"), origClassFilePath.lastIndexOf("/") + 1), origClassFilePath.substring(origClassFilePath.lastIndexOf("/") + 1), targetFile);
		
		panel.setConsoleText("SUCCESS. Upload file: " + targetFile.getPath().substring(targetFile.getPath().indexOf("com\\")));
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
		
		String mainFileName = className.split("\\.")[0];
		
		for(File file : files) {
			
			if(file.isDirectory()) {
				findClassFile(file.getPath(), className, targetFileList);
			}
			
			if(file.isFile()) {
				
				if(file.getName().equals(className) || file.getName().indexOf(mainFileName + "$") == 0) {
					targetFileList.add(file);
				}
			}
		}
	}
}
