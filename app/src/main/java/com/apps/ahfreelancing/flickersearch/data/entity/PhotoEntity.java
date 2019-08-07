package com.apps.ahfreelancing.flickersearch.data.entity;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Ahmed Hassan on 8/4/2019.
 */
@Entity(tableName = "photo_table")
public class PhotoEntity {
    @NonNull
    @PrimaryKey
    private String id;
    private String owner;
    private String secret;
    private String server;
    private int farm;
    private String title;
    @SerializedName("ispublic")
    private int isPublic;

    public PhotoEntity(String id, String owner, String secret, String server, int farm, String title, int isPublic) {
        this.id = id;
        this.owner = owner;
        this.secret = secret;
        this.server = server;
        this.farm = farm;
        this.title = title;
        this.isPublic = isPublic;
    }

    public String getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getSecret() {
        return secret;
    }

    public String getServer() {
        return server;
    }

    public int getFarm() {
        return farm;
    }

    public String getTitle() {
        return title;
    }

    public int isPublic() {
        return isPublic;
    }
}
