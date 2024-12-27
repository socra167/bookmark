package com.socra.bookmark;

import jakarta.persistence.*;
import lombok.*;

import java.net.URI;
import java.time.LocalDate;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table
public class Bookmark {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private URI uri;

    @Column(nullable = false)
    private LocalDate date;

    @Builder
    public Bookmark(String name, URI uri, LocalDate date) {
        this.name = name;
        this.uri = uri;
        this.date = date;
    }

    public void updateName(final String name) {
        this.name = name;
    }

    public void updateUri(final URI uri) {
        this.uri = uri;
    }

    public void updateDate(final LocalDate date) {
        this.date = date;
    }
}
