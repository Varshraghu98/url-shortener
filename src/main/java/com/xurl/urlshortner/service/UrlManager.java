package com.xurl.urlshortner.service;


import jakarta.validation.constraints.NotBlank;
import org.springframework.validation.annotation.Validated;

@Validated
public interface UrlManager {
    public String getUrlByKey(@NotBlank String key);
    public String shortenUrl(@NotBlank String url);
}