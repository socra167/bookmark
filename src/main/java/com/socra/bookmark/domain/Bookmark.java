package com.socra.bookmark.domain;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "bookmarks")
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

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdDate;

	@OneToMany(mappedBy = "bookmarks")
	private List<CustomTag> customTag = new ArrayList<>();

	@Builder
	public Bookmark(String name, URI uri, String description) {
		this.name = name;
		this.uri = uri;
		this.description = description;
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

}

