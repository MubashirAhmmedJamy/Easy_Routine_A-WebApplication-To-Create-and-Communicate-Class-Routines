package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class ClassRoutine_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html>\n");
      out.write("    <head>\n");
      out.write("        <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n");
      out.write("        <title>Class Routine</title>\n");
      out.write("    </head>\n");
      out.write("    <body onload=\"preLoader()\">\n");
      out.write("\n");
      out.write("        <div id=\"mySidenav\" class=\"sidenav\">\n");
      out.write("            <a href=\"javascript:void(0)\" class=\"closebtn\" onclick=\"closeNav()\">&times;</a>\n");
      out.write("            <a href=\"AdminPanel.jsp\">Admin Panel</a>\n");
      out.write("            <a href=\"RoutineDesigner.jsp\">Routine Designer</a>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <span style=\"font-size:30px;cursor:pointer;color: #000000\" onclick=\"openNav()\">&#9776;</span>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <div id=\"driver\" style=\"background-color: #ccff66; border: 2px solid black\">\n");
      out.write("            <div>\n");
      out.write("                <select id=\"db_codes\" onchange=\"db_codeManager()\">\n");
      out.write("                    <option value=\"select\">Select A Routine</option>\n");
      out.write("                </select>\n");
      out.write("\n");
      out.write("<!--                <button id=\"db\" onclick=\"db_codeManager()\">Confirm</button>    -->\n");
      out.write("            </div>\n");
      out.write("            \n");
      out.write("            \n");
      out.write("            <p align=\"center\"><strong>See Class Routine</strong></p>\n");
      out.write("\n");
      out.write("            <div class=\"A\">\n");
      out.write("                <label class=\"container\"><strong>See Routine for ALL</strong>\n");
      out.write("                    <span class=\"checkmark\"></span>\n");
      out.write("                    <input type=\"checkbox\" id=\"all\" onclick=\"All()\"/>                    \n");
      out.write("                </label>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("            <div class=\"A\">\n");
      out.write("                <select id=\"students\">\n");
      out.write("                    <option value=\"select\">Select A Batch</option>\n");
      out.write("                </select>               \n");
      out.write("\n");
      out.write("                <label class=\"container\">Students\n");
      out.write("                    <input type=\"checkbox\" id=\"only-studetns\" onclick=\"onlyStudents()\"/>\n");
      out.write("                    <span class=\"checkmark\"></span>\n");
      out.write("                </label> \n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("            <div class=\"A\">\n");
      out.write("                <select id=\"teachers\">\n");
      out.write("                    <option value=\"select\">Select A Teacher</option>\n");
      out.write("                </select>\n");
      out.write("\n");
      out.write("                <label class=\"container\">Teachers\n");
      out.write("                    <input type=\"checkbox\" id=\"only-teachers\" onclick=\"onlyTeachers()\"/>\n");
      out.write("                    <span class=\"checkmark\"></span>\n");
      out.write("                </label>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("            <div class=\"A\">\n");
      out.write("                <select id=\"classrooms\">\n");
      out.write("                    <option value=\"select\">Select Classroom</option>\n");
      out.write("                </select>\n");
      out.write("\n");
      out.write("                <label class=\"container\">Classrooms\n");
      out.write("                    <input type=\"checkbox\" id=\"only-classrooms\" onclick=\"onlyClassrooms()\"/>\n");
      out.write("                    <span class=\"checkmark\"></span>\n");
      out.write("                </label>\n");
      out.write("            </div>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div id=\"r\" hidden=\"true\">\n");
      out.write("            <h1 id=\"lebel-routine\" style=\"text-align: center\"></h1>\n");
      out.write("            <table id=\"routine\" style=\"margin-left:auto;margin-right:auto\">\n");
      out.write("                <thead>\n");
      out.write("                <th>Day</th>\n");
      out.write("                <th>Semester</th>\n");
      out.write("                <th>8AM</th>\n");
      out.write("                <th>9AM</th>\n");
      out.write("                <th>10AM</th>\n");
      out.write("                <th>11AM</th>\n");
      out.write("                <th>12PM</th>\n");
      out.write("                <th>2PM</th>\n");
      out.write("                <th>3PM</th>\n");
      out.write("                <th>4PM</th>\n");
      out.write("                </thead>\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <div id=\"t\" hidden=\"true\">\n");
      out.write("\n");
      out.write("            <h1 id=\"lebel-routine-T\" style=\"text-align: center\"></h1>\n");
      out.write("\n");
      out.write("            <table id=\"routine-T\" style=\"margin-left:auto;margin-right:auto\">\n");
      out.write("                <thead>\n");
      out.write("                <th>Day</th>\n");
      out.write("                <th>8AM</th>\n");
      out.write("                <th>9AM</th>\n");
      out.write("                <th>10AM</th>\n");
      out.write("                <th>11AM</th>\n");
      out.write("                <th>12PM</th>\n");
      out.write("                <th>2PM</th>\n");
      out.write("                <th>3PM</th>\n");
      out.write("                <th>4PM</th>\n");
      out.write("                </thead>\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div id=\"s\" hidden=\"true\">        \n");
      out.write("            <h1 id=\"lebel-routine-S\" style=\"text-align: center\"></h1>\n");
      out.write("            <table id=\"routine-S\" style=\"margin-left:auto;margin-right:auto\">\n");
      out.write("                <thead>\n");
      out.write("                <th>Day</th>\n");
      out.write("                <th>8AM</th>\n");
      out.write("                <th>9AM</th>\n");
      out.write("                <th>10AM</th>\n");
      out.write("                <th>11AM</th>\n");
      out.write("                <th>12PM</th>\n");
      out.write("                <th>2PM</th>\n");
      out.write("                <th>3PM</th>\n");
      out.write("                <th>4PM</th>\n");
      out.write("                </thead>\n");
      out.write("            </table>\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("        <div id=\"c\" hidden=\"true\">\n");
      out.write("            <h1 id=\"lebel-routine-C\" style=\"text-align: center\"></h1>\n");
      out.write("            <table id=\"routine-C\" style=\"margin-left:auto;margin-right:auto\">\n");
      out.write("                <thead>\n");
      out.write("                <th>Day</th>\n");
      out.write("                <th>8AM</th>\n");
      out.write("                <th>9AM</th>\n");
      out.write("                <th>10AM</th>\n");
      out.write("                <th>11AM</th>\n");
      out.write("                <th>12PM</th>\n");
      out.write("                <th>2PM</th>\n");
      out.write("                <th>3PM</th>\n");
      out.write("                <th>4PM</th>\n");
      out.write("                </thead>\n");
      out.write("            </table>            \n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("\n");
      out.write("        <p id=\"Demo1\"></p><br>\n");
      out.write("        <p id=\"Demo2\"></p><br>\n");
      out.write("        <p id=\"Demo3\"></p><br>\n");
      out.write("        <p id=\"Demo4\"></p><br>\n");
      out.write("        <p id=\"Demo5\"></p><br>\n");
      out.write("\n");
      out.write("        <div class=\"modal\"><!-- Place at bottom of page --></div>\n");
      out.write("        <link rel=\"stylesheet\" type=\"text/css\" href=\"css-js/classRoutine.css\">\n");
      out.write("        <script src=\"css-js/routineJavascript.js\" type=\"text/javascript\"></script>\n");
      out.write("        <script src=\"css-js/jqueryGoogle.min.js\" type=\"text/javascript\"></script>\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css\">\n");
      out.write("    </body>\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
