package com.socra.bookmark;

import java.io.IOException;
import java.net.URI;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class MetaData {
    private String title;
    private String description;

    public static class MetaDataExtractor {

        public static MetaData getMetaDataFromUri(URI uri) {
            try {
                Document document = Jsoup.connect(uri.toString()).get();
                String title = document.title();
                Element descriptionMeta = document.selectFirst("meta[name=description]");
                String description = descriptionMeta != null ? descriptionMeta.attr("content") : "No description available";
                return new MetaData(title, description);
            } catch (IOException e) {
                e.printStackTrace();
                return new MetaData("Error", "Could not fetch metadata");
            }
        }
    }
}
