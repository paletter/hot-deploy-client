package com.paletter.client.hotdeploy.window.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Properties;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

public class SFTPUtil {

	private static String userName;
	private static String pwd;
	private static String host;
	private static int port;
	
	private static String keyFilePath;
	private static String keyPwd;
	
	private static Session session;
	private static ChannelSftp sftp;

	public static void connect(String userName, String pwd, String host, int port) throws Exception {
		SFTPUtil.userName = userName;
		SFTPUtil.pwd = pwd;
		SFTPUtil.host = host;
		SFTPUtil.port = port;
		
		connect();
	}
	
	public static void connect(String userName, String keyFilePath, String keyPwd, String host, int port) throws Exception {
		SFTPUtil.userName = userName;
		SFTPUtil.keyPwd = keyPwd;
		SFTPUtil.keyFilePath = keyFilePath;
		SFTPUtil.host = host;
		SFTPUtil.port = port;
		
		connect();
	}
	
	private static void connect() throws Exception {
		
		try {
			
			if(sftp != null && sftp.isConnected()) {
				return;
			}
			
			JSch jSch = new JSch();
			
			if(keyFilePath != null) {
				jSch.addIdentity(keyFilePath, keyPwd);
			}
			
			session = jSch.getSession(userName, host, port);
			if(pwd != null) {
				session.setPassword(pwd);
			}
			
			Properties config = new Properties();
			config.put("StrictHostKeyChecking", "no");
			config.put("userauth.gssapi-with-mic", "no");
			session.setConfig(config);
			
			session.connect();
			System.out.println("Session connect");
			
			Channel channel = session.openChannel("sftp");
			channel.connect();
			
			sftp = (ChannelSftp) channel;
			System.out.println("ChannelSftp connect");
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void upload(String targetDir, String targetFileName, String origFilePath) throws Exception {
		try {
			
			try {
				sftp.cd(targetDir);
			} catch (Exception e) {
				sftp.mkdir(targetDir);
				sftp.cd(targetDir);
			}
			
			InputStream is = new FileInputStream(origFilePath);
			sftp.put(is, targetFileName);
			
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void upload(String targetDir, String targetFileName, File origFile) throws Exception {
		InputStream is = null;
		try {
			
			try {
				sftp.cd(targetDir);
			} catch (Exception e) {
				sftp.mkdir(targetDir);
				sftp.cd(targetDir);
			}
			
			is = new FileInputStream(origFile);
			sftp.put(is, targetFileName);
			is.close();
		} finally {
			if(is != null) {
				is.close();
			}
		}
	}
	
	public static void download(String targetDir, String targetFileName, String origFilePath) throws Exception {
		try {
			
			sftp.cd(targetDir);
			
			File file = new File(origFilePath);
			sftp.get(targetFileName, new FileOutputStream(file));
		} catch (Exception e) {
			throw e;
		}
	}
	
	public static void disconnect() {
		if(sftp != null) {
			if(sftp.isConnected()) {
				sftp.disconnect();
				session.disconnect();
			}
		}
	}
	
}
