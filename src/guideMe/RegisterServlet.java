package guideMe;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.*;
import java.util.concurrent.ThreadLocalRandom;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * This is the servlet for the registration process
 */
@WebServlet("/RegMe")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection con;
    java.sql.Statement st = null;
    ResultSet rs;
    String emailEntered = null;
    int ranNum;
    AuthenticatedAPICall apiCall = new AuthenticatedAPICall();

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonPressed	=	request.getParameter("pagename");

        /**
         * This code is executed the registration button is pressed....
         */
        if(buttonPressed.equals("regmail")) {
            emailEntered	=	request.getParameter("txtUserName");

            try {
                boolean success = apiCall.guideMeServer.addNewUser(emailEntered);
                if (success){
                    response.sendRedirect("registrationFinal.jsp");
                }else {
                    response.sendRedirect("wrongPassword.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        /**
         * Final stage of the registration process
         *
         * Here, the lecturer enters the pin received in email, followed by apiCall password
         */
        if(buttonPressed.equals("regreg")){
            StringBuilder hexString = null;
            //Remove any spaces from the input
            emailEntered = emailEntered.replaceAll("\\s","");
            String pin = Integer.toString(ranNum);
            String pinEntered	=	request.getParameter("secPin");
            String passwordEntered	=	request.getParameter("password");
            try {
                MessageDigest digest = MessageDigest.getInstance("SHA-256");
                byte[] hash = digest.digest(passwordEntered.getBytes("UTF-8"));

                hexString = new StringBuilder();
                for (int i: hash) {
                    hexString.append(Integer.toHexString(0XFF & i));
                }
                System.out.println(hexString);
            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

            try {
                boolean success = apiCall.guideMeServer.addNewUserFinal(pinEntered,hexString.toString());
                if (success){
                    response.sendRedirect("rightPin.jsp");
                }else {response.sendRedirect("wrongPin.jsp");}
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public RegisterServlet() {
        super();
    }

    /**
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }

}