package com.ketnoiso.media.grabber.core.model;

import com.ketnoiso.core.helper.UTF8ToAscii;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;
import java.io.Serializable;

/**
 * The Class ArticleDecorator.
 */
@XmlRootElement
@Entity
public class Article extends AbstractModel implements Serializable {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	/** The Constant serialVersionUID. */
	private static final long serialVersionUID = 6246771125144228262L;
	
    /** The song id. */
    @Transient
	private String songId;
	
	/** The type. */
	private String type;
	
	/** The title. */
	private String title;
	
	/** The performer. */
    @ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
	private Singer singer;
	
	/** The source. */
    @Transient
	private String source;
	
	/** The hq. */
    @Transient
	private String hq;
	
	/** The link. */
    @Transient
	private String link;
	
	/** The direct link. */
    @Transient
	private String directLink;
	
	private String fileName;

	@ManyToOne(fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    @JoinColumn(name = "playlist_id")
	private Playlist playlist;

    @CreatedDate
    private DateTime createdDate;

    @LastModifiedDate
    private DateTime lastModifiedDate;
	
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

    public Singer getSinger() {
        return singer;
    }

    public void setSinger(Singer singer) {
        this.singer = singer;
    }
    @XmlTransient
    public Playlist getPlaylist() {
        return playlist;
    }

    public void setPlaylist(Playlist playlist) {
        this.playlist = playlist;
    }
}
