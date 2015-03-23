package com.ketnoiso.media.grabber.model;

import com.ketnoiso.core.helper.UTF8ToAscii;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 * The Class ArticleDecorator.
 */
@XmlRootElement
@Entity
public class ArticleDecorator implements Serializable {
	@Id
	private Long id;
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6246771125144228262L;
	
	/** The song id. */
	private String songId;
	
	/** The type. */
	private String type;
	
	/** The title. */
	private String title;
	
	/** The performer. */
	private String performer;
	
	/** The source. */
	private String source;
	
	/** The hq. */
	private String hq;
	
	/** The link. */
	private String link;
	
	/** The download link. */
	private String downloadLink;
	
	/** The direct link. */
	private String directLink;
	
	private String fileName;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="playlist_id",referencedColumnName="id",nullable=false,unique=true)
	private PlaylistDecorator playlist;
	
	/**
	 * Gets the song id.
	 * 
	 * @return the songId
	 */
	public String getSongId() {
		return songId;
	}
	
	/**
	 * Sets the song id.
	 * 
	 * @param songId
	 *            the songId to set
	 */
	public void setSongId(String songId) {
		this.songId = songId;
	}
	
	/**
	 * Gets the type.
	 * 
	 * @return the type
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Sets the type.
	 * 
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Gets the title.
	 * 
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}
	
	/**
	 * Sets the title.
	 * 
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	
	/**
	 * Gets the performer.
	 * 
	 * @return the performer
	 */
	public String getPerformer() {
		return performer;
	}
	
	/**
	 * Sets the performer.
	 * 
	 * @param performer
	 *            the performer to set
	 */
	public void setPerformer(String performer) {
		this.performer = performer;
	}
	
	/**
	 * Gets the source.
	 * 
	 * @return the source
	 */
	public String getSource() {
		return source;
	}
	
	/**
	 * Sets the source.
	 * 
	 * @param source
	 *            the source to set
	 */
	public void setSource(String source) {
		this.source = source;
	}
	
	/**
	 * Gets the hq.
	 * 
	 * @return the hq
	 */
	public String getHq() {
		return hq;
	}
	
	/**
	 * Sets the hq.
	 * 
	 * @param hq
	 *            the hq to set
	 */
	public void setHq(String hq) {
		this.hq = hq;
	}
	
	/**
	 * Gets the link.
	 * 
	 * @return the link
	 */
	public String getLink() {
		return link;
	}
	
	/**
	 * Sets the link.
	 * 
	 * @param link
	 *            the link to set
	 */
	public void setLink(String link) {
		this.link = link;
	}
	
	/**
	 * Gets the download link.
	 * 
	 * @return the downloadLink
	 */
	public String getDownloadLink() {
		return downloadLink;
	}
	
	/**
	 * Sets the download link.
	 * 
	 * @param downloadLink
	 *            the downloadLink to set
	 */
	public void setDownloadLink(String downloadLink) {
		this.downloadLink = downloadLink;
	}
    
    /**
	 * Sets the direct link.
	 * 
	 * @param directLink
	 *            the directLink to set
	 */
    public void setDirectLink(String directLink) {
        this.directLink = directLink;
    }
    
    /**
	 * Gets the direct link.
	 * 
	 * @return the directLink
	 */
    public String getDirectLink() {
        return directLink;
    }
    
    /**
	 * Gets the file name.
	 * 
	 * @return the file name
	 */
    public String getFileName() {
    	return UTF8ToAscii.rename(title) + "." + type;
    }

	public void setFileName(String fileName) {
		this.fileName = UTF8ToAscii.rename(title) + "." + type;
	}
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
