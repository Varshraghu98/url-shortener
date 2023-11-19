package com.xurl.urlshortner.controller;

import com.xurl.urlshortner.service.UrlManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/rest/url")
public class UrlShortnerController {

    @Autowired
    UrlManager urlManager;

    @GetMapping("/{id}")
    public String getUrl(@PathVariable String id) {

        String url = urlManager.getUrlByKey(id);
        System.out.println("URL Retrieved: " + url);

        if (url == null) {
            throw new RuntimeException("There is no shorter URL for : " + id);
        }
        return url;
    }

    @PostMapping
    public String create(@RequestBody String url) {
        return urlManager.shortenUrl(url);
    }
}
