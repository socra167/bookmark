package com.socra.bookmark.controller;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;

import com.socra.bookmark.domain.BookmarkPlaylist;
import com.socra.bookmark.repository.BookmarkPlaylistRepository;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookmarkPlaylistControllerIntegrationTest {

	@Autowired
	private TestRestTemplate restTemplate;

	@Autowired
	private BookmarkPlaylistRepository bookmarkPlaylistRepository;

	@Test
	@DisplayName("북마크 그룹을 생성할 수 있다")
	void createBookmarkPlaylist() {
		BookmarkPlaylist bookmarkPlaylist = BookmarkPlaylist.builder().name("first bookmark group").build();

		ResponseEntity<BookmarkPlaylist> response = restTemplate.postForEntity("/api/bookmarks/playlists",
			bookmarkPlaylist, BookmarkPlaylist.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(bookmarkPlaylistRepository.existsById(response.getBody().getId())).isTrue();
	}
}