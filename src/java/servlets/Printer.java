package servlets;

import DB_Classes.ConnectionMaster;
import java.io.IOException;
import java.io.OutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.JasperRunManager;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

public class Printer extends HttpServlet {
    String db_code, table;
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        db_code = (String) request.getSession().getAttribute("db_code");
        
        table = "current_routine_status_"+db_code;
        
        

        JasperReport jasperReport = null;
        JasperDesign jasperDesign = null;
        Map parameters = new HashMap();
        parameters.put("TABLE", table);
        
        byte[] byteStream;

        String path = getServletContext().getRealPath("");
        
        //System.out.println(path);

        try {
            jasperDesign = JRXmlLoader.load(path + "/EasyRoutine.jrxml");
            jasperReport = JasperCompileManager.compileReport(jasperDesign);
            byteStream = JasperRunManager.runReportToPdf(jasperReport, parameters, ConnectionMaster.getInstance().connection);
            OutputStream outStream = response.getOutputStream();
            response.setHeader("Content-Disposition", "inline, filename=EasyRoutine_"+DateTimeFormatter.ofPattern("yyyy-MM-dd_HH:mm:ss").format(LocalDateTime.now())+".pdf");
            response.setContentType("application/pdf");
            response.setContentLength(byteStream.length);
            outStream.write(byteStream, 0, byteStream.length);
            //System.out.println("Report Printing successful");
        } catch (JRException ex) {
            ex.printStackTrace();
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }
}
