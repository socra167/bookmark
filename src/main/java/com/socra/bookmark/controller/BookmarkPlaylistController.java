package com.socra.bookmark.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socra.bookmark.domain.BookmarkPlaylist;
import com.socra.bookmark.service.BookmarkPlaylistService;
import com.socra.bookmark.service.BookmarkService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bookmarks/playlists")
public class BookmarkPlaylistController {

	private final BookmarkPlaylistService bookmarkPlaylistService;
	private final BookmarkService bookmarkService;

	@PostMapping
	public ResponseEntity<BookmarkPlaylist> createBookmarkPlaylist() {
		BookmarkPlaylist bookmarkPlaylist = bookmarkPlaylistService.createBookmarkPlaylist();
		return ResponseEntity.created(URI.create("/api/bookmarks/playlists/" + bookmarkPlaylist.getId()))
			.body(bookmarkPlaylist);
	}

	@GetMapping
	public ResponseEntity<List<BookmarkPlaylist>> getAllBookmarkPlaylist() {
		return ResponseEntity.ok(bookmarkPlaylistService.findAllBookmarkPlaylist());
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookmarkPlaylist> getBookmarkPlaylist(@PathVariable long id) {
		return ResponseEntity.ok(bookmarkPlaylistService.findBookmarkPlaylistById(id));
	}

	@PostMapping("/{id}")
	public ResponseEntity addBookmarkToBookmarkPlaylist(@PathVariable long id, @RequestBody long bookmarkId) {
		var bookmarkPlaylist = bookmarkPlaylistService.findBookmarkPlaylistById(id);
		var bookmark = bookmarkService.findBookmarkById(bookmarkId);
		bookmarkPlaylistService.addBookmark(bookmarkPlaylist,
			bookmark.orElseThrow(() -> new UnsupportedOperationException()));
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity updateBookmarkPlaylistName(@PathVariable long id, @RequestBody String name) {
		var bookmarkPlaylist = bookmarkPlaylistService.findBookmarkPlaylistById(id);
		bookmarkPlaylistService.updateName(bookmarkPlaylist, name);
		return ResponseEntity.ok().build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity deleteBookmarkFromBookmarkPlaylist(@PathVariable long id, @RequestBody long bookmarkId) {
		var bookmarkPlaylist = bookmarkPlaylistService.findBookmarkPlaylistById(id);
		var bookmark = bookmarkService.findBookmarkById(bookmarkId)
			.orElseThrow(() -> new UnsupportedOperationException());
		bookmarkPlaylistService.removeBookmark(bookmarkPlaylist, bookmark);
		return ResponseEntity.ok().build();
	}

	@PatchMapping("/{id}/order")
	public ResponseEntity changeOrderOfBookmarksFromBookmarkPlaylist(@PathVariable long id, @RequestBody int fromIndex,
		@RequestBody int destIndex) {
		var bookmarkPlaylist = bookmarkPlaylistService.findBookmarkPlaylistById(id);
		bookmarkPlaylistService.changeOrderOfBookmark(bookmarkPlaylist, fromIndex, destIndex);
		return ResponseEntity.ok().build();
	}
}
