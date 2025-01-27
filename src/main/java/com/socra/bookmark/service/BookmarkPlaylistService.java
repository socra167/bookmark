package com.socra.bookmark.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.socra.bookmark.domain.Bookmark;
import com.socra.bookmark.domain.BookmarkPlaylist;
import com.socra.bookmark.repository.BookmarkPlaylistRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookmarkPlaylistService {

	private final BookmarkPlaylistRepository bookmarkPlaylistRepository;

	@Transactional(readOnly = true)
	public List<BookmarkPlaylist> findAllBookmarkPlaylist() {
		return bookmarkPlaylistRepository.findAll();
	}

	@Transactional
	public BookmarkPlaylist createBookmarkPlaylist() {
		BookmarkPlaylist bookmarkPlaylist = BookmarkPlaylist.builder().name("bookmark group").build();
		return bookmarkPlaylistRepository.save(bookmarkPlaylist);
	}

	@Transactional(readOnly = true)
	public BookmarkPlaylist findBookmarkPlaylistById(Long id) {
		return bookmarkPlaylistRepository.findById(id).orElseThrow(() -> new UnsupportedOperationException());
	}

	@Transactional
	public void addBookmark(BookmarkPlaylist bookmarkPlaylist, Bookmark bookmark) {
		bookmarkPlaylist.addBookmark(bookmark);
	}

	@Transactional
	public void updateName(BookmarkPlaylist bookmarkPlaylist, String newName) {
		bookmarkPlaylist.updateName(newName);
	}

	@Transactional
	public void removeBookmark(BookmarkPlaylist bookmarkPlaylist, Bookmark bookmark) {
		bookmarkPlaylist.removeBookmark(bookmark);
	}

	@Transactional
	public void changeOrderOfBookmark(BookmarkPlaylist bookmarkPlaylist, int fromIndex, int destIndex) {
		bookmarkPlaylist.changeOrderOfBookmark(fromIndex, destIndex);
	}
}
