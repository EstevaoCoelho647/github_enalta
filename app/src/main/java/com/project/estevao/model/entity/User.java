package com.project.estevao.apigit.model.entity;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.ArrayList;

/**
 * Created by Estevao on 07/08/2016.
 */

@JsonIgnoreProperties(ignoreUnknown = true)
public class User implements Parcelable {
    @JsonIgnore
    private Long id;
    @JsonProperty("id")
    private Long idWeb;
    @JsonProperty("avatar_url")
    private String avatar;
    @JsonIgnore
    private byte[] avatarBytes;
    private String login;
    @JsonProperty("repos_url")
    private String repositoryUrl;
    @JsonIgnore
    private ArrayList<Repository> repositories;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getAvatarBytes() {
        return avatarBytes;
    }

    public void setAvatarBytes(byte[] avatarBytes) {
        this.avatarBytes = avatarBytes;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getRepositoryUrl() {
        return repositoryUrl;
    }

    public void setRepositoryUrl(String repositoryUrl) {
        this.repositoryUrl = repositoryUrl;
    }

    public ArrayList<Repository> getRepositories() {
        return repositories;
    }

    public void setRepositories(ArrayList<Repository> repositories) {
        this.repositories = repositories;
    }

    public Long getIdWeb() {
        return idWeb;
    }

    public void setIdWeb(Long idWeb) {
        this.idWeb = idWeb;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeValue(this.idWeb);
        dest.writeString(this.avatar);
        dest.writeByteArray(this.avatarBytes);
        dest.writeString(this.login);
        dest.writeString(this.repositoryUrl);
        dest.writeTypedList(this.repositories);
    }

    public User() {
    }

    protected User(Parcel in) {
        this.id = (Long) in.readValue(Long.class.getClassLoader());
        this.idWeb = (Long) in.readValue(Long.class.getClassLoader());
        this.avatar = in.readString();
        this.avatarBytes = in.createByteArray();
        this.login = in.readString();
        this.repositoryUrl = in.readString();
        this.repositories = in.createTypedArrayList(Repository.CREATOR);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel source) {
            return new User(source);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
