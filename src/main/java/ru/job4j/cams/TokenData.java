package ru.job4j.cams;
/**
 * Class for json parsing camera token urls
 * @author AndrewMs
 * @version 1.0
 */
public class TokenData {
    private final String value;
    private final int ttl;

    public TokenData(String value, int ttl) {
        this.value = value;
        this.ttl = ttl;
    }

    public String getValue() {
        return value;
    }

    public int getTtl() {
        return ttl;
    }
}