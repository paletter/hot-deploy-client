package com.paletter.client.hotdeploy.window.data.entity;

public class DefaultConf {

	public static final String DEFAULT_ID = "1";
	public static final String KEY = "id";
	
	private String id;
	private String session;
	private String xmlDBPath;
	private String keyPath;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getSession() {
		return session;
	}

	public void setSession(String session) {
		this.session = session;
	}

	public String getXmlDBPath() {
		return xmlDBPath;
	}

	public void setXmlDBPath(String xmlDBPath) {
		this.xmlDBPath = xmlDBPath;
	}

	public String getKeyPath() {
		return keyPath;
	}

	public void setKeyPath(String keyPath) {
		this.keyPath = keyPath;
	}
	
}
