package ru.job4j.cams;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Map;
/**
 * The class parses responses from camera token data urls
 * @author AndrewMs
 * @version 1.0
 */
public class TokenParser implements Runnable {
    private final String url;
    private final int camId;
    private HttpClient client = HttpClient.of();
    private final Map<Integer, CamSumInfo> map;

    private TokenParser(String url, int camId, Map<Integer, CamSumInfo> map) {
        this.url = url;
        this.camId = camId;
        this.map = map;
    }

    public static TokenParser of(String url, int camId, Map<Integer, CamSumInfo> map) {
        return new TokenParser(url, camId, map);
    }

    /**
     * Gets TokenData object using json parsing and put its fields into the results map
     */
    @Override
    public void run() {
        try {
            TokenData tokenData = new Gson().fromJson(client.getResponse(url), TokenData.class);
            map.computeIfPresent(camId, (k, v) -> {
                v.setValue(tokenData.getValue());
                v.setTtl(tokenData.getTtl());
                return v;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
