package com.apps.ahfreelancing.flickersearch.data.cloud;

import com.apps.ahfreelancing.flickersearch.BuildConfig;
import com.apps.ahfreelancing.flickersearch.data.entity.PhotosWrapperEntity;
import com.apps.ahfreelancing.flickersearch.data.entity.SearchResponse;

import javax.inject.Inject;

import io.reactivex.Single;
import retrofit2.Retrofit;

/**
 * Created by Ahmed Hassan on 8/5/2019.
 */
public class CloudManager {
    private FlickerApi flickerApi;

    @Inject
    public CloudManager(Retrofit retrofit){
        flickerApi = retrofit.create(FlickerApi.class);
    }

    public Single<SearchResponse> searchPhotos(String tag, int page) throws Exception{
        return flickerApi.searchPhotos(BuildConfig.API_KEY, tag, page, BuildConfig.PAGE_SIZE);
    }
}
