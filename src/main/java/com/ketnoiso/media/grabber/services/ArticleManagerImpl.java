package com.ketnoiso.media.grabber.services;

import com.ketnoiso.media.grabber.core.model.Article;
import com.ketnoiso.media.grabber.core.model.Playlist;
import com.ketnoiso.core.helper.UTF8ToAscii;
import com.ketnoiso.media.grabber.core.model.Singer;
import com.ketnoiso.media.grabber.model.Item;
import com.ketnoiso.media.grabber.repository.SingerRepository;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class ArticleManagerImpl.
 */
@Service
public class ArticleManagerImpl implements ArticleManager {
	@Autowired
    private SingerRepository singerRepository;
	/* (non-Javadoc)
	 * @see com.baclp.com.ketnoiso.media.grabber.services.ArticleManager#getArticleDecorator(com.baclp.com.ketnoiso.media.grabber.model.Item, com.baclp.com.ketnoiso.media.grabber.model.PlaylistDecorator)
	 */
	public Article getArticleDecorator(Item item, Playlist playlistDecorator) {
		if(item != null) {
            String singerName = item.getPerformer().trim();
            Singer singer = singerRepository.findByNameIgnoreCase(singerName);
            if(singer==null) {
                singer = new Singer(singerName);
                singerRepository.save(singer);
            }
			Article articleDecorator = new Article();
			articleDecorator.setHq(item.getHq());
			articleDecorator.setLink(item.getLink());
			articleDecorator.setSinger(singer);
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
