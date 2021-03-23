package ru.job4j.cams;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * The class implements url-connection with 5 sec. maximum waiting time interval.
 * @author AndrewMs
 * @version 1.0
 */
public class HttpClient {
    private final static  String USER_AGENT = "Mozilla/5.0";
    private final static int MAX_TIME_WAIT = 5000;

    private HttpClient() {
    }

    public static HttpClient of() {
        return new HttpClient();
    }

    /**
     * Gets a response from the url
     * @param url URL
     * @return Response string
     * @throws IOException IO Exception
     */
    public String getResponse(String url) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setReadTimeout(MAX_TIME_WAIT);
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        con.disconnect();
        return response.toString();
    }
}
