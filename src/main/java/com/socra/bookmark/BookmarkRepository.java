package com.socra.bookmark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {

    Optional<Bookmark> findById(Long id);
    boolean existsById(Long id);
    List<Bookmark> findAll();
    boolean existsByUri(URI uri);
    void deleteByUri(URI uri);
}
