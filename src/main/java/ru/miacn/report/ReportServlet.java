package ru.miacn.report;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

import javax.inject.Inject;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.jasperreports.engine.JRException;
import ru.miacn.fias.FiasEditorEditman;

public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 9063662138458031340L;
	
	@Inject
	private FiasEditorEditman fias;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		try {
			JasperReport rep;
			HttpSession ses = req.getSession();
			int id = (int) ((ses.getAttribute("id") != null) ? ses.getAttribute("id") : 0);
			ReportType type = ((ses.getAttribute("pdf") != null) && ((boolean) ses.getAttribute("pdf"))) ? ReportType.pdf : ReportType.html;
			
			switch (id) {
			case 1:
				rep = new ReportSocGroup();
				break;
			case 2:
				rep = new ReportMedGroup();
				break;
			case 3:
				rep = new ReportDecrGroup();
				break;
			case 4:
				rep = new ReportForm057u();
				break;
			default:
				throw new ServletException("Specify 'id' parameter with value 1, 2, 3 or 4.");
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
			
			rep.fias = fias;
			rep.setParameters(ses);
			rep.printReport(resp.getOutputStream(), type);
		} catch (JRException | SQLException | NamingException | ParseException e) {
			e.printStackTrace();
		}
	}
}
