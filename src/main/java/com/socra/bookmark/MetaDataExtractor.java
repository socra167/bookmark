package com.socra.bookmark;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.net.URI;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MetaDataExtractor {
    public static MetaData getMetaDataFromUri(URI uri) {
        try {
            // HTML
            Document document = Jsoup.connect(uri.toString()).get();

            // Title
            String title = document.title();

            // Description
            Element descriptionMeta = document.selectFirst("meta[name=description]");
            String description = descriptionMeta != null ? descriptionMeta.attr("content") : "No description available";

            // 결과 반환
            return new MetaData(title, description);

        } catch (IOException e) {
            e.printStackTrace();
            return new MetaData("Error", "Could not fetch metadata");
        }
    }
}
