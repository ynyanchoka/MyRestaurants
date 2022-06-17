
package com.moringaschool.myrestaurants.models;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.moringaschool.myrestaurants.models.Business;
import com.moringaschool.myrestaurants.models.Region;

import org.parceler.Parcel;

@Parcel
public class YelpBusinessesSearchResponse {

    @SerializedName("total")
    @Expose
    private Integer total;
    @SerializedName("businesses")
    @Expose
    private ArrayList<Business> businesses = null;
    @SerializedName("region")
    @Expose
    private Region region;

    /**
     * No args constructor for use in serialization
     * 
     */
    public YelpBusinessesSearchResponse() {
    }

    /**
     * 
     * @param total
     * @param region
     * @param businesses
     */
    public YelpBusinessesSearchResponse(Integer total, ArrayList<Business> businesses, Region region) {
        super();
        this.total = total;
        this.businesses = businesses;
        this.region = region;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public ArrayList<Business> getBusinesses() {
        return businesses;
    }

    public void setBusinesses(ArrayList<Business> businesses) {
        this.businesses = businesses;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

}
