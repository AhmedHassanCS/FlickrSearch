package com.apps.ahfreelancing.flickersearch.presentation.di.module;

import android.content.Context;

import com.apps.ahfreelancing.flickersearch.data.cache.FlickrDatabase;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Ahmed Hassan on 8/6/2019.
 */

@Module
public class CacheModule {
    private Context context;

    public CacheModule(Context context){ this.context = context; }

    @Provides
    FlickrDatabase provideRoomDatabase(){
        return FlickrDatabase.getDatabase(context);
    }
}
