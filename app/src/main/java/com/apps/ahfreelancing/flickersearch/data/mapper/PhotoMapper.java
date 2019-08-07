package com.apps.ahfreelancing.flickersearch.data.mapper;

import com.apps.ahfreelancing.flickersearch.data.entity.PhotoEntity;
import com.apps.ahfreelancing.flickersearch.data.entity.PhotosWrapperEntity;
import com.apps.ahfreelancing.flickersearch.data.entity.SearchResponse;
import com.apps.ahfreelancing.flickersearch.domain.model.PhotoModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * Created by Ahmed Hassan on 8/5/2019.
 */
public class PhotoMapper {
    public static PhotoModel mapEntityToModel(PhotoEntity entity){
        return new PhotoModel(
                //Create small image url and large image url
                String.format(Locale.ENGLISH,
                        "https://farm%d.staticflickr.com/%s/%s_%s.jpg",
                        entity.getFarm(),
                        entity.getServer(),
                        entity.getId(),
                        entity.getSecret()),
                String.format(Locale.ENGLISH,
                        "https://farm%d.staticflickr.com/%s/%s_%s_b.jpg",
                        entity.getFarm(),
                        entity.getServer(),
                        entity.getId(),
                        entity.getSecret()),
                entity.getTitle()
        );
    }

    public static ArrayList<PhotoModel> mapResponseToModels(SearchResponse response){
        ArrayList<PhotoModel> models = new ArrayList<>();
        for (PhotoEntity e: response.getPhotos().getPhotoEntities()) {
            models.add(mapEntityToModel(e));
        }
        return models;
    }

    public static ArrayList<PhotoModel> mapEntitiesToModels(List<PhotoEntity> entities){
        ArrayList<PhotoModel> models = new ArrayList<>();
        for (PhotoEntity e: entities) {
            models.add(mapEntityToModel(e));
        }
        return models;
    }

}
