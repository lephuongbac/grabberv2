package com.ketnoiso.media.grabber.core.model;

import org.apache.commons.lang3.builder.ToStringBuilder;

/**
 * Created by IT on 3/30/2015.
 */
public class AbstractModel {
    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
