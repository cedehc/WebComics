package com.example.webcomics;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.bytten.comicviewer.IComicInfo;
import net.bytten.comicviewer.IComicProvider;
import net.bytten.comicviewer.Utility;
import net.bytten.comicviewer.ArchiveData.ArchiveItem;

import org.json.JSONObject;
import org.json.JSONTokener;

import android.net.Uri;
import android.util.Log;

public class DucaWillComicProvider implements IComicProvider {

    private static final Pattern archiveItemPattern = Pattern.compile(
            // group(1): comic number;   group(2): date;   group(3): title
            "\\s*<a href=\"/(\\d+)/\" title=\"(\\d+-\\d+-\\d+)\">([^<]+)</a><br/>\\s*");
    private static final String ARCHIVE_URL = "http://www.xkcd.com/archive/";

    private DucaWillComicDefinition def;

    public DucaWillComicProvider(DucaWillComicDefinition def) {
        this.def = def;
    }

    @Override
    public Uri comicDataUrlForUrl(Uri url) {
        Matcher m = DucaWillComicDefinition.comicUrlPattern
            .matcher(url.toString());
        if (m.matches())
            return createComicUrl(m.group(2));
        return null;
    }

    @Override
    public Uri createComicUrl(String comicId) {
        return Uri.parse("http://duca-pauloalexandre.rhcloud.com/api/v1/willtirando/");
    }

    @Override
    public IComicInfo fetchComicInfo(Uri url) throws Exception {
        String text = Utility.blockingReadUri(url);
        JSONObject obj = (JSONObject)new JSONTokener(text).nextValue();
        Log.d("json", obj.names().toString());
        DucaWillComicInfo data = new DucaWillComicInfo();
        data.img = Uri.parse(obj.getString("comic"));
        //byte [] encodedAlt = obj.getString("alt").getBytes("ISO-8859-1");
        //data.alt = new String("teste");
        data.num = 1337;
        data.title = obj.getString("title");
        if (obj.has("source") && obj.getString("source").length() > 0) {
            data.link = Uri.parse(obj.getString("source"));
        }
        return data;
    }

    @Override
    public Uri fetchRandomComicUrl() throws Exception {
        HttpURLConnection http = (HttpURLConnection) new URL("http",
                "dynamic.xkcd.com", "/random/comic").openConnection();
        http.setInstanceFollowRedirects(false);
        String redirect = http.getHeaderField("Location");
        if (redirect != null) {
            Uri loc = Uri.parse(redirect);
            if (def.isComicUrl(loc)) {
                return comicDataUrlForUrl(loc);
            }
        }
        Log.w("headers", Integer.toString(http.getResponseCode()));
        Map<String, List<String>> headers = http.getHeaderFields();
        for (String key: headers.keySet()) {
            if (key == null) continue;
            Log.w("headers", key);
            for (String val: headers.get(key)) {
                Log.w("headers", "    "+val);
            }
        }
        return null;
    }

    @Override
    public Uri getFinalComicUrl() {
        return Uri.parse("http://duca-pauloalexandre.rhcloud.com/api/v1/willtirando/");
    }

    @Override
    public String getFirstId() {
        return "1";
    }

    @Override
    public IComicInfo createEmptyComicInfo() {
        return new DucaWillComicInfo();
    }

    @Override
    public List<ArchiveItem> fetchArchive() throws Exception {
        List<ArchiveItem> archiveItems = new ArrayList<ArchiveItem>();
        URL url = new URL(ARCHIVE_URL);
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream()));

        try {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher m = archiveItemPattern.matcher(line);
                while (m.find()) {
                    ArchiveItem item = new ArchiveItem();
                    item.comicId = m.group(1);
                    item.title = item.comicId + " - " + m.group(3);
                    archiveItems.add(item);
                }

                Utility.allowInterrupt();
            }

        } finally {
            br.close();
        }
        return archiveItems;
    }

    @Override
    public Uri getExplainUrl(IComicInfo comic) {
        return Uri.parse("http://www.explainxkcd.com/wiki/index.php?title="+
                comic.getId());
    }

}
