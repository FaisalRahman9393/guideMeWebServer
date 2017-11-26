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
@WebServlet("/Facilities")
public class facilitiesManager extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection con;
    Statement st = null;
    ResultSet rs;
    String email;
    String selectedFacility;
    guideMeServerInt x;
    AuthenticatedAPICall apiCall = new AuthenticatedAPICall();

    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonPressed	=	request.getParameter("pagename");

        if(buttonPressed.equals("facilitiesFormAddNew")){
            String name	=	request.getParameter("facilityName");
            String location	=	request.getParameter("facilityLocation");
            String openingHours	=	request.getParameter("facilityOpeningHours");
            String info	=	request.getParameter("facilityInfo");

            try {
                apiCall.guideMeServer.addANewFacility(name,location,openingHours,info);
                response.sendRedirect("rightPin.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(buttonPressed.equals("facilitiesFormEditPressed")){

            /**
             * A list will be created to send to the jsp file
             */
            ArrayList<String> list = new ArrayList<>();

            try {
                String jstring = apiCall.guideMeServer.returnATableFac("facilities");

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
            RequestDispatcher rd=request.getRequestDispatcher("adminFacilitiesEditCurrentPre.jsp");
            rd.forward(request,response);

        }


        if(buttonPressed.equals("chosenFacility")){
            //ArrayList<String> list2 = new ArrayList<>();
            //ArrayList<String> list = new ArrayList<>();
            selectedFacility  = request.getParameter("selectedFac");
            try {
                String parser = apiCall.guideMeServer.returnATableWhereFac("facilities","name",selectedFacility);

                Object obj= JSONValue.parse(parser);
                JSONObject jsonObject = (JSONObject) obj;

                request.setAttribute("name",jsonObject.get("Name"));
                request.setAttribute("location",jsonObject.get("Location"));
                request.setAttribute("hour",jsonObject.get("Hours"));
                request.setAttribute("info",jsonObject.get("Info"));
                RequestDispatcher rd=request.getRequestDispatcher("adminFacilitiesEditCurrent.jsp");
                rd.forward(request,response);
            } catch (SQLException e) {
                e.printStackTrace();
            }catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(buttonPressed.equals("facilitiesFormEditUpdate")){
            String locationofFac	=	request.getParameter("facilitiesLocation");
            String hoursOfFac	=	request.getParameter("facilitiesHours");
            String descr	=	request.getParameter("facilitiesInfo");
            try {
                apiCall.guideMeServer.updateFacility(selectedFacility,locationofFac,hoursOfFac,descr);
                response.sendRedirect("rightPin.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public facilitiesManager() {
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