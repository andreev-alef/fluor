package ru.miacn.report;

import java.io.IOException;
import java.sql.SQLException;

import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.jasperreports.engine.JRException;

public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 9063662138458031340L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			HtmlReport rep;
			String id = (req.getParameter("id") != null) ? req.getParameter("id") : "";
			
			switch (id) {
			case "1":
				rep = new ReportSocGroup();
				break;
			case "2":
				rep = new ReportMedGroup();
				break;
			case "3":
				rep = new ReportDecrGroup();
				break;
			default:
				throw new ServletException("Specify 'id' parameter with value 1, 2 or 3.");
			}
			
			rep.printReport(resp.getOutputStream());
		} catch (JRException | SQLException | NamingException e) {
			e.printStackTrace();
		}
	}
}
