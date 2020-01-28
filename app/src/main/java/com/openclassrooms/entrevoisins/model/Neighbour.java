package com.openclassrooms.entrevoisins.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.Objects;

/**
 * Model object representing a Neighbour
 */
public class Neighbour implements Parcelable {
    public static final String NEIGHBOUR_EXTRA = "NEIGHBOUR_EXTRA" ;



    /** Identifier */
    private Integer id;

    /** Full name */
    private String name;

    /** Avatar */
    private String avatarUrl;

    /** Adress */
    private String adress;

    /** Telephone */
    private String tel;

    /** Url */
    private String url;

    /** Description */
    private String description;

    /** favorit**/
    private boolean favoris;

    /**
     * Constructor
     * @param id
     * @param name
     * @param avatarUrl
     * @param adress
     * @param tel
     * @param url
     * @param description
     */

    public Neighbour(Integer id, String name, String avatarUrl, String adress, String tel, String url, String description) {
        this.id = id;
        this.name = name;
        this.avatarUrl = avatarUrl;
        this.adress = adress;
        this.tel = tel;
        this.url = url;
        this.description = description;
    }

    public Neighbour(Integer id){
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAvatarUrl() {
        return avatarUrl;
    }

    public void setAvatarUrl(String avatarUrl) {
        this.avatarUrl = avatarUrl;
    }

    public String getAdress() {
        return adress;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Neighbour neighbour = (Neighbour) o;
        return Objects.equals(id, neighbour.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this.id);
        dest.writeString(this.name);
        dest.writeString(this.avatarUrl);
        dest.writeString(this.adress);
        dest.writeString(this.tel);
        dest.writeString(this.url);
        dest.writeString(this.description);
    }

    protected Neighbour(Parcel in) {
        this.id = (Integer) in.readValue(Integer.class.getClassLoader());
        this.name = in.readString();
        this.avatarUrl = in.readString();
        this.adress = in.readString();
        this.tel = in.readString();
        this.url = in.readString();
        this.description = in.readString();
    }

    public static final Parcelable.Creator<Neighbour> CREATOR = new Parcelable.Creator<Neighbour>() {
        @Override
        public Neighbour createFromParcel(Parcel source) {
            return new Neighbour(source);
        }

        @Override
        public Neighbour[] newArray(int size) {
            return new Neighbour[size];
        }
    };

    public void setFavoris(boolean favoris) {
        this.favoris = favoris;
    }

    public boolean isFavoris() {return favoris;
    }
}
