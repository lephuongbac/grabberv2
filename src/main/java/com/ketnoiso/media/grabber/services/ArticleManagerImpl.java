package com.ketnoiso.media.grabber.services;

import com.ketnoiso.media.grabber.model.ArticleDecorator;
import com.ketnoiso.media.grabber.model.PlaylistDecorator;
import com.ketnoiso.core.helper.UTF8ToAscii;
import com.ketnoiso.media.grabber.model.Item;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class ArticleManagerImpl.
 */
@Service
public class ArticleManagerImpl implements ArticleManager {
	
	/* (non-Javadoc)
	 * @see com.baclp.com.ketnoiso.media.grabber.services.ArticleManager#getArticleDecorator(com.baclp.com.ketnoiso.media.grabber.model.Item, com.baclp.com.ketnoiso.media.grabber.model.PlaylistDecorator)
	 */
	public ArticleDecorator getArticleDecorator(Item item, PlaylistDecorator playlistDecorator) {
		if(item != null) {
			ArticleDecorator articleDecorator = new ArticleDecorator();
			articleDecorator.setHq(item.getHq());
			articleDecorator.setLink(item.getLink());
			articleDecorator.setPerformer(item.getPerformer());
			//articleDecorator.setSongId(item.get)
			articleDecorator.setSource(item.getSource());
			articleDecorator.setTitle(StringUtils.trim(item.getTitle()));
			articleDecorator.setType(item.getType());
			articleDecorator.setSongId(extractSongId(articleDecorator.getSource()));
			articleDecorator.setDirectLink("/download/" + playlistDecorator.getPlaylistId() + "/" + UTF8ToAscii.rename(articleDecorator.getTitle()) + "." + articleDecorator.getType());
			return articleDecorator;
		}
		return null;
	}
	
	/**
	 * Extract song id.
	 * 
	 * @param itemSource
	 *            the item source
	 * @return the string
	 */
	public String extractSongId(String itemSource) {
		String songId = null;
		Pattern playlistPattern = Pattern.compile("load-song/(.*?)\\z");
		Matcher m = playlistPattern.matcher(itemSource);
		while (m.find()) {
			songId = m.group(1);
		    // s now contains "BAR"
		}
		return songId;
	}
}
