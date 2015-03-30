package com.ketnoiso.media.grabber.repository;

import com.ketnoiso.media.grabber.core.model.Singer;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by IT on 3/30/2015.
 */
public interface SingerRepository extends CrudRepository<Singer,Long> {
    Singer findByNameIgnoreCase(String name);
}
