package com.socra.bookmark.service;

import org.springframework.stereotype.Service;

import com.socra.bookmark.domain.BookmarkGroup;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookmarkGroupService {
	private final BookmarkGroupRepository bookmarkGroupRepository;

	public BookmarkGroup saveBookmarkGroup(BookmarkGroup bookmarkGroup) {
		return bookmarkGroupRepository.save(bookmarkGroup);
	}
}
