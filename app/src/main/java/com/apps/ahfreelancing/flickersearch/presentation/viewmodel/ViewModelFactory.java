package com.apps.ahfreelancing.flickersearch.presentation.viewmodel;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import javax.inject.Inject;

/**
 * Created by Ahmed Hassan on 8/6/2019.
 */
public class ViewModelFactory <T extends ViewModel> implements ViewModelProvider.Factory {

    T viewModel;
    @Inject
    public ViewModelFactory(T viewModel){
        this.viewModel = viewModel;
    }

    @NonNull
    @Override
    public <V extends ViewModel> V create(@NonNull Class<V> modelClass) {
        return (V) viewModel;
    }
}
