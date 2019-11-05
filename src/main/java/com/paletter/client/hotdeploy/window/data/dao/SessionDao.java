package com.paletter.client.hotdeploy.window.data.dao;

import java.util.ArrayList;
import java.util.List;

import com.paletter.client.hotdeploy.window.data.entity.Session;
import com.paletter.xmldb.context.XmlDBContext;
import com.paletter.xmldb.dao.XmlDBDao;
import com.paletter.xmldb.generator.XmlGenerator;
import com.paletter.xmldb.util.XmlDBUtil;

public class SessionDao {

	public static Integer saveSession(String xmlDBPath, Session session) throws Exception {
		XmlDBContext.init(xmlDBPath);
		
		if(!XmlDBUtil.isXmlExist("session.xml")) {
			XmlGenerator.generateXml("session.xml", Session.KEY);
		}
		
		return XmlDBDao.save("session.xml", session, Session.class);
	}
	
	public static Integer deleteSession(String xmlDBPath, String session) throws Exception {
		XmlDBContext.init(xmlDBPath);
		return XmlDBDao.delete("session.xml", session);
	}
	
	public static List<Session> queryAllSession(String xmlDBPath) {
		XmlDBContext.init(xmlDBPath);
		
		List<Session> resultList = XmlDBDao.queryAll("session.xml", Session.class);
		return resultList;
	}
	
	public static List<String> queryAllSessionId(String xmlDBPath) {
		XmlDBContext.init(xmlDBPath);
		
		List<Session> sessionList = XmlDBDao.queryAll("session.xml", Session.class);
		
		List<String> sessionIdList = new ArrayList<String>();
		for(Session session : sessionList) {
			sessionIdList.add(session.getSession());
		}
		
		return sessionIdList;
	}
	
	public static Session querySessionByKey(String xmlDBPath, String key) {
		XmlDBContext.init(xmlDBPath);
		
		Session sessionQuery = new Session();
		sessionQuery.setSession(key);
		Session session = XmlDBDao.queryByKey("session.xml", sessionQuery, Session.class);
		return session;
	}
}
