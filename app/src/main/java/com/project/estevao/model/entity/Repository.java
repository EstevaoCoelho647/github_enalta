package com.project.estevao.apigit.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Created by Estevao on 07/08/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class Repository implements Parcelable {

    @JsonIgnore
    private Long id;
    @JsonProperty("id")
    private Long idWeb;
    private String name;
    @JsonProperty("stargazers_count")
    private Integer stargazers;
    @JsonIgnore
    private Long idUser;

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdWeb() {
        return idWeb;
    }

    public void setIdWeb(Long idWeb) {
        this.idWeb = idWeb;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getStargazers() {
        return stargazers;
    }

    public void setStargazers(Integer stargazers) {
        this.stargazers = stargazers;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.idWeb);
        dest.writeString(this.name);
        dest.writeValue(this.stargazers);
        dest.writeValue(this.idUser);
    }

    public Repository() {
    }

    protected Repository(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.idWeb = (Long) in.readValue(Long.class.getClassLoader());
        this.name = in.readString();
        this.stargazers = (Integer) in.readValue(Integer.class.getClassLoader());
        this.idUser = (Long) in.readValue(Long.class.getClassLoader());
    }

    public static final Creator<Repository> CREATOR = new Creator<Repository>() {
        @Override
        public Repository createFromParcel(Parcel source) {
            return new Repository(source);
        }

        @Override
        public Repository[] newArray(int size) {
            return new Repository[size];
        }
    };
}
