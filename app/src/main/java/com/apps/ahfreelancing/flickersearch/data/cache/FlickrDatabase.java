package com.apps.ahfreelancing.flickersearch.data.cache;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.apps.ahfreelancing.flickersearch.data.entity.PhotoEntity;

/**
 * Created by Ahmed Hassan on 8/7/2019.
 */
@Database(entities = {PhotoEntity.class}, version = 1)
public abstract class FlickrDatabase extends RoomDatabase {
    public abstract PhotoDao photoDao();

    private static FlickrDatabase INSTANCE;

    public static FlickrDatabase getDatabase(Context context){
        FlickrDatabase tempINSTANCE = INSTANCE;

        if(tempINSTANCE != null)
            return tempINSTANCE;

        synchronized (context){
            FlickrDatabase instance = Room.databaseBuilder(
                    context.getApplicationContext(),
                    FlickrDatabase.class,
                    "photo_database"
                    ).build();

            INSTANCE = instance;
            return instance;
        }
    }
}
