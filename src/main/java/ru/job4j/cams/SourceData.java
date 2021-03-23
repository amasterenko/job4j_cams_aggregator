package ru.job4j.cams;

/**
 * Class for json parsing camera source urls
 * @author AndrewMs
 * @version 1.0
 */
public class SourceData {
    private final String urlType;
    private final String videoUrl;

    public SourceData(String urlType, String videoUrl) {
        this.urlType = urlType;
        this.videoUrl = videoUrl;
    }

    public String getUrlType() {
        return urlType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

}
