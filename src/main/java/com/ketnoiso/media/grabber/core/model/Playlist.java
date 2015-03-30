package com.ketnoiso.media.grabber.core.model;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;
import java.util.List;

/**
 * The Class PlaylistDecorator.
 *
 * @author baclp
 */
@XmlRootElement
@Entity
public class Playlist implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    /**
     * The Constant serialVersionUID.
     */
    private static final long serialVersionUID = -3930309389544854211L;

    /**
     * The title.
     */
    String title;

    /**
     * The playlist id.
     */
    String playlistId;

    /**
     * The playlist url.
     */
    private String playlistUrl;

    @CreatedDate
    private DateTime createdDate;

    /**
     * The article decorators.
     */
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "playlist",cascade = CascadeType.ALL)
    private List<Article> articles;

    /**
     * The playlist info.
     */
    @OneToOne(cascade = CascadeType.ALL)
    private PlaylistInfo playlistInfo;

    private String source;

    /**
     * Gets the title.
     *
     * @return the title
     */
    public String getTitle() {
        return StringUtils.substringBefore(title, "-");
    }

    /**
     * Sets the title.
     *
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets the playlist id.
     *
     * @return the albumId
     */
    public String getPlaylistId() {
        return playlistId;
    }

    /**
     * Sets the playlist id.
     *
     * @param playlistId the albumId to set
     */
    public void setPlaylistId(String playlistId) {
        this.playlistId = playlistId;
    }

    /**
     * Gets the playlist url.
     *
     * @return the playlistUrl
     */
    public String getPlaylistUrl() {
        return playlistUrl;
    }

    /**
     * Sets the playlist url.
     *
     * @param playlistUrl the playlistUrl to set
     */
    public void setPlaylistUrl(String playlistUrl) {
        this.playlistUrl = playlistUrl;
    }

    /**
     * Gets the article decorators.
     *
     * @return the articles
     */
    public List<Article> getArticles() {
        return articles;
    }

    /**
     * Sets the article decorators.
     *
     * @param articles the articles to set
     */
    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }

    /**
     * Gets the playlist info.
     *
     * @return the playlist info
     */
    public PlaylistInfo getPlaylistInfo() {
        return playlistInfo;
    }

    /**
     * Sets the playlist info.
     *
     * @param playlistInfo the new playlist info
     */
    public void setPlaylistInfo(PlaylistInfo playlistInfo) {
        this.playlistInfo = playlistInfo;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
