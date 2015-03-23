package com.ketnoiso.media.grabber.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * The Class Item.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD) 
public class Item {
	
	/** The type. */
	@XmlAttribute
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
	
	/**
	 * Instantiates a new item.
	 */
	public Item() {
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
}
