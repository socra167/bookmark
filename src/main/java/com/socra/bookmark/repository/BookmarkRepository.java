package com.socra.bookmark.repository;

import java.net.URI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.socra.bookmark.domain.Bookmark;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

	boolean existsByUri(URI uri);

	void deleteByUri(URI uri);

}
