package com.apps.ahfreelancing.flickersearch.data.repository;

import com.apps.ahfreelancing.flickersearch.BuildConfig;
import com.apps.ahfreelancing.flickersearch.data.cache.FlickrDatabase;
import com.apps.ahfreelancing.flickersearch.data.cloud.CloudManager;
import com.apps.ahfreelancing.flickersearch.data.mapper.PhotoMapper;
import com.apps.ahfreelancing.flickersearch.domain.model.PhotoModel;
import com.apps.ahfreelancing.flickersearch.domain.repository.IPhotoRepository;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.functions.Consumer;

/**
 * Created by Ahmed Hassan on 8/6/2019.
 */
public class PhotoRepository implements IPhotoRepository {

    private CloudManager cloudManager;
    private FlickrDatabase flickrDatabase;

    @Inject
    public PhotoRepository(CloudManager cloudManager,
                           FlickrDatabase flickrDatabase
                           ){
        this.cloudManager = cloudManager;
        this.flickrDatabase = flickrDatabase;
    }


    @Override
    public Single<ArrayList<PhotoModel>> searchPhotos(String tag, int page) {

        try {
            return cloudManager.searchPhotos(tag, page)
                    .doAfterSuccess(response ->
                            flickrDatabase.photoDao().insertPhotos(response.getPhotos().getPhotoEntities()))
                    .map(PhotoMapper::mapResponseToModels);
        } catch (Exception e){
            return flickrDatabase.photoDao()
                    .getPhotos(tag, BuildConfig.PAGE_SIZE, BuildConfig.PAGE_SIZE * (page - 1))
                    .map(PhotoMapper::mapEntitiesToModels);
        }
    }
}
