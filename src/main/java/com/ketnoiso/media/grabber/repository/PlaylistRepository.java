package com.ketnoiso.media.grabber.repository;

import com.ketnoiso.media.grabber.core.model.Playlist;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IT on 3/30/2015.
 */
public interface PlaylistRepository extends CrudRepository<Playlist,Long> {
    Playlist findPlaylistByPlaylistId(String playlistId);
}
