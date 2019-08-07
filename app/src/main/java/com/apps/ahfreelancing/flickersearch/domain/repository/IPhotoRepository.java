package com.apps.ahfreelancing.flickersearch.domain.repository;

import com.apps.ahfreelancing.flickersearch.domain.model.PhotoModel;

import java.util.ArrayList;

import io.reactivex.Single;

/**
 * Created by Ahmed Hassan on 8/6/2019.
 */
public interface IPhotoRepository {
    Single<ArrayList<PhotoModel>> searchPhotos(String tag, int page);
}
