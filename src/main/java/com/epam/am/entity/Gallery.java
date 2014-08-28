package com.epam.am.entity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Gallery {
    private Map<Painting, String> links = new HashMap<>();

    public Gallery() {
    }

    public Gallery(List<Painting> paintings) {
        add(paintings);
    }

    public void add(Painting painting) {
        String path = painting.getPath();
        int index = path.indexOf("img");
        if (index < 0) return;
        String link = path.substring(path.indexOf("img"));
        links.put(painting, link);
    }

    public int size() {
        return links.size();
    }

    public void remove(Painting painting) {
        links.remove(painting);
    }

    public Map<Painting, String> getLinks() {
        return links;
    }

    public void add(List<Painting> paintings) {
        for (Painting painting : paintings) {
            add(painting);
        }
    }
}
