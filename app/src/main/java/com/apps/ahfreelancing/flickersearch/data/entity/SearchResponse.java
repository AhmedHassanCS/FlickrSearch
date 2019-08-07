package com.apps.ahfreelancing.flickersearch.data.entity;

/**
 * Created by Ahmed Hassan on 8/6/2019.
 */
public class SearchResponse {
    private PhotosWrapperEntity photos;

    public SearchResponse(PhotosWrapperEntity photos) {
        this.photos = photos;
    }

    public PhotosWrapperEntity getPhotos() {
        return photos;
    }
}
