package ru.job4j.cams;

import org.junit.Test;

import java.io.IOException;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class CamsInfoAggregatorTest {
    /**
     * URL = http://www.mocky.io/v2/5c51b9dd3400003252129fb5
     */
    @Test
    public void whenCamsAggregateComplete() throws IOException, InterruptedException {
        String url = "http://www.mocky.io/v2/5c51b9dd3400003252129fb5";
        String expected = "[\n"
                + "  {\n"
                + "    \"id\": 1,\n"
                + "    \"urlType\": \"LIVE\",\n"
                + "    \"videoUrl\": \"rtsp://127.0.0.1/1\",\n"
                + "    \"value\": \"fa4b588e-249b-11e9-ab14-d663bd873d93\",\n"
                + "    \"ttl\": 120\n"
                + "  },\n"
                + "  {\n"
                + "    \"id\": 2,\n"
                + "    \"urlType\": \"LIVE\",\n"
                + "    \"videoUrl\": \"rtsp://127.0.0.1/20\",\n"
                + "    \"value\": \"fa4b5f64-249b-11e9-ab14-d663bd873d93\",\n"
                + "    \"ttl\": 180\n"
                + "  },\n"
                + "  {\n"
                + "    \"id\": 3,\n"
                + "    \"urlType\": \"ARCHIVE\",\n"
                + "    \"videoUrl\": \"rtsp://127.0.0.1/3\",\n"
                + "    \"value\": \"fa4b5d52-249b-11e9-ab14-d663bd873d93\",\n"
                + "    \"ttl\": 120\n"
                + "  },\n"
                + "  {\n"
                + "    \"id\": 20,\n"
                + "    \"urlType\": \"ARCHIVE\",\n"
                + "    \"videoUrl\": \"rtsp://127.0.0.1/2\",\n"
                + "    \"value\": \"fa4b5b22-249b-11e9-ab14-d663bd873d93\",\n"
                + "    \"ttl\": 60\n"
                + "  }\n"
                + "]";
        CamsInfoAggregator aggregator = new CamsInfoAggregator();
        aggregator.start(url);
        while (aggregator.isRunning()) {
            Thread.sleep(100);
        }
        assertThat(aggregator.getAggregatedCams(), is(expected));
    }
}