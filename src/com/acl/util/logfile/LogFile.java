package com.acl.util.logfile;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

import javax.servlet.http.HttpServletRequest;

public class LogFile {
	public void getLogger(String msg, String type, String username, HttpServletRequest request) {
		Logger l = Logger.getLogger("Log");
		FileHandler fh = null;
		
		try {
			fh = new FileHandler("D:/eclipse-workspace/accessControlLogins/aclLog.log", true);
			l.addHandler(fh);
			fh.setFormatter(new SimpleFormatter());
			if (type.equals("warn")) {
				l.warning(msg + " USERNAME: " + username + " IP ADDRESS: " + request.getRemoteAddr() + "\n");
			} else if (type.equals("info")) {
				l.info(msg + " USERNAME: " + username + " IP ADDRESS: " + request.getRemoteAddr() + "\n");
			}
		} catch (SecurityException | IOException e) {
			e.printStackTrace();
		} finally {
			if (fh != null) {
				fh.close();
			}
		}
	}
}
