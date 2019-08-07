package com.apps.ahfreelancing.flickersearch.presentation.viewmodel;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.apps.ahfreelancing.flickersearch.domain.interactor.SearchPhotosInteractor;
import com.apps.ahfreelancing.flickersearch.domain.model.PhotoModel;

import java.util.ArrayList;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;

/**
 * Created by Ahmed Hassan on 8/6/2019.
 */
public class SearchViewModel extends ViewModel {
    private SearchPhotosInteractor interactor;
    private MutableLiveData<ArrayList<PhotoModel>> resultsMutable;
    private ArrayList<PhotoModel> allPhotos;
    private int page = 0;
    private String tag;

    @Inject
    public SearchViewModel(SearchPhotosInteractor interactor){
        this.interactor = interactor;
        resultsMutable = new MutableLiveData<>();
        this.tag = "";
        this.allPhotos = new ArrayList<>();
    }
    public LiveData<ArrayList<PhotoModel>> subscribeResults(){
        return resultsMutable;
    }

    public void searchPhotos(String tag){
        if(!this.tag.matches(tag)) {
            this.allPhotos.clear();
            this.tag = tag;
            this.page = 0;
            getNextPage();
        }
    }

    public void getNextPage(){
        page++;
        interactor.execute(new SearchObserver(), new SearchPhotosInteractor.SearchParams(tag, page));
    }

    public void getAllPhotos(){
        resultsMutable.setValue(allPhotos);
    }

    class SearchObserver implements SingleObserver<ArrayList<PhotoModel>>{

        Disposable d;
        @Override
        public void onSubscribe(Disposable d) {
            this.d = d;
        }

        @Override
        public void onSuccess(ArrayList<PhotoModel> photoModels) {
            allPhotos.addAll(photoModels);
            resultsMutable.setValue(photoModels);
            if(d != null)
                d.dispose();
        }

        @Override
        public void onError(Throwable e) {
            Log.d("Error", e.getMessage());
        }
    }

}
