package com.maple.rimaproject.Retrofit;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class Project implements Serializable{



    @SerializedName("reference_id")
    @Expose
    private String referenceId;
    @SerializedName("area")
    @Expose
    private String area;
    @SerializedName("details")
    @Expose
    private String details;
    @SerializedName("location")
    @Expose
    private String location;
    @SerializedName("latitude")
    @Expose
    private String latitude;
    @SerializedName("longitude")
    @Expose
    private String longitude;
    @SerializedName("prices_from")
    @Expose
    private String pricesFrom;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("plan1")
    @Expose
    private String plan1;
    @SerializedName("plan2")
    @Expose
    private String plan2;
    @SerializedName("types")
    @Expose
    private String types;
    @SerializedName("sizes")
    @Expose
    private String sizes;
    @SerializedName("features")
    @Expose
    private String features;
    @SerializedName("create_date")
    @Expose
    private String createDate;
    @SerializedName("update_date")
    @Expose
    private String updateDate;
    @SerializedName("id")
    @Expose
    private int id;
    @SerializedName("sliders")
    @Expose
    private List<Slider> sliders = null;


    public Project(){

    }

    protected Project(Parcel in) {
        id = in.readInt();
        referenceId = in.readString();
        area = in.readString();
        details = in.readString();
        location = in.readString();
        latitude = in.readString();
        longitude = in.readString();
        pricesFrom = in.readString();
        status = in.readString();
        plan1 = in.readString();
        plan2 = in.readString();
        types = in.readString();
        sizes = in.readString();
        features = in.readString();
        createDate = in.readString();
        updateDate = in.readString();
    }

    public String getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(String referenceId) {
        this.referenceId = referenceId;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getPricesFrom() {
        return pricesFrom;
    }

    public void setPricesFrom(String pricesFrom) {
        this.pricesFrom = pricesFrom;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getPlan1() {
        return plan1;
    }

    public void setPlan1(String plan1) {
        this.plan1 = plan1;
    }

    public String getPlan2() {
        return plan2;
    }

    public void setPlan2(String plan2) {
        this.plan2 = plan2;
    }

    public String getTypes() {
        return types;
    }

    public void setTypes(String types) {
        this.types = types;
    }

    public String getSizes() {
        return sizes;
    }

    public void setSizes(String sizes) {
        this.sizes = sizes;
    }

    public String getFeatures() {
        return features;
    }

    public void setFeatures(String features) {
        this.features = features;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(String updateDate) {
        this.updateDate = updateDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Slider> getSliders() {
        return sliders;
    }

    public void setSliders(List<Slider> sliders) {
        this.sliders = sliders;
    }


//-----------------------------------com.example.Slider.java-----------------------------------


    public class Slider implements Serializable {

        @SerializedName("photo_path")
        @Expose
        private String photoPath;
        @SerializedName("status")
        @Expose
        private String status;

        protected Slider(Parcel in) {
            photoPath = in.readString();
            status = in.readString();
        }






        public String getPhotoPath() {
            return photoPath;
        }

        public void setPhotoPath(String photoPath) {
            this.photoPath = photoPath;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }



        public void readFromParcel(Parcel in) {
           photoPath = in.readString();
           status = in.readString();
        }
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Project other = (Project) obj;
        if (id != other.id)
            return false;
        return true;

    }




    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }



}



