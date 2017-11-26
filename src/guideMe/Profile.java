package guideMe;

import java.io.IOException;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is the servlet for the registration process
 */
@WebServlet("/Profile")
public class Profile extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection con;
    java.sql.Statement st = null;
    ResultSet rs;
    AuthenticatedAPICall apiCall = new AuthenticatedAPICall();
    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonPressed	=	request.getParameter("pagename");

        if(buttonPressed.equals("profileForm")) {
            //Get the inputs from the jsp page for changing information about the lecturer
            String nameOfAcademic	=	request.getParameter("academicName");
            String departmentOfAcademic	=	request.getParameter("academicDepartment");
            String roleOfAcademic	=	request.getParameter("academicRole");
            String officeOfAcademic	=	request.getParameter("academicOffice");
            String numberOfAcademic	=	request.getParameter("academicNumber");
            String availabilityOfAcademic	=	request.getParameter("academicAvailability");
            String emailOfAcadmic	=	request.getParameter("academicEmail");

            System.out.println(officeOfAcademic+"\n"+emailOfAcadmic);
            try {
                boolean success = apiCall.guideMeServer.updateAcademics(emailOfAcadmic,nameOfAcademic,roleOfAcademic,
                        departmentOfAcademic,numberOfAcademic,officeOfAcademic,availabilityOfAcademic);
                if (success){
                    response.sendRedirect("rightPin.jsp");
                }
                else response.sendRedirect("wrongPass.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(buttonPressed.equals("adminForm")) {
            //Get the inputs from the jsp page for changing information about the lecturer
            String nameOfAcademic	=	request.getParameter("academicName");
            String departmentOfAcademic	=	request.getParameter("academicDepartment");
            String roleOfAcademic	=	request.getParameter("academicRole");
            String officeOfAcademic	=	request.getParameter("academicOffice");
            String numberOfAcademic	=	request.getParameter("academicNumber");
            String availabilityOfAcademic	=	request.getParameter("academicAvailability");
            String emailOfAcadmic	=	request.getParameter("academicEmail");

            System.out.println(officeOfAcademic+"\n"+emailOfAcadmic);
            try {
                boolean success = apiCall.guideMeServer.addANewAcademics(emailOfAcadmic,nameOfAcademic,roleOfAcademic,departmentOfAcademic,numberOfAcademic,officeOfAcademic,availabilityOfAcademic);
                if (success){
                    response.sendRedirect("rightPin.jsp");
                }                else response.sendRedirect("wrongPass.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public Profile() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}