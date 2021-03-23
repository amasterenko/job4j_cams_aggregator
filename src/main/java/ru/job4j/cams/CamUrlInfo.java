package ru.job4j.cams;

public class CamUrlInfo {
    private final int id;
    private final String sourceDataUrl;
    private final String tokenDataUrl;

    public CamUrlInfo(int id, String sourceDataUrl, String tokenDataUrl) {
        this.id = id;
        this.sourceDataUrl = sourceDataUrl;
        this.tokenDataUrl = tokenDataUrl;
    }

    public int getId() {
        return id;
    }

    public String getSourceDataUrl() {
        return sourceDataUrl;
    }

    public String getTokenDataUrl() {
        return tokenDataUrl;
    }
}
