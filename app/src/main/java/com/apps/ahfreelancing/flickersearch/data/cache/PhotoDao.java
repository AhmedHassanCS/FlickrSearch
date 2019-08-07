package com.apps.ahfreelancing.flickersearch.data.cache;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.apps.ahfreelancing.flickersearch.data.entity.PhotoEntity;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Single;

/**
 * Created by Ahmed Hassan on 8/7/2019.
 */

@Dao
public interface PhotoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertPhotos(ArrayList<PhotoEntity> photos);

    @Query("SELECT * FROM photo_table WHERE title LIKE :title ORDER BY title LIMIT :pageSize OFFSET :offset")
    Single<List<PhotoEntity>> getPhotos(String title, int pageSize, int offset);
}
