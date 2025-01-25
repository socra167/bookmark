package com.socra.bookmark.service;

import static org.assertj.core.api.Assertions.*;

import java.net.URI;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.socra.bookmark.domain.Bookmark;
import com.socra.bookmark.domain.BookmarkGroup;
import com.socra.bookmark.repository.BookmarkGroupRepository;

@ActiveProfiles("test")
@SpringBootTest
class BookmarkGroupServiceTest {

	@Autowired
	private BookmarkGroupService bookmarkGroupService;

	@Autowired
	private BookmarkGroupRepository bookmarkGroupRepository;

	@Autowired
	private BookmarkService bookmarkService;

	@Test
	@Transactional
	@DisplayName("북마크 그룹을 생성할 수 있다")
	void createBookmarkGroup() {
		bookmarkGroupService.createBookmarkGroup();
		assertThat(bookmarkGroupRepository.findAll()).hasSize(1);
	}

	@Test
	@Transactional
	@DisplayName("북마크 그룹에 북마크를 추가할 수 있다")
	void addBookmarkToBookmarkGroup() {
		Bookmark bookmark1 = Bookmark.builder()
			.uri(URI.create("https://www.google.com"))
			.name("Google")
			.description("google description")
			.build();
		Bookmark bookmark2 = Bookmark.builder()
			.uri(URI.create("https://www.naver.com"))
			.name("naver")
			.description("naver description")
			.build();
		bookmark1 = bookmarkService.saveBookmark(bookmark1);
		bookmark2 = bookmarkService.saveBookmark(bookmark2);
		bookmarkGroupService.createBookmarkGroup();
		BookmarkGroup bookmarkGroup = bookmarkGroupService.findBookmarkGroupById(1L);
		bookmarkGroup.addBookmark(bookmark1);
		bookmarkGroup.addBookmark(bookmark2);

		assertThat(bookmarkGroup.getBookmarks()).containsExactly(bookmark1, bookmark2);
	}
}