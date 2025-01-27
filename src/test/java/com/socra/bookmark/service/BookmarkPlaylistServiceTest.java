package com.socra.bookmark.service;

import static org.assertj.core.api.Assertions.*;

import java.net.URI;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import com.socra.bookmark.domain.Bookmark;
import com.socra.bookmark.domain.BookmarkPlaylist;
import com.socra.bookmark.repository.BookmarkPlaylistRepository;

@ActiveProfiles("test")
@SpringBootTest
class BookmarkPlaylistServiceTest {

	@Autowired
	private BookmarkPlaylistService bookmarkPlaylistService;

	@Autowired
	private BookmarkPlaylistRepository bookmarkPlaylistRepository;

	@Autowired
	private BookmarkService bookmarkService;

	@Test
	@Transactional
	@DisplayName("북마크 플레이리스트를 생성할 수 있다")
	void createBookmarkPlaylist() {
		bookmarkPlaylistService.createBookmarkPlaylist();
		assertThat(bookmarkPlaylistRepository.findAll()).hasSize(1);
	}

	@Test
	@Transactional
	@DisplayName("북마크 플레이리스트에 북마크를 추가할 수 있다")
	void addBookmarkToBookmarkPlaylist() {
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
		bookmarkPlaylistService.createBookmarkPlaylist();
		BookmarkPlaylist bookmarkPlaylist = bookmarkPlaylistService.findBookmarkPlaylistById(1L);
		bookmarkPlaylist.addBookmark(bookmark1);
		bookmarkPlaylist.addBookmark(bookmark2);

		assertThat(bookmarkPlaylist.getBookmarks()).containsExactly(bookmark1, bookmark2);
	}

	@Test
	@Transactional
	@DisplayName("북마크 플레이리스트의 이름을 수정할 수 있다")
	void updateBookmarkPlaylistName() {
		BookmarkPlaylist bookmarkPlaylist = bookmarkPlaylistService.createBookmarkPlaylist();
		var newName = "new name";
		bookmarkPlaylist.updateName(newName);

		assertThat(bookmarkPlaylist.getName()).isEqualTo(newName);
	}

	@Test
	@Transactional
	@DisplayName("모든 북마크 플레이리스트를 조회할 수 있다")
	void findAllBookmarkPlaylist() {
		bookmarkPlaylistService.createBookmarkPlaylist();
		bookmarkPlaylistService.createBookmarkPlaylist();
		bookmarkPlaylistService.createBookmarkPlaylist();

		List<BookmarkPlaylist> bookmarkPlaylists = bookmarkPlaylistService.findAllBookmarkPlaylist();

		assertThat(bookmarkPlaylists).hasSize(3);
	}

	@Test
	@DisplayName("북마크 플레이리스트에서 북마크를 삭제할 수 있다")
	void removeBookmarkFromBookmarkPlaylist() {
		var bookmarkPlaylist = bookmarkPlaylistService.createBookmarkPlaylist();
		var bookmark1 = bookmarkService.saveBookmark(Bookmark.builder()
			.uri(URI.create("https://www.google.com"))
			.name("Google")
			.description("google description")
			.build());
		var bookmark2 = bookmarkService.saveBookmark(Bookmark.builder()
			.uri(URI.create("https://www.naver.com"))
			.name("naver")
			.description("naver description")
			.build());
		bookmarkPlaylist.addBookmark(bookmark1);
		bookmarkPlaylist.addBookmark(bookmark2);

		bookmarkPlaylist.removeBookmark(bookmark1);

		assertThat(bookmarkPlaylist.getBookmarks()).hasSize(1);
	}

	@Test
	@DisplayName("북마크 플레이리스트에서 북마크의 순서를 변경할 수 있다. ")
	void changeOrderOfBookmarksFromBookmarkPlaylist() {
		var bookmarkPlaylist = bookmarkPlaylistService.createBookmarkPlaylist();
		var bookmark1 = bookmarkService.saveBookmark(Bookmark.builder()
			.uri(URI.create("https://www.google.com"))
			.name("Google")
			.description("google description")
			.build());
		var bookmark2 = bookmarkService.saveBookmark(Bookmark.builder()
			.uri(URI.create("https://www.naver.com"))
			.name("naver")
			.description("naver description")
			.build());
		var bookmark3 = bookmarkService.saveBookmark(Bookmark.builder()
			.uri(URI.create("https://www.youtube.com"))
			.name("Youtube")
			.description("youtube description")
			.build());
		bookmarkPlaylist.addBookmark(bookmark1);
		bookmarkPlaylist.addBookmark(bookmark2);
		bookmarkPlaylist.addBookmark(bookmark3);

		bookmarkPlaylist.changeOrderOfBookmark(2, 0);

		assertThat(bookmarkPlaylist.getBookmarks()).containsExactly(bookmark3, bookmark1, bookmark2);
	}
}