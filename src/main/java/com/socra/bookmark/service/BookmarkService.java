package com.socra.bookmark.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.Optional;

import com.socra.bookmark.domain.Bookmark;
import com.socra.bookmark.repository.BookmarkRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class BookmarkService {
    private final BookmarkRepository bookmarkRepository;

    @Transactional
    public Bookmark saveBookmark(Bookmark bookmark) {
        return bookmarkRepository.save(bookmark);
    }

    @Transactional(readOnly = true)
    public Optional<Bookmark> findBookmarkById(Long id) {
        return bookmarkRepository.findById(id);
    }

    @Transactional(readOnly = true)
    public List<Bookmark> findAllBookmark() {
        return bookmarkRepository.findAll();
    }

    @Transactional
    public void deleteBookmarkById(Long id) {
        bookmarkRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByUri(URI uri) {
        return bookmarkRepository.existsByUri(uri);
    }

    @Transactional
    public void deleteBookmarkByUri(URI uri) {
        bookmarkRepository.deleteByUri(uri);
    }
}
