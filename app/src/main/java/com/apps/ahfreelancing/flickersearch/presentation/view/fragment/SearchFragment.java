package com.apps.ahfreelancing.flickersearch.presentation.view.fragment;


import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.AbsListView;
import android.widget.EditText;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.apps.ahfreelancing.flickersearch.R;
import com.apps.ahfreelancing.flickersearch.domain.model.PhotoModel;
import com.apps.ahfreelancing.flickersearch.presentation.view.adapter.SearchResultsAdapter;
import com.apps.ahfreelancing.flickersearch.presentation.viewmodel.SearchViewModel;
import com.apps.ahfreelancing.flickersearch.presentation.viewmodel.ViewModelFactory;

import java.util.ArrayList;

import javax.inject.Inject;

public class SearchFragment extends BaseFragment {

    @Inject
    ViewModelFactory<SearchViewModel> viewModelFactory;
    SearchViewModel searchViewModel;

    private RecyclerView photosRecyclerView;
    private EditText searchEditText;

    private SearchResultsAdapter adapter;
    private ArrayList<PhotoModel> photoModels;


    public SearchFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getDaggerFragmentComponent().inject(this);
        searchViewModel = ViewModelProviders.of(this, viewModelFactory).get(SearchViewModel.class);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);
        photosRecyclerView = view.findViewById(R.id.photosRecyclerView);
        searchEditText = view.findViewById(R.id.searchEditText);
        setupRecyclerView();
        setupEditText();
        return view;
    }

    private void setupEditText() {
        searchEditText.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_SEARCH){
                InputMethodManager imm = (InputMethodManager)getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.hideSoftInputFromWindow(getView().getWindowToken(), 0);

                photoModels.clear();
                adapter.notifyDataSetChanged();
                searchViewModel.searchPhotos(searchEditText.getText().toString());
                return true;
            }
            return false;
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        observeViewModel();
    }

    private void setupRecyclerView(){
        photosRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));

        if(photosRecyclerView.getAdapter() == null){
            if(photoModels == null)
                photoModels = new ArrayList<>();
            adapter = new SearchResultsAdapter(photoModels, this::navigateToPhotoFragment, getActivity());
            photosRecyclerView.setAdapter(adapter);

            photosRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
                @Override
                public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                    super.onScrolled(recyclerView, dx, dy);
                    if (((GridLayoutManager) photosRecyclerView.getLayoutManager())
                            .findLastCompletelyVisibleItemPosition() == photoModels.size() - 1)
                        searchViewModel.getNextPage();
                }
            });
        }
    }

    private void observeViewModel(){
        if(!searchViewModel.subscribeResults().hasObservers()) {
            searchViewModel.subscribeResults().observe(this, (photos) -> {
                int startPos = photoModels.size();
                photoModels.addAll(photos);
                adapter.notifyItemRangeInserted(startPos, photos.size());
            });
        } else {
            searchViewModel.getAllPhotos();
        }
    }

    private void navigateToPhotoFragment(PhotoModel model){
        Log.d("Large_Image_URL", model.getLargeUrl());
        SearchFragmentDirections.ActionSearchFragmentToPhotoFragment action =
                SearchFragmentDirections.actionSearchFragmentToPhotoFragment(model.getLargeUrl());
        Navigation.findNavController(getView()).navigate(action);
    }

}
