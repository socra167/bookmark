package com.socra.bookmark.controller;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.socra.bookmark.domain.BookmarkGroup;
import com.socra.bookmark.service.BookmarkGroupService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/bookmarkgroup")
public class BookmarkGroupController {
	private final BookmarkGroupService bookmarkGroupService;

	@PostMapping()
	public ResponseEntity<BookmarkGroup> createBookmarkGroup() {
		BookmarkGroup bookmarkGroup = bookmarkGroupService.createBookmarkGroup();
		return ResponseEntity.created(URI.create("/api/bookmarkgroup/" + bookmarkGroup.getId()))
			.body(bookmarkGroup);
	}

	@GetMapping("/")
	public ResponseEntity<List<BookmarkGroup>> getAllBookmarkGroups() {
		return ResponseEntity.ok(bookmarkGroupService.findAllBookmarkGroups());
	}
}
