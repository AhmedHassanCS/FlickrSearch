package com.apps.ahfreelancing.flickersearch.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by Ahmed Hassan on 8/4/2019.
 */
public class PhotosWrapperEntity {
    private  @SerializedName("photo")
    ArrayList<PhotoEntity> photoEntities;

    public PhotosWrapperEntity(ArrayList<PhotoEntity> photoEntities) {
        this.photoEntities = photoEntities;
    }

    public ArrayList<PhotoEntity> getPhotoEntities() {
        return photoEntities;
    }
}
