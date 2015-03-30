package com.ketnoiso.media.grabber.core.model;

import org.joda.time.DateTime;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Created by IT on 3/30/2015.
 */
@Entity
public class Singer {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    @CreatedDate
    private DateTime createdDate;
    @LastModifiedDate
    private DateTime lastModifyDate;

    public Singer(){

    }
    public Singer(String name) {
        this.name = name;
    }
}
