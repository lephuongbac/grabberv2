package com.ketnoiso.media.grabber.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class Article implements Serializable {
	@Id
	private Long id;
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

}
