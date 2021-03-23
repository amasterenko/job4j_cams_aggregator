package ru.job4j.cams;

import com.google.gson.Gson;
import java.io.IOException;
import java.util.Map;

/**
 * The class parses responses from camera source data urls
 * @author AndrewMs
 * @version 1.0
 */
public class SourceParser implements Runnable {
    private final String url;
    private final int camId;
    private HttpClient client = HttpClient.of();
    private final Map<Integer, CamSumInfo> map;

    private SourceParser(String url, int camId, Map<Integer, CamSumInfo> map) {
        this.url = url;
        this.camId = camId;
        this.map = map;
    }

    public static SourceParser of(String url, int camId, Map<Integer, CamSumInfo> map) {
        return new SourceParser(url, camId, map);
    }

    /**
     * Gets SourceData object using json parsing and put its fields into the results map
     */
    @Override
    public void run() {
        try {
            SourceData sourceData = new Gson().fromJson(client.getResponse(url), SourceData.class);
            map.computeIfPresent(camId, (k, v) -> {
                v.setUrlType(sourceData.getUrlType());
                v.setVideoUrl(sourceData.getVideoUrl());
                return v;
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
