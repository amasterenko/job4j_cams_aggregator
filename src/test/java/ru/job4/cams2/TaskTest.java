package ru.job4.cams2;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TaskTest {
    /**
     * URL = http://www.mocky.io/v2/5c51b230340000094f129f5d
     * Response:
     * {
     *     "urlType": "LIVE",
     *     "videoUrl": "rtsp://127.0.0.1/1"
     * }
     * Task class doesn't return strings containing parentheses
     */
    @Test
    public void whenParseSourceData() {
        String url = "http://www.mocky.io/v2/5c51b230340000094f129f5d";
        Task task = new Task(url);
        List<String> expected = new ArrayList<>();
        expected.add("    \"urlType\": \"LIVE\",");
        expected.add("    \"videoUrl\": \"rtsp://127.0.0.1/1\"");
        assertThat(task.call(), is(expected));
    }
}