package com.apps.ahfreelancing.flickersearch.presentation.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.apps.ahfreelancing.flickersearch.R;
import com.apps.ahfreelancing.flickersearch.domain.model.PhotoModel;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by Ahmed Hassan on 8/6/2019.
 */
public class SearchResultsAdapter extends RecyclerView.Adapter<SearchResultsAdapter.PhotoHolder>{

    private ArrayList<PhotoModel> photos;
    private PhotoCallback callback;
    private Context context;
    public SearchResultsAdapter(ArrayList<PhotoModel> photos, PhotoCallback callback, Context context){
        this.callback = callback;
        this.photos = photos;
        this.context = context;
    }
    @NonNull
    @Override
    public PhotoHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        final View inflatedView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.cell_search_result, parent, false);
        return new PhotoHolder(inflatedView, callback, context);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoHolder holder, int position) {
        super.onViewAttachedToWindow(holder);
        holder.bind(photos.get(position));
    }

    @Override
    public int getItemCount() {
        return photos.size();
    }

    class PhotoHolder extends RecyclerView.ViewHolder{

        PhotoCallback callback;
        View view;
        ImageView photoImageView;
        TextView titleTextView;
        Context context;

        public PhotoHolder(@NonNull View itemView, PhotoCallback callback, Context context) {
            super(itemView);
            this.callback = callback;
            this.view = itemView;
            this.context = context;
        }

        public void bind(PhotoModel model){
            photoImageView = view.findViewById(R.id.photoImageView);
            titleTextView = view.findViewById(R.id.titleTextView);

            titleTextView.setText(model.getTitle());

            Picasso.with(context)
                    .load(model.getSmallUrl())
                    .into(photoImageView);

            photoImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onPhotoClicked(model);
                }
            });
        }
    }

    public interface PhotoCallback{
        void onPhotoClicked(PhotoModel model);
    }
}
