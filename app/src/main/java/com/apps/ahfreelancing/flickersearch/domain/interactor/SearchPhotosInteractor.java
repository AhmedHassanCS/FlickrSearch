package com.apps.ahfreelancing.flickersearch.domain.interactor;

import com.apps.ahfreelancing.flickersearch.data.repository.PhotoRepository;
import com.apps.ahfreelancing.flickersearch.domain.model.PhotoModel;

import java.util.ArrayList;

import javax.inject.Inject;
import javax.inject.Named;

import io.reactivex.Scheduler;
import io.reactivex.SingleObserver;

/**
 * Created by Ahmed Hassan on 8/6/2019.
 */
public class SearchPhotosInteractor extends BaseInteractor<SearchPhotosInteractor.SearchParams, ArrayList<PhotoModel>> {

    private PhotoRepository photoRepository;

    @Inject
    public SearchPhotosInteractor(@Named("observeOn") Scheduler observeOn,
                                  @Named("subscribeOn") Scheduler subscribeOn,
                                  PhotoRepository photoRepository) {
        super(observeOn, subscribeOn);
        this.photoRepository = photoRepository;
    }

    @Override
    public void execute(SingleObserver<ArrayList<PhotoModel>> observer, SearchParams params) {
        photoRepository.searchPhotos(params.tag, params.page)
                .subscribeOn(subscribeOn)
                .observeOn(observeOn)
                .subscribe(observer);
    }

    public static class SearchParams{
        private String tag;
        private int page;

        public SearchParams(String tag, int page) {
            this.tag = tag;
            this.page = page;
        }

        public int getPage() {
            return page;
        }

        public String getTag() {
            return tag;
        }
    }
}
