package com.socra.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socra.bookmark.domain.BookmarkGroup;

public interface BookmarkGroupRepository extends JpaRepository<BookmarkGroup, Long> {
}
