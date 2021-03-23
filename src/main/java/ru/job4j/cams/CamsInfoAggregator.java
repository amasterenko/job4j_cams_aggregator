package ru.job4j.cams;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Collects camera data from a start url in multithreaded mode
 * @author AndrewMs
 * @version 1.0
 */
public class CamsInfoAggregator {
    private final HttpClient client = HttpClient.of();
    private ExecutorService threadPool =
            Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    private final ConcurrentHashMap<Integer, CamSumInfo> results = new ConcurrentHashMap<>();

    /**
     * Runs url-parsers in a thread pool
     * @param startUrl Start url
     * @throws IOException IOException
     */

    public void start(String startUrl) throws IOException {
        if (threadPool.isShutdown()) {
            threadPool = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        }
        CamUrlInfo[] camUrlInfos =
                new Gson().fromJson(client.getResponse(startUrl), CamUrlInfo[].class);
        for (CamUrlInfo camUrlInfo : camUrlInfos) {
            int camId = camUrlInfo.getId();
            String sourceDataUrl = camUrlInfo.getSourceDataUrl();
            String tokenDataUrl = camUrlInfo.getTokenDataUrl();
            results.put(camId, new CamSumInfo(camId));
            SourceParser sourceParser = SourceParser.of(sourceDataUrl, camId, results);
            TokenParser tokenParser = TokenParser.of(tokenDataUrl, camId, results);
            threadPool.execute(sourceParser);
            threadPool.execute(tokenParser);
        }
        threadPool.shutdown();
    }

    /**
     * Returns a status of tasks
     * @return True if tasks are not finished
     */
    public boolean isRunning() {
        return !threadPool.isTerminated();
    }

    /**
     * Returns aggregated data in a json "pretty printing" string format
     * @return Json string
     */
    public String getAggregatedCams() {
        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        //Gson gson = new Gson();
        return gson.toJson(results.values());
    }

    /**
     * Cancels tasks
     */
    public void cancel() {
        threadPool.shutdownNow();
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        String url = "http://www.mocky.io/v2/5c51b9dd3400003252129fb5";
        CamsInfoAggregator agr = new CamsInfoAggregator();
        agr.start(url);
        while (agr.isRunning()) {
            Thread.sleep(100);
        }
        System.out.println(agr.getAggregatedCams());
    }
}
