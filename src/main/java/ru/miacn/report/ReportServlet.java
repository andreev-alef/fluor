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
			JasperReport rep;
			String id = (req.getParameter("id") != null) ? req.getParameter("id") : "";
			ReportType type = (req.getParameter("pdf") != null) ? ReportType.pdf : ReportType.html;
			
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
			
			switch (type) {
			case html:
				resp.setContentType("text/html");
				break;
			case pdf:
				resp.setContentType("application/pdf");
				resp.addHeader("Content-Disposition", "filename=" + rep.getReportName() + ".pdf");
				break;
			default:
				break;
			}
			
			rep.printReport(resp.getOutputStream(), type);
		} catch (JRException | SQLException | NamingException e) {
			e.printStackTrace();
		}
	}
}
