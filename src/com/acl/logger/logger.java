package com.acl.logger;


import java.io.IOException;
import java.util.logging.*;

public class logger {
	public void getLogger(String msg) {
		Logger l = Logger.getLogger("Log");
		try {
			FileHandler fh = new FileHandler("D:/eclipse-workspace/accessControlLogins/aclLog.log", true);
			l.addHandler(fh);
	        fh.setFormatter(new SimpleFormatter());
	        l.warning(msg);
	        fh.close();
		} catch (SecurityException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
