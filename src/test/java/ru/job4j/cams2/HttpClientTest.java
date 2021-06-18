package ru.job4j.cams2;

import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class HttpClientTest {
    @Test
    public void whenGetCamsUrl() throws IOException {
        String url = "http://www.mocky.io/v2/5c51b9dd3400003252129fb5";
        HttpClient cl = new HttpClient(url);
        List<String> expected = new ArrayList<>();
        expected.add("[");
        expected.add("    {");
        expected.add("        \"id\": 1,");
        expected.add(
                "        \"sourceDataUrl\": \"http://www.mocky.io/v2/5c51b230340000094f129f5d\",");
        expected.add(
                "        \"tokenDataUrl\": "
                        + "\"http://www.mocky.io/v2/5c51b5b6340000554e129f7b?mocky-delay=1s\"");
        expected.add("    },");
        expected.add("    {");
        expected.add("        \"id\": 20,");
        expected.add("        \"sourceDataUrl\": "
                + "\"http://www.mocky.io/v2/5c51b2e6340000a24a129f5f?mocky-delay=100ms\",");
        expected.add("        \"tokenDataUrl\": "
                + "\"http://www.mocky.io/v2/5c51b5ed340000554e129f7e\"");
        expected.add("    },");
        expected.add("    {");
        expected.add("        \"id\": 3,");
        expected.add("        \"sourceDataUrl\": "
                + "\"http://www.mocky.io/v2/5c51b4b1340000074f129f6c\",");
        expected.add("        \"tokenDataUrl\": "
                + "\"http://www.mocky.io/v2/5c51b600340000514f129f7f?mocky-delay=2s\"");
        expected.add("    },");
        expected.add("    {");
        expected.add("        \"id\": 2,");
        expected.add("        \"sourceDataUrl\": "
                + "\"http://www.mocky.io/v2/5c51b5023400002f4f129f70\",");
        expected.add("        \"tokenDataUrl\": "
                + "\"http://www.mocky.io/v2/5c51b623340000404f129f82\"");
        expected.add("    }");
        expected.add("]");
        assertThat(cl.getResponse(), is(expected));
    }

    @Test
    public void whenSourceDataUrl() throws IOException {
        String url = "http://www.mocky.io/v2/5c51b230340000094f129f5d";
        HttpClient cl = new HttpClient(url);
        List<String> expected = new ArrayList<>();
        expected.add("{");
        expected.add("    \"urlType\": \"LIVE\",");
        expected.add("    \"videoUrl\": \"rtsp://127.0.0.1/1\"");
        expected.add("}");
        assertThat(cl.getResponse(), is(expected));
    }

    @Test
    public void whenTokenDataUrl() throws IOException {
        String url = "http://www.mocky.io/v2/5c51b5b6340000554e129f7b?mocky-delay=1s";
        HttpClient cl = new HttpClient(url);
        List<String> expected = new ArrayList<>();
        expected.add("{");
        expected.add("    \"value\": \"fa4b588e-249b-11e9-ab14-d663bd873d93\",");
        expected.add("    \"ttl\": 120");
        expected.add("}");
        assertThat(cl.getResponse(), is(expected));
    }
}