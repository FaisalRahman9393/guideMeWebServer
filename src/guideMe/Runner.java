package guideMe;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by Faze on 18/11/2016.
 */
public class Runner {
    public static void main(String[] args) {
        AuthenticatedAPICall iAuthenticatedAPICall = new AuthenticatedAPICall();
        iAuthenticatedAPICall.menu();

    }
}
