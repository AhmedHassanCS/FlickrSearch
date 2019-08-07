package com.apps.ahfreelancing.flickersearch.data.cloud;

import com.apps.ahfreelancing.flickersearch.data.entity.PhotosWrapperEntity;
import com.apps.ahfreelancing.flickersearch.data.entity.SearchResponse;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Ahmed Hassan on 8/4/2019.
 */
public interface FlickerApi {

    String SEARCH_METHOD = "method=flicker.photo.search";

    @GET("?method=flickr.photos.search&format=json&nojsoncallback=1")
    Single<SearchResponse> searchPhotos(
            @Query("api_key") String apiKey,
            @Query("tags") String tag,
            @Query("page") int page,
            @Query("per_page") int perPage);

}
