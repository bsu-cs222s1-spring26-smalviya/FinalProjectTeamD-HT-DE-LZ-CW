package bsu.edu.cs.APIApps.USDAClient;


import org.json.JSONException;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

public class USDAToJsonClient {
    public String getConnection(String articleTitle) throws IOException, JSONException {
        String urlString = getURLString(articleTitle);
        URL url = new URL(urlString);
        URLConnection connection = url.openConnection();
        connection.setRequestProperty("User-Agent",
                "MediaWikiReader; HumbertoTorres(humberto.torres@bsu.edu)");
        try (InputStream inputStream = connection.getInputStream()) {
            return new String(inputStream.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    private String getURLString(String articleTitle) {
        String encodedTitle = URLEncoder.encode(articleTitle, java.nio.charset.StandardCharsets.UTF_8);
        String urlString = String.format(
                "https://en.wikipedia.org/w/api.php?action=query&format=json&prop=revisions&titles=%s&rvprop=timestamp|user&rvlimit=15&redirects",
                encodedTitle);
        return urlString;
    }
}
