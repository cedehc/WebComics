/*
 *  WebComics - Android app to view web comics
 *  Copyright (C) 2015 Igor Almeida
 *
 *  Based on xkcdViewer:
 *  Copyright (C) 2009-2014 Tom Coxon, Tyler Breisacher, David McCullough,
 *      Kristian Lundkvist, Ivan Vasiljević.
 *  
 *  This program is free software; you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation; either version 2 of the License, or
 *  (at your option) any later version.
 *
 *  This program is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License along
 *  with this program; if not, write to the Free Software Foundation, Inc.,
 *  51 Franklin Street, Fifth Floor, Boston, MA 02110-1301 USA.
 */
package com.example.webcomics;

import net.bytten.comicviewer.ArchiveActivity;
import net.bytten.comicviewer.ComicViewerActivity;
import net.bytten.comicviewer.IComicDefinition;

import net.bytten.xkcdviewer.XkcdArchiveActivity;
import net.bytten.xkcdviewer.XkcdComicDefinition;

public class WebComicsActivity extends ComicViewerActivity {

    @Override
    protected IComicDefinition makeComicDef() {
        return new DucaWillComicDefinition();
    }

    @Override
    protected Class<? extends ArchiveActivity> getArchiveActivityClass() {
        return XkcdArchiveActivity.class;
    }

    @Override
    protected String getStringAboutText() {
        return getResources().getString(R.string.aboutText);
    }

    @Override
    protected String getStringAppName() {
        return getResources().getString(R.string.app_name);
    }

}
