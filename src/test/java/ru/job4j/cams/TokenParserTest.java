package ru.job4j.cams;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class TokenParserTest {
    /**
     * URL = http://www.mocky.io/v2/5c51b5b6340000554e129f7b?mocky-delay=1s
     * Response:
     * {
     *     "value": "fa4b588e-249b-11e9-ab14-d663bd873d93",
     *     "ttl": 120
     * }
     */
    @Test
    public void whenParseTokenData() {
        String url = "http://www.mocky.io/v2/5c51b5b6340000554e129f7b?mocky-delay=1s";
        Map<Integer, CamSumInfo> map = new HashMap<>();
        map.put(1, new CamSumInfo(1));
        TokenParser parser = TokenParser.of(url, 1, map);
        parser.run();
        assertThat(map.get(1).getValue(), is("fa4b588e-249b-11e9-ab14-d663bd873d93"));
        assertThat(map.get(1).getTtl(), is(120));
    }
}