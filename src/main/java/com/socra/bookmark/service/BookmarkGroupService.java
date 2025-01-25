package com.socra.bookmark.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.socra.bookmark.domain.BookmarkGroup;
import com.socra.bookmark.repository.BookmarkGroupRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookmarkGroupService {
	private final BookmarkGroupRepository bookmarkGroupRepository;

	public List<BookmarkGroup> findAllBookmarkGroup() {
		return bookmarkGroupRepository.findAll();
	}

	public BookmarkGroup createBookmarkGroup() {
		BookmarkGroup bookmarkGroup = BookmarkGroup.builder().name("bookmark group").build();
		return bookmarkGroupRepository.save(bookmarkGroup);
	}

	public List<BookmarkGroup> findAllBookmarkGroups() {
		return bookmarkGroupRepository.findAll();
	}

	public BookmarkGroup findBookmarkGroupById(Long id) {
		return bookmarkGroupRepository.findById(id).orElseThrow(() -> new UnsupportedOperationException());
	}
}
