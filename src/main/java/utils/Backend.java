package utils;

import com.playtech.qatools.openapi.OpenApiConnection;
import org.json.JSONObject;
import springConstructors.UserData;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Backend {

    public static void createUser(UserData userData) {

        Date now = new Date();
        SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String strDate = sdfDate.format(now);
        //System.out.println(strDate);

        JSONObject request = new JSONObject();
        request.put("changeTimestamp", strDate);

        JSONObject dataMap = prepareDataMap(userData);
        request.put("dataMap", dataMap);

        request.put("clientType", "casino");
        request.put("ID", 31007);
        request.put("casinoName", DataProvider.getIMSData().getImsCasino());

        OpenApi.getOpenApiConnection().waitForID(request.toString(), Arrays.asList(31008, 33006));

        System.out.println("User with: "+userData.getUsername()+" username was created successfully");
    }

    private static JSONObject prepareDataMap(UserData userData) {
        JSONObject dataMap = new JSONObject();
        //*dataMap.put("bonusgroupid", 20);
        dataMap.put("currencyCode", userData.getCurrency());
        dataMap.put("countrycode", userData.getCountry());
        dataMap.put("city", userData.getCity());
        dataMap.put("zip", userData.getPostCode());
        dataMap.put("phone", userData.getPhone());
        dataMap.put("cellphone", userData.getPhone());
        dataMap.put("address", userData.getAddress());
        //dataMap.put("address", (userData.getHouse() + " " + userData.getAddress() + " " + userData.getAddress2()).trim());
        dataMap.put("birthdate", userData.getBirthYear() + "-" + userData.getBirthMonth() + "-" + userData.getBirthDay());
        dataMap.put("userName", userData.getUsername());
        dataMap.put("password", userData.getPassword());
        dataMap.put("title", userData.getTitle());
        dataMap.put("firstname", userData.getFirstName());
        dataMap.put("lastname", userData.getLastName());
        dataMap.put("email", userData.getEmail());

        //dataMap.put("tcVersion", .getDefaults().getTcVersion());
        dataMap.put("language", "EN");

        return dataMap;
    }

    private static String prepareLoginRequest(UserData userData) {
        JSONObject login = new JSONObject();
        login.put("ID", 31001);
        login.put("userName", userData.getUsername());
        login.put("password", userData.getPassword());
        login.put("casinoName", DataProvider.getIMSData().getImsCasino());
        login.put("languageCode", "EN");
        login.put("clientVersion", -1);
        login.put("realMode", 1);
        return login.toString();
    }

    private static String prepareAcceptTC() {
        JSONObject tc = new JSONObject();
        tc.put("ID", 31011);
        tc.put("accept", true);
        tc.put("termVersionReference", "73591");
        return tc.toString();
    }


    private static class OpenApi {

        private static OpenApiConnection openApiConnection;

        private static void openConnection() {
            openApiConnection = new OpenApiConnection();
            openApiConnection.open(DataProvider.getIMSData().getImsOpenApiConnection());
            openApiConnection.setTimeout(5, TimeUnit.SECONDS);
        }

        static OpenApiConnection getOpenApiConnection () {
            openConnection();
            return openApiConnection;
        }
    }


}
