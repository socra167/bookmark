package com.socra.bookmark.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.socra.bookmark.domain.BookmarkPlaylist;

public interface BookmarkPlaylistRepository extends JpaRepository<BookmarkPlaylist, Long> {
}
