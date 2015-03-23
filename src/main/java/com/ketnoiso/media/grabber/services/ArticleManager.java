package com.ketnoiso.media.grabber.services;


import com.ketnoiso.media.grabber.model.ArticleDecorator;
import com.ketnoiso.media.grabber.model.PlaylistDecorator;
import com.ketnoiso.media.grabber.model.Item;

/**
 * The Interface ArticleManager.
 */
public interface ArticleManager {
	/**
	 * Gets the article decorator.
	 * 
	 * @param item
	 *            the item
	 * @param playlistDecorator
	 *            the playlist decorator
	 * @return the article decorator
	 */
	public ArticleDecorator getArticleDecorator(Item item, PlaylistDecorator playlistDecorator);
	
	/**
	 * Extract song id.
	 * 
	 * @param itemSource
	 *            the item source
	 * @return the string
	 */
	public String extractSongId(String itemSource);
}
