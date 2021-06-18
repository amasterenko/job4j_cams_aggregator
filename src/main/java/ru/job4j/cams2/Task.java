package ru.job4j.cams2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Collectors;

/**
 * The class is using for asynchronous getting the content of sourceDataUrl and tokenDataUrl.
 *
 * @author AndrewMs
 * @version 2.0
 */
public class Task implements Callable<List<String>> {
    private final String url;

    public Task(String url) {
        this.url = url;
    }

    /**
     * Gets content from the URL.
     * @return List of strings without parentheses.
     */

    @Override
    public List<String> call() {
        List<String> result = new ArrayList<>();
        HttpClient thrClient = new HttpClient(url);
        try {
            result.addAll(thrClient.getResponse().stream()
                    .filter(s -> !s.equals("{") && !s.equals("}"))
                    .collect(Collectors.toList()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
