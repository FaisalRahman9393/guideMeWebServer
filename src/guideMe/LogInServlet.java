package guideMe;

import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * - Before deploying the server, make sure Email Service is On inside the RegisterServlet
 * - The admin username and password: -u admin, -p guidemeisawesome
 */
/**
 * This is the servlet for the login/ index process
 */
@WebServlet("/LogIn")
public class LogInServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection con;
    java.sql.Statement st = null;
    ResultSet rs;
    String email;
    AuthenticatedAPICall apiCall = new AuthenticatedAPICall();

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonPressed	=	request.getParameter("pagename");
         StringBuilder hexString = null;

        //f.rahman@lancaster.ac.uk
        //password:hello
        //Once it is fixed: http://stackoverflow.com/questions/13331478/matching-java-sha2-output-vs-mysql-sha2-output

        /**
         * If log in is selected then execute the following...
         */
        if(buttonPressed.equals("login")){

            /**
             * Hashing the password for caparison later...
             */
            String userName	=	request.getParameter("txtUserName");

            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(request.getParameter("txtPassword").getBytes("UTF-8"));

                hexString = new StringBuilder();
                for (int i: hash) {
                    hexString.append(Integer.toHexString(0XFF & i));
                }
                System.out.println(hexString);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            if (userName.equals("admin")) {
                    boolean result = false;
                    try {
                        result = apiCall.guideMeServer.logIn(userName, hexString.toString());
                        if (result) {
                            response.sendRedirect("admin.jsp");
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }

            boolean result = false;
            try {
                result = apiCall.guideMeServer.logIn(userName, hexString.toString());
                if (result) {
                    String parser = apiCall.guideMeServer.getAcademics(userName);
                    Object obj= JSONValue.parse(parser);
                    JSONObject jsonObject = (JSONObject) obj;

                    String nameOfAcademic = jsonObject.get("Name").toString();
                    String roleOfAcademic = jsonObject.get("Role").toString();
                    String departmentOfAcademic = jsonObject.get("Department").toString();
                    String emailOfAcademic = jsonObject.get("Email").toString();
                    String officeOfAcademic = jsonObject.get("Office").toString();
                    String numberOfAcademic = jsonObject.get("Number").toString();
                    String availabilityOfAcademic = jsonObject.get("Availability").toString();

                    request.setAttribute("nameOfAcademic",nameOfAcademic);
                    request.setAttribute("roleOfAcademic",roleOfAcademic);
                    request.setAttribute("departmentOfAcademic",departmentOfAcademic);
                    request.setAttribute("emailOfAcademic",emailOfAcademic);
                    request.setAttribute("officeOfAcademic",officeOfAcademic);
                    request.setAttribute("numberOfAcademic",numberOfAcademic);
                    request.setAttribute("availabilityOfAcademic",availabilityOfAcademic);
                    RequestDispatcher rd=request.getRequestDispatcher("authenticated.jsp");
                    rd.forward(request,response);
                    //response.sendRedirect("authenticated.jsp");
                }
                else response.sendRedirect("wrongPass.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public LogInServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}