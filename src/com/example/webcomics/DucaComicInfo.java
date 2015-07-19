package com.example.webcomics;

import net.bytten.comicviewer.IComicInfo;
import android.net.Uri;

public class DucaComicInfo implements IComicInfo {

    protected static final String baseURL = "http://duca-pauloalexandre.rhcloud.com/api/v1/";

    private String comicShortName = null;

    public DucaComicInfo(String comicShortName) {
        this.comicShortName = comicShortName;
    }

    public Uri img, link;
    public int num;
    public String title = "", alt = "";
    public boolean bookmarked;

    @Override
    public String getAlt() {
        return alt;
    }

    @Override
    public String getId() {
        return Integer.toString(num);
    }

    @Override
    public Uri getImage() {
        return img;
    }

    @Override
    public String getNextId() {
        int n = num + 1;
        return Integer.toString(n);
    }

    @Override
    public String getPrevId() {
        int n = num - 1;
        return Integer.toString(n);
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public String getUrl() {
        return baseURL + comicShortName + "/";
    }

    @Override
    public boolean isBookmarked() {
        return bookmarked;
    }

    @Override
    public void setBookmarked(boolean b) {
        bookmarked = b;
    }

    @Override
    public Uri getLink() {
        return link;
    }

}
