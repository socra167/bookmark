package com.socra.bookmark.domain;

import java.net.URI;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
@Entity
public class Bookmark {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private URI uri;

	@Column(nullable = true)
	private String description;

	@Column(nullable = false)
	private LocalDate date;

	@Builder
	public Bookmark(String name, URI uri, String description, LocalDate date) {
		this.name = name;
		this.uri = uri;
		this.description = description;
		this.date = date;
	}

	public void updateName(final String name) {
		this.name = name;
	}

	public void updateUri(final URI uri) {
		this.uri = uri;
	}

	public void updateDescription(final String description) {
		this.description = description;
	}

	public void updateDate(final LocalDate date) {
		this.date = date;
	}
}

