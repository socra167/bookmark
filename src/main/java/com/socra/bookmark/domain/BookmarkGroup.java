package com.socra.bookmark.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@EntityListeners(AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "bookmark_groups")
@Entity
public class BookmarkGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@CreatedDate
	@Column(nullable = false, updatable = false)
	private LocalDateTime createdDate;

	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY)
	private List<Bookmark> bookmarks = new ArrayList<>();

	@Builder.Default
	@OneToMany(fetch = FetchType.LAZY)
	private List<CustomTag> customTags = new ArrayList<>();

	public void addBookmark(Bookmark bookmark) {
		bookmarks.add(bookmark);
	}

	public void updateName(String name) {
		this.name = name;
	}
}
