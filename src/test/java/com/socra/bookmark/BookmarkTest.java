package com.socra.bookmark;

import org.assertj.core.api.SoftAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDate;

import com.socra.bookmark.domain.Bookmark;

class BookmarkTest {
    SoftAssertions softly = new SoftAssertions();

    @Test
    @DisplayName("북마크를 생성할 수 있다.")
    void create_bookmark() throws URISyntaxException {
        String name = "Google";
        URI uri = new URI("https://www.google.com/");
        LocalDate date = LocalDate.of(2024, 1, 1);
        Bookmark bookmark = Bookmark.builder()
                .name(name)
                .uri(uri)
                .date(date).build();

        softly.assertThat(bookmark.getName()).isEqualTo(name);
        softly.assertThat(bookmark.getUri()).isEqualTo(uri);
        softly.assertThat(bookmark.getDate()).isEqualTo(date);
        softly.assertAll();
    }

    @Test
    @DisplayName("북마크의 정보를 수정할 수 있다.")
    void update_bookmark() throws URISyntaxException {
        String name = "Google";
        URI uri = new URI("https://www.google.com/");
        LocalDate date = LocalDate.of(2024, 1, 1);
        String newName = "Github";
        URI newUri = new URI("https://github.com/");
        LocalDate newDate = LocalDate.of(2024, 12, 12);
        Bookmark bookmark = Bookmark.builder()
                .name(name)
                .uri(uri)
                .date(date).build();

        bookmark.updateName(newName);
        bookmark.updateUri(newUri);
        bookmark.updateDate(newDate);

        softly.assertThat(bookmark.getName()).isEqualTo(newName);
        softly.assertThat(bookmark.getUri()).isEqualTo(newUri);
        softly.assertThat(bookmark.getDate()).isEqualTo(newDate);
        softly.assertAll();
    }
}
