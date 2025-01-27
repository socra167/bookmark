package com.socra.bookmark.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socra.bookmark.domain.BookmarkPlaylist;
import com.socra.bookmark.service.BookmarkPlaylistService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bookmarks/playlists")
public class BookmarkPlaylistController {

	private final BookmarkPlaylistService bookmarkPlaylistService;

	@PostMapping()
	public ResponseEntity<BookmarkPlaylist> createBookmarkPlaylist() {
		BookmarkPlaylist bookmarkPlaylist = bookmarkPlaylistService.createBookmarkPlaylist();
		return ResponseEntity.created(URI.create("/api/bookmarks/playlists/" + bookmarkPlaylist.getId()))
			.body(bookmarkPlaylist);
	}

	@GetMapping("/")
	public ResponseEntity<List<BookmarkPlaylist>> getAllBookmarkPlaylist() {
		return ResponseEntity.ok(bookmarkPlaylistService.findAllBookmarkPlaylist());
	}
}
