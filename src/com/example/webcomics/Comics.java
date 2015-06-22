package com.example.webcomics;

import android.content.Context;
import android.widget.TextView;
import android.view.View;
import android.view.ViewGroup;

import net.bytten.comicviewer.ComicListAdapter;

public class Comics extends ComicListAdapter {

    private String[] mComics = new String[] {
        "xkcd",
        "WillTirando",
        "AbstruseGoose",
    };

    private Context mContext;

    public Comics(Context context) {
        mContext = context;
    }

    public boolean areAllItemsEnabled() {
        return true;
    }

    @Override
    public boolean isEnabled(int position) {
        return true;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    public Object getItem(int position) {
        return mComics[position];
    }

    public long getItemId(int position) {
        return getItem(position).hashCode();
    }

    public int getCount() {
        return mComics.length;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    public boolean isItemSelectable(int position) {
        return true;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        if (position <= getCount()) {
            return getItemView(position, convertView, parent);
        } else {
            return null;
        }
    }

    public View getItemView(int itemPosition, View convertView, ViewGroup parent) {
        String str = (String) getItem(itemPosition);
        TextView tview;

        if (convertView != null) {
            tview = (TextView)convertView;
        } else {
            tview = new TextView(mContext);
        }

        tview.setText(str);

        return tview;
    }
}

