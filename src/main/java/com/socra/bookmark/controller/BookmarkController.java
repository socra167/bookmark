package com.socra.bookmark.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

import com.socra.bookmark.domain.Bookmark;
import com.socra.bookmark.service.BookmarkService;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/bookmarks")
public class BookmarkController {

	private final BookmarkService bookmarkService;

	@GetMapping
	public ResponseEntity<List<Bookmark>> getAllBookmarks() {
		List<Bookmark> bookmarks = bookmarkService.findAllBookmark();
		return ResponseEntity.ok(bookmarks);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Bookmark> getBookmarkById(@PathVariable("id") Long id) {
		var bookmark = bookmarkService.findBookmarkById(id);
		return bookmark.map(ResponseEntity::ok)
			.orElseGet(ResponseEntity.notFound()::build);
	}

	@GetMapping("/exists")
	public boolean existByUri(@RequestParam("uri") String uri) {
		try {
			return bookmarkService.existsByUri(new URI(uri));
		} catch (Exception e) {
			throw new IllegalArgumentException("Invalid URI format", e);
		}
	}

	@PostMapping
	public ResponseEntity<Bookmark> createBookmark(@RequestBody Bookmark bookmark) {
		bookmarkService.saveBookmark(bookmark);
		return ResponseEntity.created(URI.create("/api/bookmarks/" + bookmark.getId()))
			.body(bookmark);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Bookmark> updateBookmark(@PathVariable long id, @RequestBody Bookmark newBookmark) {
		var bookmark = bookmarkService.findBookmarkById(id).get();
		bookmarkService.updateBookmark(bookmark, newBookmark);
		return ResponseEntity.ok(bookmark);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBookmarkById(@PathVariable("id") Long id) {
		if (!bookmarkService.findBookmarkById(id).isPresent()) {
			return ResponseEntity.notFound().build();
		}
		bookmarkService.deleteBookmarkById(id);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping
	public ResponseEntity<Void> deleteBookmarkByUri(@RequestParam("uri") String uriInput) {
		URI uri = URI.create(uriInput);
		if (!bookmarkService.existsByUri(uri)) {
			return ResponseEntity.notFound().build();
		}
		bookmarkService.deleteBookmarkByUri(uri);
		return ResponseEntity.noContent().build();
	}
}
