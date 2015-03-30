package com.ketnoiso.media.grabber.core.model;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;

/**
 * The Class PlaylistInfo.
 */
@Entity
public class PlaylistInfo implements Serializable{
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The play count. */
	private Integer playCount;
	
	/** The published year. */
	private Integer publishedYear;
	
	/** The description. */
	private String description;
	
	/** The playlist image cover. */
	private String playlistImageCover;

    @CreatedDate
    private DateTime createdDate;

	/**
	 * Gets the description.
	 * 
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Sets the description.
	 * 
	 * @param description
	 *            the new description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Gets the playlist image cover.
	 * 
	 * @return the playlist image cover
	 */
	public String getPlaylistImageCover() {
		return playlistImageCover;
	}
	
	/**
	 * Sets the playlist image cover.
	 * 
	 * @param playlistImageCover
	 *            the new playlist image cover
	 */
	public void setPlaylistImageCover(String playlistImageCover) {
		this.playlistImageCover = playlistImageCover;
	}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getPlayCount() {
        return playCount;
    }

    public void setPlayCount(Integer playCount) {
        this.playCount = playCount;
    }

    public Integer getPublishedYear() {
        return publishedYear;
    }

    public void setPublishedYear(Integer publishedYear) {
        this.publishedYear = publishedYear;
    }
}
