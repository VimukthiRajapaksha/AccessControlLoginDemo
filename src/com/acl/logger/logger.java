package com.acl.logger;


import java.io.IOException;
import java.util.logging.*;

import javax.servlet.http.HttpServletRequest;

public class logger {
	public void getLogger(String msg, String type, String username, HttpServletRequest request) {
		Logger l = Logger.getLogger("Log");
		try {
			FileHandler fh = new FileHandler("D:/eclipse-workspace/accessControlLogins/aclLog.log", true);
			l.addHandler(fh);
	        fh.setFormatter(new SimpleFormatter());
	        if(type.equals("warn")) {
	        	l.warning(msg+" USERNAME: "+username+" IP ADDRESS: "+ request.getRemoteAddr()+"\n\n");
	        }
	        else if(type.equals("info")) {
	        	l.info(msg+" USERNAME: "+username+" IP ADDRESS: "+ request.getRemoteAddr()+"\n\n");
	        }
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
