package com.socra.bookmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class BookmarkService {

    private final BookmarkRepository bookmarkRepository;

    @Autowired
    public BookmarkService(BookmarkRepository bookmarkRepository) {
        this.bookmarkRepository = bookmarkRepository;
    }

    public void saveBookmark(Bookmark bookmark) {
        bookmarkRepository.save(bookmark);
    }

    public Optional<Bookmark> findBookmarkById(Long id) {
        return bookmarkRepository.findById(id);
    }

    public List<Bookmark> findAllBookmark() {
        return bookmarkRepository.findAll();
    }

    public void deleteBookmarkById(Long id) {
        bookmarkRepository.deleteById(id);
    }

    public boolean existsByUri(URI uri) {
        return bookmarkRepository.existsByUri(uri);
    }

    @Transactional
    public void deleteBookmarkByUri(URI uri) {
        bookmarkRepository.deleteByUri(uri);
    }
}
