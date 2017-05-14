package com.enenim.movies.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.dsl.Unique;

/**
 * Created by enenim on 5/11/17.
 */

public class Trailer implements Parcelable {
    @SerializedName("db_id")
    private transient Long id = null;

    @SerializedName("id")
    @Expose
    @Unique
    private String trailerId;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("key")
    @Expose
    private String key;

    @SerializedName("")
    @Expose
    private String url;

    @SerializedName("site")
    @Expose
    private String site;

    @SerializedName("size")
    @Expose
    private String size;

    @SerializedName("clip")
    @Expose
    private String clip;

    @SerializedName("type")
    @Expose
    private String type;

    public Trailer(){

    }

    //Parcel Constructor
    public Trailer(Parcel in) {
        setId(in.readLong());
        setTrailerId(in.readString());
        setName(in.readString());
        setKey(in.readString());
        setUrl(in.readString());
        setSite(in.readString());
        setSize(in.readString());
        setClip(in.readString());
        setType(in.readString());
    }

    public static final Creator<Trailer> CREATOR = new Creator<Trailer>() {
        @Override
        public Trailer createFromParcel(Parcel in) {
            return new Trailer(in);
        }

        @Override
        public Trailer[] newArray(int size) {
            return new Trailer[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeLong(getId());
        dest.writeString(getTrailerId());
        dest.writeString(getName());
        dest.writeString(getKey());
        dest.writeString(getUrl());
        dest.writeString(getSite());
        dest.writeString(getSize());
        dest.writeString(getClip());
        dest.writeString(getType());
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTrailerId() {
        return trailerId;
    }

    public void setTrailerId(String trailerId) {
        this.trailerId = trailerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public String getClip() {
        return clip;
    }

    public void setClip(String clip) {
        this.clip = clip;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
