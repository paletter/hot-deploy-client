package com.paletter.client.hotdeploy.window.logic;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.paletter.client.hotdeploy.window.component.panel.UploadHtmlPanel;
import com.paletter.client.hotdeploy.window.util.HotDeployComUtil;
import com.paletter.client.hotdeploy.window.util.SFTPUtil;

public class HtmlUploadLogic {

	public static Map<String, File> findTargetFileMap(UploadHtmlPanel panel) throws Exception {

		String inputHtmlPrefix = panel.getHtmlPrefixText().getText();
		String htmlRootPath = panel.getHtmlRootPathText().getText();
		
		if(HotDeployComUtil.isNullOrEmpty(htmlRootPath)) {
			panel.setConsoleText("Fail. htmlRootPath is null");
		}
		
		File projectFileDir = new File(htmlRootPath);
		if(!projectFileDir.isDirectory()) {
			panel.setConsoleText("Fail. projectFileDir is dire");
		}
		
		List<File> htmlFileList = new ArrayList<File>();
		findFile(projectFileDir.getPath(), inputHtmlPrefix + ".html", htmlFileList);
		List<File> jsFileList = new ArrayList<File>();
		findFile(projectFileDir.getPath(), inputHtmlPrefix + ".js", jsFileList);
		
		if(htmlFileList.size() == 0 && jsFileList.size() == 0) {
			throw new Exception("html/js file is null");
		}
		
		if(htmlFileList.size() > 1) {
			throw new Exception("htmlFileList.size() > 1");
		}
		
		if(jsFileList.size() > 1) {
			throw new Exception("jsFileList.size() > 1");
		}
		
		Map<String, File> targetFileMap = new HashMap<String, File>();
		if(htmlFileList.size() > 0) {
			targetFileMap.put("html", htmlFileList.get(0));
		}
		
		if(jsFileList.size() > 0) {
			targetFileMap.put("js", jsFileList.get(0));
		}
		
		return targetFileMap;
	}
	
	public static void upload(UploadHtmlPanel panel, Map<String, File> targetFileMap) throws Exception {
		String serverHost = panel.getServerHostText().getText();
		String uploadPath = panel.getUploadPathText().getText();
		String keyPath = panel.getKeyPathText().getText();
		String user = panel.getKeyUserText().getText();
		String pwd = panel.getKeyPwdText().getText();
		
		SFTPUtil.connect(user, keyPath, pwd, serverHost, 22);
		
		String result = "Start upload html!";
		
		File htmlFile = targetFileMap.get("html");
		if(htmlFile != null && htmlFile.isFile()) {
			String htmlFilePath = htmlFile.getPath().replace("\\", "/");
			SFTPUtil.upload(uploadPath + htmlFilePath.substring(htmlFilePath.indexOf("/webapp") + 7, htmlFilePath.lastIndexOf("/") + 1), htmlFile.getName(), htmlFile);
			result += "\n SUCCESS. Upload file:" + htmlFile.getName();
		}
		
		File jsFile = targetFileMap.get("js");
		if(jsFile != null && jsFile.isFile()) {
			String jsFilePath = jsFile.getPath().replace("\\", "/");
			SFTPUtil.upload(uploadPath + jsFilePath.substring(jsFilePath.indexOf("/webapp") + 7, jsFilePath.lastIndexOf("/") + 1), jsFile.getName(), jsFile);
			result += "\n SUCCESS. Upload file:" + jsFile.getName();
		}
		
		panel.setConsoleText(result);
	}
	
	private static void findFile(String dir, String fileName, List<File> targetFileList) {
		File[] files = new File(dir).listFiles();
		
		for(File file : files) {
			
			if(file.isDirectory()) {
				findFile(file.getPath(), fileName, targetFileList);
			}
			
			if(file.isFile()) {
				
				if(file.getName().equals(fileName)) {
					targetFileList.add(file);
				}
			}
		}
	}
	
}
