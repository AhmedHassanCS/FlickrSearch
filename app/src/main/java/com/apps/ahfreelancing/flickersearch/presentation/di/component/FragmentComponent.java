package com.apps.ahfreelancing.flickersearch.presentation.di.component;

import com.apps.ahfreelancing.flickersearch.data.cache.FlickrDatabase;
import com.apps.ahfreelancing.flickersearch.presentation.di.module.ApiModule;
import com.apps.ahfreelancing.flickersearch.presentation.di.module.CacheModule;
import com.apps.ahfreelancing.flickersearch.presentation.di.module.ThreadModule;
import com.apps.ahfreelancing.flickersearch.presentation.view.fragment.SearchFragment;

import javax.inject.Named;

import dagger.Component;
import io.reactivex.Scheduler;
import retrofit2.Retrofit;

/**
 * Created by Ahmed Hassan on 8/6/2019.
 */
@Component(modules = {ApiModule.class, CacheModule.class, ThreadModule.class})
public interface FragmentComponent {
    void inject(SearchFragment searchFragment);

    Retrofit retrofit();

    FlickrDatabase flickrDatabase();

    @Named("observeOn")
    Scheduler observeScheduler();

    @Named("subscribeOn")
    Scheduler subscribeScheduler();
}
