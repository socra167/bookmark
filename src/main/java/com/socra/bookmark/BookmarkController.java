package com.socra.bookmark;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/bookmarks")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    @Autowired
    public BookmarkController(BookmarkService bookmarkService) {
        this.bookmarkService = bookmarkService;
    }

    // 모든 북마크 조회
    @GetMapping
    public ResponseEntity<List<Bookmark>> getAllBookmarks() {
        List<Bookmark> bookmarks = bookmarkService.findAllBookmark();
        return ResponseEntity.ok(bookmarks);
    }

    // ID로 특정 북마크 조회
    @GetMapping("/{id}")
    public ResponseEntity<Bookmark> getBookmarkById(@PathVariable("id") Long id) {
        Optional<Bookmark> bookmark = bookmarkService.findBookmarkById(id);
        return bookmark.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/exists")
    public boolean existByUri(@RequestParam("uri") String uri) {
        try {
            return bookmarkService.existsByUri(new URI(uri));
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid URI format", e);
        }
    }

    // 새로운 북마크 추가
    @PostMapping
    public ResponseEntity<Bookmark> createBookmark(@RequestBody Bookmark bookmark) {
        bookmarkService.saveBookmark(bookmark);
        return ResponseEntity.created(URI.create("/api/bookmarks/" + bookmark.getId())).body(bookmark);
    }

    // ID로 특정 북마크 수정
    @PutMapping("/{id}")
    public ResponseEntity<Bookmark> updateBookmark(@PathVariable Long id, @RequestBody Bookmark updatedBookmark) {
        Optional<Bookmark> existingBookmark = bookmarkService.findBookmarkById(id);
        if (existingBookmark.isPresent()) {
            Bookmark bookmark = existingBookmark.get();
            bookmark.setName(updatedBookmark.getName());
            bookmark.setUri(updatedBookmark.getUri());
            bookmark.setDate(updatedBookmark.getDate());
            bookmarkService.saveBookmark(bookmark);
            return ResponseEntity.ok(bookmark);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // ID로 특정 북마크 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBookmarkById(@PathVariable("id") Long id) {
        if (bookmarkService.findBookmarkById(id).isPresent()) {
            bookmarkService.deleteBookmarkById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteBookmarkByUri(@RequestParam("uri") String uriInput) {
        URI uri = URI.create(uriInput);
        if (bookmarkService.existsByUri(uri)) {
            bookmarkService.deleteBookmarkByUri(uri);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
