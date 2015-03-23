package com.ketnoiso.media.grabber.controllers;

import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.context.annotation.Bean;

/**
 * Created by Bac on 3/14/2015.
 */
@org.springframework.context.annotation.Configuration
public class Configuration {
    @Bean
    public HtmlUnitDriver createWebdrider() {
        HtmlUnitDriver webdriver = new HtmlUnitDriver(false);
        return webdriver;
    }
}
