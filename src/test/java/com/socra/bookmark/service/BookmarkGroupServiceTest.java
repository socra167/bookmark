package com.socra.bookmark.service;

import static org.assertj.core.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.socra.bookmark.repository.BookmarkGroupRepository;

@ActiveProfiles("test")
@SpringBootTest
class BookmarkGroupServiceTest {

	@Autowired
	private BookmarkGroupService bookmarkGroupService;

	@Autowired
	private BookmarkGroupRepository bookmarkGroupRepository;

	@Test
	@DisplayName("북마크 그룹을 생성할 수 있다")
	void createBookmarkGroup() {
		bookmarkGroupService.createBookmarkGroup();
		assertThat(bookmarkGroupRepository.findAll()).hasSize(1);
	}

}