package com.socra.bookmark;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MetaData {
    private String title;
    private String description;

    public MetaData(String title, String description) {
        this.title = title;
        this.description = description;
    }
}
