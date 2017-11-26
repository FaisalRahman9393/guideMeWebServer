package guideMe;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
/**
 * - Before deploying the server, make sure Email Service is On inside the RegisterServlet
 * - The admin username and password: -u admin, -p guidemeisawesome
 */

/**
 * This is the servlet for the login/ index process
 */
@WebServlet("/Departments")
public class departmentsManager extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection con;
    Statement st = null;
    ResultSet rs;
    String email;
    String selectedDepart;
    AuthenticatedAPICall apiCall = new AuthenticatedAPICall();

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonPressed	=	request.getParameter("pagename");
        if(buttonPressed.equals("departmentsFormAddNew")){
            String name	=	request.getParameter("departmentName");
            String location	=	request.getParameter("departmentLocation");
            String faculty	=	request.getParameter("departmentFaculty");
            String info	=	request.getParameter("departmentInfo");

            try {
                apiCall.guideMeServer.addANewDepartment(name,location,faculty,info);
                response.sendRedirect("rightPin.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(buttonPressed.equals("departmentsFormEditPressed")){
            /**
             * A list will be created to send to the jsp file
             */
            ArrayList<String> list = new ArrayList<>();

            try {
                String jstring = apiCall.guideMeServer.returnATableDepartment("departments");

                Gson gson = new Gson();
                Type type = new TypeToken<ArrayList<String>>() {}.getType();
                list = gson.fromJson(jstring, type);
            } catch (Exception e) {
                e.printStackTrace();
            }

            /**
             * This list will then be passed to the servlet for drop down menu
             */
            request.setAttribute("list",list);
            RequestDispatcher rd=request.getRequestDispatcher("adminDepartmentsEditCurrentPre.jsp");
            rd.forward(request,response);
        }


        if(buttonPressed.equals("chosenDepartment")){
            selectedDepart  = request.getParameter("selectedDepart");

            try {
                String parser = apiCall.guideMeServer.returnATableWhereDepartment("departments","name",selectedDepart);

                Object obj= JSONValue.parse(parser);
                JSONObject jsonObject = (JSONObject) obj;

                    request.setAttribute("nameOfDep",jsonObject.get("Name"));
                    request.setAttribute("locationOfDep",jsonObject.get("Location"));
                    request.setAttribute("facultyOfDep",jsonObject.get("Faculty"));
                    request.setAttribute("infoOfDep",jsonObject.get("Info"));
                    RequestDispatcher rd=request.getRequestDispatcher("adminDepartmentsEditCurrent.jsp");
                    rd.forward(request,response);
                } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(buttonPressed.equals("departmentsFormEditUpdate")){
            String locationofDEP	=	request.getParameter("departmentLocation");
            String facOfDept	=	request.getParameter("departmentFac");
            String descr	=	request.getParameter("departmentInfo");

            try {
                apiCall.guideMeServer.updateDepartment(selectedDepart,locationofDEP,facOfDept,descr);
                response.sendRedirect("rightPin.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public departmentsManager() {
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