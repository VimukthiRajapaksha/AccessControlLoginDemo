package com.acl.print;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.naming.NamingException;

import com.acl.bean.UserBean;
import com.acl.dao.EmployeesDao;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;

public class PrintToExcel {
	public boolean printToExcel(String limit, String offset) throws IOException, WriteException, NumberFormatException, NamingException, SQLException {
		WritableWorkbook wworkbook = null;
		ArrayList<UserBean> view = null;
		String tableName = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss").format(new Date());
		String path = "D:/eclipse-workspace/accessControlLogins/print/"+tableName+".xls";
		EmployeesDao ed = new EmployeesDao();
		
		try {
			wworkbook = Workbook.createWorkbook(new File(path));
			// Sheet name
			WritableSheet wsheet = wworkbook.createSheet("User Details", 0);
			int col = 0;
			int row = 0;
			view = ed.getView(Integer.parseInt(limit), Integer.parseInt(offset));
			
			Label label = new Label(col++, row, "user id");
			wsheet.addCell(label);
			label = new Label(col++, row, "username");
			wsheet.addCell(label);
			label = new Label(col++, row, "user role");
			wsheet.addCell(label);
			label = new Label(col++, row, "email");
			wsheet.addCell(label);
			label = new Label(col++, row, "phone");
			wsheet.addCell(label);
			row++;
			col = 0;
			
			for(UserBean bean : view) {
				label = new Label(col++, row, bean.getUserid().toString());
				wsheet.addCell(label);
				label = new Label(col++, row, bean.getUsername().toString());
				wsheet.addCell(label);
				label = new Label(col++, row, bean.getRole_name().toString());
				wsheet.addCell(label);
				label = new Label(col++, row, bean.getEmail().toString());
				wsheet.addCell(label);
				label = new Label(col++, row, bean.getPhone().toString());
				wsheet.addCell(label);
				row++;
				col = 0;
			}
			wworkbook.write();
			return true;
		}finally {
			if(wworkbook!=null) {
				wworkbook.close();
			}
		}
	}
}