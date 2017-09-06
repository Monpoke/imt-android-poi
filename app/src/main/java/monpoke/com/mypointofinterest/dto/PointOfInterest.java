package monpoke.com.mypointofinterest.dto;

import java.io.Serializable;

/**
 * Created by A643012 on 05/09/2017.
 */

public class PointOfInterest implements Serializable {

    private String title;

    private String description;

    private float rating;

    private double geo_lat;

    private double geo_long;

    public PointOfInterest(String title, String description, double geo_lat, double geo_long) {
        this.title = title;
        this.description = description;
        this.geo_lat = geo_lat;
        this.geo_long = geo_long;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public double getGeo_lat() {
        return geo_lat;
    }

    public void setGeo_lat(double geo_lat) {
        this.geo_lat = geo_lat;
    }

    public double getGeo_long() {
        return geo_long;
    }

    public void setGeo_long(double geo_long) {
        this.geo_long = geo_long;
    }


    @Override
    public String toString() {
        return getTitle();
    }
}
