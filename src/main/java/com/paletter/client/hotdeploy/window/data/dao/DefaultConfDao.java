package com.paletter.client.hotdeploy.window.data.dao;

import com.paletter.client.hotdeploy.window.data.entity.DefaultConf;
import com.paletter.xmldb.context.XmlDBContext;
import com.paletter.xmldb.dao.XmlDBDao;
import com.paletter.xmldb.generator.XmlGenerator;
import com.paletter.xmldb.util.XmlDBUtil;

public class DefaultConfDao {

	public static Integer saveDefaultConf(String xmlDBPath, DefaultConf defaultConf) throws Exception {
		XmlDBContext.init(xmlDBPath);

		if(!XmlDBUtil.isXmlExist("default_conf.xml")) {
			XmlGenerator.generateXml("default_conf.xml", DefaultConf.KEY);
		}
		
		return XmlDBDao.save("default_conf.xml", defaultConf, DefaultConf.class);
	}
	
	public static DefaultConf queryDefaultConf(String xmlDBPath) throws Exception {
		XmlDBContext.init(xmlDBPath);
		return XmlDBDao.queryByKey("default_conf.xml", DefaultConf.DEFAULT_ID, DefaultConf.class);
	}
	
}
