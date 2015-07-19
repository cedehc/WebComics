package com.example.webcomics;

import java.util.regex.Pattern;

import net.bytten.comicviewer.IComicDefinition;
import net.bytten.comicviewer.IComicProvider;

import android.net.Uri;

public abstract class DucaComicDefinition implements IComicDefinition {

    private DucaComicProvider provider;

    public abstract DucaComicProvider newProviderInstance();

    public abstract Pattern getComicUrlPattern();

    public DucaComicDefinition() {
        provider = newProviderInstance();
    }

    @Override
    public String getPackageName() {
        return "com.example.webcomics";
    }

    @Override
    public boolean hasAltText() {
        return false;
    }

    @Override
    public boolean idsAreNumbers() {
        return false;
    }

    @Override
    public IComicProvider getProvider() {
        return provider;
    }

    @Override
    public Uri getDonateUrl() {
        return null;
    }

}
