package com.apps.ahfreelancing.flickersearch.presentation.view.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.apps.ahfreelancing.flickersearch.presentation.di.component.DaggerFragmentComponent;
import com.apps.ahfreelancing.flickersearch.presentation.di.component.FragmentComponent;
import com.apps.ahfreelancing.flickersearch.presentation.di.module.ApiModule;
import com.apps.ahfreelancing.flickersearch.presentation.di.module.CacheModule;

/**
 * Created by Ahmed Hassan on 8/6/2019.
 */
public abstract class BaseFragment extends Fragment {
    private FragmentComponent daggerFragmentComponent;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initDagger();
    }

    public FragmentComponent getDaggerFragmentComponent() {
        return daggerFragmentComponent;
    }

    protected void initDagger(){
        daggerFragmentComponent = DaggerFragmentComponent
                .builder()
                .cacheModule(new CacheModule(getActivity()))
                .apiModule(new ApiModule())
                .build();
    }
}
