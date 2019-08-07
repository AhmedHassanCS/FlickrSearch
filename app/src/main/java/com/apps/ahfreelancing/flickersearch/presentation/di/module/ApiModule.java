package com.apps.ahfreelancing.flickersearch.presentation.di.module;

import com.apps.ahfreelancing.flickersearch.BuildConfig;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

/**
 * Created by Ahmed Hassan on 8/6/2019.
 */

@Module
public class ApiModule {

    @Provides
    Retrofit provideRetrofit(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        //To log the response for testing
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(interceptor).build();

        return new Retrofit.Builder()
                .client(client)
                .baseUrl(BuildConfig.API_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

    }
}
