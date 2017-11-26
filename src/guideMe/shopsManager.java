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
@WebServlet("/Shops")
public class shopsManager extends HttpServlet {
    private static final long serialVersionUID = 1L;
    Connection con;
    Statement st = null;
    ResultSet rs;
    String email;
    String selectedShop;
    AuthenticatedAPICall apiCall = new AuthenticatedAPICall();


    /**
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String buttonPressed	=	request.getParameter("pagename");

        apiCall.initiateAuthenticationProtocol();


        if(buttonPressed.equals("shopFormAddNew")){
            String name	=	request.getParameter("shopName");
            String location	=	request.getParameter("shopLocation");
            String openingHours	=	request.getParameter("shopOpeningHours");
            String info	=	request.getParameter("shopInfo");

            try {
                apiCall.guideMeServer.addANewShop(name,location,openingHours,info);
                response.sendRedirect("rightPin.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(buttonPressed.equals("shopsFormEditPressed")){
            /**
             * A list will be created to send to the jsp file
             */
            ArrayList<String> list = new ArrayList<>();

            try {
                String jstring = apiCall.guideMeServer.returnATableShop("shops");
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
            RequestDispatcher rd=request.getRequestDispatcher("adminShopsEditCurrentPre.jsp");
            rd.forward(request,response);

        }


        if(buttonPressed.equals("chosenShop")){
            selectedShop  = request.getParameter("selectedShop");
            try {
                String parser = apiCall.guideMeServer.returnATableWhereShop("shops","name",selectedShop);

                Object obj= JSONValue.parse(parser);
                JSONObject jsonObject = (JSONObject) obj;


                request.setAttribute("nameOfShop",jsonObject.get("Name").toString());
                request.setAttribute("shopLocation",jsonObject.get("Location").toString());
                request.setAttribute("shopOpeningHours",jsonObject.get("Hours").toString());
                request.setAttribute("shopInfo",jsonObject.get("Info").toString());
                RequestDispatcher rd=request.getRequestDispatcher("adminShopsEditCurrent.jsp");
                rd.forward(request,response);
                //response.sendRedirect("authenticated.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        if(buttonPressed.equals("shopFormEditUpdate")){
            String locationofShop	=	request.getParameter("shopLocation");
            String hoursOfShop	=	request.getParameter("shopOpeningHours");
            String descr	=	request.getParameter("shopInfo");
            try {
                apiCall.guideMeServer.updateShop(selectedShop,locationofShop,hoursOfShop,descr);
                response.sendRedirect("rightPin.jsp");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * @see HttpServlet#HttpServlet()
     */
    public shopsManager() {
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