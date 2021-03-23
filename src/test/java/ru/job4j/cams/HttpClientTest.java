package ru.job4j.cams;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HttpClientTest {
    @Test
    public void whenGetCamsUrl() throws IOException {
        String url = "http://www.mocky.io/v2/5c51b9dd3400003252129fb5";
        HttpClient cl = HttpClient.of();
        String expected = "["
                + "    {"
                + "        \"id\": 1,"
                + "        \"sourceDataUrl\": \"http://www.mocky.io/v2/5c51b230340000094f129f5d\","
                + "        \"tokenDataUrl\": "
                + "\"http://www.mocky.io/v2/5c51b5b6340000554e129f7b?mocky-delay=1s\""
                + "    },"
                + "    {"
                + "        \"id\": 20,"
                + "        \"sourceDataUrl\": "
                + "\"http://www.mocky.io/v2/5c51b2e6340000a24a129f5f?mocky-delay=100ms\","
                + "        \"tokenDataUrl\": \"http://www.mocky.io/v2/5c51b5ed340000554e129f7e\""
                + "    },"
                + "    {"
                + "        \"id\": 3,"
                + "        \"sourceDataUrl\": \"http://www.mocky.io/v2/5c51b4b1340000074f129f6c\","
                + "        \"tokenDataUrl\": "
                + "\"http://www.mocky.io/v2/5c51b600340000514f129f7f?mocky-delay=2s\""
                + "    },"
                + "    {"
                + "        \"id\": 2,"
                + "        \"sourceDataUrl\": \"http://www.mocky.io/v2/5c51b5023400002f4f129f70\","
                + "        \"tokenDataUrl\": \"http://www.mocky.io/v2/5c51b623340000404f129f82\""
                + "    }"
                + "]";
        assertThat(cl.getResponse(url), is(expected));
    }

    @Test
    public void whenSourceDataUrl() throws IOException {
        String url = "http://www.mocky.io/v2/5c51b230340000094f129f5d";
        HttpClient cl = HttpClient.of();
        String expected = "{"
                + "    \"urlType\": \"LIVE\","
                + "    \"videoUrl\": \"rtsp://127.0.0.1/1\""
                + "}";
        assertThat(cl.getResponse(url), is(expected));
    }

    @Test
    public void whenTokenDataUrl() throws IOException {
        String url = "http://www.mocky.io/v2/5c51b5b6340000554e129f7b?mocky-delay=1s";
        HttpClient cl = HttpClient.of();
        String expected = "{"
                + "    \"value\": \"fa4b588e-249b-11e9-ab14-d663bd873d93\","
                + "    \"ttl\": 120"
                + "}";
        assertThat(cl.getResponse(url), is(expected));
    }
}