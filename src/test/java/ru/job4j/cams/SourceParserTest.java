package ru.job4j.cams;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class SourceParserTest {
    /**
     * URL = http://www.mocky.io/v2/5c51b230340000094f129f5d
     * Response:
     * {
     *     "urlType": "LIVE",
     *     "videoUrl": "rtsp://127.0.0.1/1"
     * }
     */
    @Test
    public void whenParseSourceData() {
        String url = "http://www.mocky.io/v2/5c51b230340000094f129f5d";
        Map<Integer, CamSumInfo> map = new HashMap<>();
        map.put(1, new CamSumInfo(1));
        SourceParser parser = SourceParser.of(url, 1, map);
        parser.run();
        assertThat(map.get(1).getUrlType(), is("LIVE"));
        assertThat(map.get(1).getVideoUrl(), is("rtsp://127.0.0.1/1"));
    }
}