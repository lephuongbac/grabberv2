package com.ketnoiso.media.grabber.model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;

/**
 * The Class Data.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD) 
public class Data {
	
	/** The page. */
	@XmlAttribute
	private String page;
	
	/** The items. */
	@XmlElement(name="item")
	private ArrayList<Item> items;
	
	/**
	 * Sets the items.
	 * 
	 * @param items
	 *            the items to set
	 */
	public void setItems(ArrayList<Item> items) {
		this.items = items;
	}
	
	/**
	 * Gets the items.
	 * 
	 * @return the items
	 */
	public ArrayList<Item> getItems() {
		return items;
	}
}
