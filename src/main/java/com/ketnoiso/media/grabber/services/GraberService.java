package com.ketnoiso.media.grabber.services;

import com.ketnoiso.media.grabber.core.model.Article;
import com.ketnoiso.media.grabber.core.model.Playlist;
import com.ketnoiso.media.grabber.repository.ArticleRepository;
import com.ketnoiso.media.grabber.repository.PlaylistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Created by IT on 3/30/2015.
 */
@Service
@Transactional
public class GraberService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private PlaylistRepository playlistRepository;

    /**
     *
     * @param playlist
     * @return
     */
    public Playlist savePlaylist(Playlist playlist) {
        Playlist playlistFromDB = playlistRepository.findPlaylistByPlaylistId(playlist.getPlaylistId());
        if(playlistFromDB !=null) {
            return playlistFromDB;
        } else {
            for (Article article : playlist.getArticles()) {
                article.setPlaylist(playlist);
            }
            return playlistRepository.save(playlist);
        }

    }
}
