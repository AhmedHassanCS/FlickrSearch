package com.apps.ahfreelancing.flickersearch.presentation.view.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.fragment.app.Fragment;

import com.apps.ahfreelancing.flickersearch.R;
import com.squareup.picasso.Picasso;


public class PhotoFragment extends Fragment {


    private ImageView photoImageView;
    private String url;
    public PhotoFragment() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        url = PhotoFragmentArgs.fromBundle(getArguments()).getUrl();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_photo, container, false);
        photoImageView = view.findViewById(R.id.photoImageView);
        Picasso.with(getContext())
                .load(url)
                .into(photoImageView);
        return view;
    }

}
