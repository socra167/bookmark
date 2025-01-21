package com.socra.bookmark.controller;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.socra.bookmark.domain.BookmarkGroup;
import com.socra.bookmark.repository.BookmarkGroupRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookmarkGroupControllerIntegrationTest {
	@Autowired private TestRestTemplate restTemplate;
	@Autowired private BookmarkGroupRepository bookmarkGroupRepository;

	@Test
	@DisplayName("북마크 그룹을 생성할 수 있다")
	void createBookmarkGroup() {
		BookmarkGroup bookmarkGroup = BookmarkGroup.builder()
			.name("first bookmark group")
			.build();

		ResponseEntity<BookmarkGroup> response = restTemplate.postForEntity("/api/bookmarkgroup", bookmarkGroup, BookmarkGroup.class);

		assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
		assertThat(bookmarkGroupRepository.existsById(response.getBody().getId())).isTrue();
	}
}