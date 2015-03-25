package com.ketnoiso.media.grabber.services;


import com.ketnoiso.media.grabber.core.model.Article;
import com.ketnoiso.media.grabber.core.model.Playlist;
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
	public Article getArticleDecorator(Item item, Playlist playlistDecorator);
	
	/**
	 * Extract song id.
	 * 
	 * @param itemSource
	 *            the item source
	 * @return the string
	 */
	public String extractSongId(String itemSource);
}
