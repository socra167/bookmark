package com.socra.bookmark.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "bookmark_groups")
@Entity
public class BookmarkGroup {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Column(nullable = false)
	private String name;

	@OneToMany(mappedBy = "bookmarkGroups")
	private List<Bookmark> bookmarks = new ArrayList<>();

	@OneToMany(mappedBy = "bookmark_groups")
	private List<CustomTag> customTags = new ArrayList<>();
}
