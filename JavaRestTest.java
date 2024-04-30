package rest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class JavaRestTest {
    public static void main(String[] args) throws IOException {
        String url = "http://localhost:8081/simulator/";

        HashMap<String, String> params = new HashMap<>();
        params.put("param1", "Parth");
        params.put("param2", "Amlan");
        params.put("param3", "Rishabh");
        System.out.println(getResponse(url, params));
    }

    public static String getResponse(String urlString, Map<String, String> requestParams) throws IOException {
        StringBuilder finalUrl = new StringBuilder(urlString);

        if (requestParams.size() > 0) finalUrl.append("?");

        int count = 0;
        for (Map.Entry<String, String> entry : requestParams.entrySet()) {
            if (count != 0) finalUrl.append("&");
            finalUrl.append(entry.getKey()).append("=").append(entry.getValue());
            count++;
        }

        URL url = new URL(finalUrl.toString());
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setRequestProperty("Content-Type", "text/json; charset=utf-8");
        httpConn.setRequestMethod("GET");
        httpConn.setDoInput(true);

        InputStream inputStream = httpConn.getInputStream();
        InputStreamReader isr = new InputStreamReader(inputStream, "UTF-8");
        BufferedReader in = new BufferedReader(isr);
        String bufferedValue = null;
        StringBuilder returnVal = new StringBuilder();
        while ((bufferedValue = in.readLine()) != null) {
            returnVal.append(bufferedValue);
        }
        return returnVal.toString();
    }
}
