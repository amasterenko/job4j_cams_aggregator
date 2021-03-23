package ru.job4j.cams;

import java.util.Objects;

/**
 * The class stores camera aggregated data
 * @author AndrewMs
 * @version 1.0
 */
public class CamSumInfo {
    private final int id;
    private String urlType;
    private String videoUrl;
    private String value;
    private int ttl;

    public CamSumInfo(int id) {
        this.id = id;
    }

    public void setUrlType(String urlType) {
        this.urlType = urlType;
    }

    public void setVideoUrl(String videoUrl) {
        this.videoUrl = videoUrl;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public int getId() {
        return id;
    }

    public String getUrlType() {
        return urlType;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getValue() {
        return value;
    }

    public int getTtl() {
        return ttl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CamSumInfo that = (CamSumInfo) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
