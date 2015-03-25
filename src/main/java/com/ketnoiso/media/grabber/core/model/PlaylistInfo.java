package com.ketnoiso.media.grabber.core.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * The Class PlaylistInfo.
 */
@Entity
@Table(name="playlist")
public class PlaylistInfo implements Serializable{
	@Id
	private Long id;

	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 1L;
	
	/** The play count. */
	private int playCount;
	
	/** The published year. */
	private int publishedYear;
	
	/** The description. */
	private String description;
	
	/** The playlist image cover. */
	private String playlistImageCover;
	
	/**
	 * Gets the play count.
	 * 
	 * @return the play count
	 */
	public int getPlayCount() {
		return playCount;
	}
	
	/**
	 * Sets the play count.
	 * 
	 * @param playCount
	 *            the new play count
	 */
	public void setPlayCount(int playCount) {
		this.playCount = playCount;
	}
	
	/**
	 * Gets the published year.
	 * 
	 * @return the published year
	 */
	public int getPublishedYear() {
		return publishedYear;
	}
	
	/**
	 * Sets the published year.
	 * 
	 * @param publishedYear
	 *            the new published year
	 */
	public void setPublishedYear(int publishedYear) {
		this.publishedYear = publishedYear;
	}
	
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
	
	
}
