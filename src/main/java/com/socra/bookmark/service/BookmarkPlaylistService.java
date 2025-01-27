package com.socra.bookmark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.socra.bookmark.domain.BookmarkPlaylist;
import com.socra.bookmark.repository.BookmarkPlaylistRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookmarkPlaylistService {

	private final BookmarkPlaylistRepository bookmarkPlaylistRepository;

	public List<BookmarkPlaylist> findAllBookmarkPlaylist() {
		return bookmarkPlaylistRepository.findAll();
	}

	public BookmarkPlaylist createBookmarkPlaylist() {
		BookmarkPlaylist bookmarkPlaylist = BookmarkPlaylist.builder().name("bookmark group").build();
		return bookmarkPlaylistRepository.save(bookmarkPlaylist);
	}

	public BookmarkPlaylist findBookmarkPlaylistById(Long id) {
		return bookmarkPlaylistRepository.findById(id).orElseThrow(() -> new UnsupportedOperationException());
	}
}
