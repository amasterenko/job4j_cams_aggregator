package ru.job4j.cams;

import java.util.Map;

/**
 * The class stores parameters for the url-parsers
 * @author AndrewMs
 * @version 1.0
 */
public abstract class DataParser implements Runnable {
    private String url;
    private int camId;
    private HttpClient client = HttpClient.of();
    private Map<Integer, CamSumInfo> map;

    protected DataParser(String url, int camId, Map<Integer, CamSumInfo> map) {
        this.url = url;
        this.camId = camId;
        this.map = map;
    }

    @Override
    public void run() {

    }
}