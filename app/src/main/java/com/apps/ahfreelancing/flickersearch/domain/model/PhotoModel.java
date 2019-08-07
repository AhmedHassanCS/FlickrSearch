package com.apps.ahfreelancing.flickersearch.domain.model;

/**
 * Created by Ahmed Hassan on 8/4/2019.
 */
public class PhotoModel {
    private String smallUrl;
    private String largeUrl;
    private String title;

    public PhotoModel(String smallUrl, String largeUrl, String title) {
        this.smallUrl = smallUrl;
        this.largeUrl = largeUrl;
        this.title = title;
    }

    public String getLargeUrl() {
        return largeUrl;
    }

    public String getSmallUrl() {
        return smallUrl;
    }

    public String getTitle() {
        return title;
    }
}
