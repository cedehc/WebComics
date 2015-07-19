package com.example.webcomics;

import java.io.*;
import java.net.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bytten.comicviewer.IComicInfo;

import android.net.Uri;

public class DucaExplosmComicProvider extends DucaComicProvider {

    private static final Pattern archiveItemPattern = Pattern.compile(
            // group(1): comic number;   group(2): date;   group(3): title
            "\\s*<a href=\"/(\\d+)/\" title=\"(\\d+-\\d+-\\d+)\">([^<]+)</a><br/>\\s*");
    private static final String ARCHIVE_URL = "http://www.xkcd.com/archive/";

    private DucaExplosmComicDefinition def;

    public DucaExplosmComicProvider(DucaExplosmComicDefinition def) {
        super(def);
    }

    @Override
    public String getComicShortName() {
        return "explosm";
    }

    @Override
    public Uri comicDataUrlForUrl(Uri url) {
        Matcher m = def.getComicUrlPattern()
            .matcher(url.toString());
        if (m.matches())
            return createComicUrl(m.group(2));
        return null;
    }

    @Override
    public IComicInfo createEmptyComicInfo() {
        return new DucaComicInfo(getComicShortName());
    }
}
