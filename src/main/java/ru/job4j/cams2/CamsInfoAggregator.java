package ru.job4j.cams2;

import java.io.IOException;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * Collects camera data from the start URL in multithreaded mode.
 *
 * @author AndrewMs
 * @version 2.0
 */
public class CamsInfoAggregator {
    private final String startUrl;

    public CamsInfoAggregator(String startUrl) {
        this.startUrl = startUrl;
    }

    /**
     * Parses first page with the source and the token data URLs.
     * Runs an asynchronous task for each URL.
     * Collects the results into a string.
     * @return List of strings with aggregated cam's data.
     */
    public String getInfo() {
        HttpClient client = new HttpClient(startUrl);
        ExecutorService threadPool =
                Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        LinkedList<FutureTask<List<String>>> futureTasks = new LinkedList<>();
        StringBuilder camsSumInfo = new StringBuilder();
        List<String> camsInfo;
        try {
            camsInfo = client.getResponse();
            for (String str : camsInfo) {
                if (str.contains("Url")) {
                    String url = str.strip().split(": ")[1].split("\"")[1];
                    FutureTask<List<String>> futureTask = new FutureTask<>(new Task(url));
                    threadPool.submit(futureTask);
                    futureTasks.add(futureTask);
                }
            }
            for (String str : camsInfo) {
                if (str.contains("sourceDataUrl")) {
                    List<String> strings = futureTasks.poll().get(5, TimeUnit.SECONDS);
                    camsSumInfo.append("    ").append(strings.get(0)).append("\n");
                    camsSumInfo.append("    ").append(strings.get(1)).append(",").append("\n");
                    continue;
                }
                if (str.contains("tokenDataUrl")) {
                    List<String> strings = futureTasks.poll().get(5, TimeUnit.SECONDS);
                    camsSumInfo.append("    ").append(strings.get(0)).append("\n");
                    camsSumInfo.append("    ").append(strings.get(1)).append("\n");
                    continue;
                }
                camsSumInfo.append(str).append("\n");
            }
        } catch (IOException | ExecutionException | InterruptedException | TimeoutException e) {
            e.printStackTrace();
        }
        threadPool.shutdownNow();
        return camsSumInfo.toString();
    }

    public static void main(String[] args)  {
        if (args.length == 0) {
            System.out.println("Please, specify the start URL as parameter.");
            return;
        }
        System.out.println("Task started..");
        CamsInfoAggregator agr = new CamsInfoAggregator(args[0]);
        System.out.println(agr.getInfo());
    }
}
