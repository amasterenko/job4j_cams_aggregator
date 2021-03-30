package ru.job4.cams2;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * The class implements url-connection with 5 sec. maximum waiting time interval.
 *
 * @author AndrewMs
 * @version 2.0
 */
public class HttpClient {
    private final static String USER_AGENT = "Mozilla/5.0";
    private final static int MAX_TIME_WAIT = 5000;
    private final String url;

    public HttpClient(String url) {
        this.url = url;
    }
    /**
     * Gets a response for specified url
     *
     * @return List of strings
     * @throws IOException IO Exception
     */

    public List<String> getResponse() throws IOException {
        List<String> rsl = new ArrayList<>();
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setReadTimeout(MAX_TIME_WAIT);
        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        while ((inputLine = in.readLine()) != null) {
            rsl.add(inputLine);
        }
        in.close();
        con.disconnect();
        return rsl;
    }
}
