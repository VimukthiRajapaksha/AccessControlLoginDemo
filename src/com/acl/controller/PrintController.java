package com.acl.controller;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.acl.print.PrintToExcel;
import com.acl.print.PrintToPDF;
import com.acl.print.PrintToWord;
import com.itextpdf.text.DocumentException;

import jxl.write.WriteException;

/**
 * Servlet implementation class PrintController
 */
@WebServlet("/secure/PrintController")
public class PrintController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public PrintController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		String type = request.getParameter("type");
		String username = session.getAttribute("uname").toString();
		String limit = request.getParameter("limit");
		String offset = request.getParameter("offset");
		String page = session.getAttribute("page").toString();
		boolean saveResult = false;
		System.out.println(type);
		if(limit==null || Integer.parseInt(limit) <= 0 ) {
			limit = "7";
		}
		if(offset==null || Integer.parseInt(offset) <= 0) {
			offset = "0";
		}
		switch (type) {
		case "e":
			try {
				saveResult = toExcel(limit, offset);
			} catch (NumberFormatException | WriteException | NamingException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case "p":
			try {
				saveResult = toPDF(limit, offset, username);
			} catch (NumberFormatException | DocumentException | NamingException | SQLException e) {
				e.printStackTrace();
			}
			break;
		case "w":
			try {
				saveResult = toWord(limit, offset, username);
			} catch (NumberFormatException | NamingException | SQLException e) {
				e.printStackTrace();
			}
			break;
		default:
			break;
		}
		if(saveResult) {
			request.setAttribute("page", page);
			request.setAttribute("excelResult", true);
			request.setAttribute("limit", limit);
			request.setAttribute("offset", offset);
			request.getRequestDispatcher("employee").forward(request, response);
		}
		else {
			request.setAttribute("page", page);
			request.setAttribute("limit", limit);
			request.setAttribute("offset", offset);
			request.setAttribute("excelResult", false);
			request.getRequestDispatcher("employee").forward(request, response);
		}
		
		/*try {
			excelResult = px.printToExcel(limit, offset);
			if(excelResult) {
				request.setAttribute("page", page);
				request.setAttribute("excelResult", true);
				request.setAttribute("limit", limit);
				request.setAttribute("offset", offset);
				request.getRequestDispatcher("employee").forward(request, response);
			}
			else {
				request.setAttribute("page", page);
				request.setAttribute("limit", limit);
				request.setAttribute("offset", offset);
				request.setAttribute("excelResult", false);
				request.getRequestDispatcher("employee").forward(request, response);
			}
		} catch (NumberFormatException | WriteException | NamingException | SQLException e) {
			System.out.println(e.getClass()+e.getMessage());
		}*/
		
	}
	private boolean toExcel(String limit, String offset) throws NumberFormatException, WriteException, IOException, NamingException, SQLException {
		boolean excelResult = false;
		PrintToExcel px = new PrintToExcel();
		excelResult = px.printToExcel(limit, offset);
		return excelResult;
	}
	private boolean toPDF(String limit, String offset, String username) throws FileNotFoundException, DocumentException, NumberFormatException, NamingException, SQLException {
		boolean pdfResult = false;
		PrintToPDF pd = new PrintToPDF();
		pdfResult = pd.printToPdf(limit, offset, username);
		return pdfResult;
	}
	private boolean toWord(String limit, String offset, String username) throws NumberFormatException, NamingException, SQLException, IOException {
		boolean wordResult = false;
		PrintToWord pw = new PrintToWord();
		wordResult = pw.printToWord(limit, offset, username);
		return wordResult;
	}
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
