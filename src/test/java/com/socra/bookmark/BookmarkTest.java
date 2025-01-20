package com.socra.bookmark;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;

import com.socra.bookmark.domain.Bookmark;

class BookmarkTest {
    SoftAssertions softly = new SoftAssertions();

    @Test
    @DisplayName("북마크를 생성할 수 있다.")
    void create_bookmark() throws URISyntaxException {
        String name = "Google";
        URI uri = new URI("https://www.google.com/");
        Bookmark bookmark = Bookmark.builder()
                .name(name)
                .uri(uri).build();

        softly.assertThat(bookmark.getName()).isEqualTo(name);
        softly.assertThat(bookmark.getUri()).isEqualTo(uri);
        softly.assertAll();
    }

    @Test
    @DisplayName("북마크의 정보를 수정할 수 있다.")
    void update_bookmark() throws URISyntaxException {
        String name = "Google";
        URI uri = new URI("https://www.google.com/");
        String newName = "Github";
        URI newUri = new URI("https://github.com/");
        Bookmark bookmark = Bookmark.builder()
                .name(name)
                .uri(uri).build();

        bookmark.updateName(newName);
        bookmark.updateUri(newUri);

        softly.assertThat(bookmark.getName()).isEqualTo(newName);
        softly.assertThat(bookmark.getUri()).isEqualTo(newUri);
        softly.assertAll();
    }
}
