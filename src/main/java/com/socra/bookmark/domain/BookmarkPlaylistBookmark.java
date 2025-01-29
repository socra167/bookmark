package com.socra.bookmark.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "bookmark_playlists_bookmarks")
public class BookmarkPlaylistBookmark {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "bookmark_playlist_id", nullable = false)
	private BookmarkPlaylist bookmarkPlaylist;

	@ManyToOne
	@JoinColumn(name = "bookmark_id", nullable = false)
	private Bookmark bookmark;

	@Column(nullable = false)
	private int orderIndex;

	public void setBookmarkPlaylist(BookmarkPlaylist playlist) {
		this.bookmarkPlaylist = playlist;
	}

	public void setBookmark(Bookmark bookmark) {
		this.bookmark = bookmark;
	}

	public void setOrderIndex(int nextOrderIndex) {
		this.orderIndex = nextOrderIndex;
	}
}

