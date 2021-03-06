package com.example.webcomics;

import java.util.regex.Pattern;

import android.net.Uri;

public class DucaWillComicDefinition extends DucaComicDefinition {

    private Pattern comicUrlPattern;

    private Pattern xkcdHomePattern = Pattern.compile(
            "http://(www\\.)?xkcd\\.com(/)?");

    private Pattern archiveUrlPattern = Pattern.compile(
            "http://(www\\.)?xkcd\\.com/archive(/)?");

    public DucaWillComicDefinition() {
        super();
    }

    @Override
    public DucaComicProvider newProviderInstance() {
        return new DucaWillComicProvider(this);
    }

    @Override
    public Pattern getComicUrlPattern() {
        if (comicUrlPattern == null) {
            comicUrlPattern = Pattern.compile(
                "http://(www\\.)?xkcd\\.com/([0-9]+)(/)?");
        }
        return comicUrlPattern;
    }

    @Override
    public Uri getArchiveUrl() {
        return null;
    }

    @Override
    public String getAuthorLinkText() {
        return "Will Tirando";
    }

    @Override
    public Uri getAuthorLinkUrl() {
        return null;
    }

    @Override
    public String getAuthorName() {
        return "Will autor";
    }

    @Override
    public String getComicTitle() {
        return "Will Tirando";
    }

    @Override
    public String getComicTitleAbbrev() {
        /* TODO only testing, remove later */
        return "willtirandoabbrev";
    }

    @Override
    public boolean isArchiveUrl(Uri url) {
        return archiveUrlPattern.matcher(url.toString()).matches();
    }

    @Override
    public boolean isComicUrl(Uri url) {
        return getComicUrlPattern().matcher(url.toString()).matches();
    }

    @Override
    public boolean isHomeUrl(Uri url) {
        return xkcdHomePattern.matcher(url.toString()).matches();
    }
}
