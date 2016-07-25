package project.hackathon.herewego.Models;

/**
 * Created by saikat on 7/24/2016.
 */
public class Location {

    String Id;
    double Latitude;
    double Longitude;
    double rating;
    String category;
    boolean IsPreferred;
    String ImageUrl;
    String Name;
    int Time;


    public String getId() {
        return Id;
    }

    public double getLatitude() {
        return Latitude;
    }

    public double getLongitude() {
        return Longitude;
    }

    public double getRating() {
        return rating;
    }

    public String getCategory() {
        return category;
    }

    public boolean isPreferred() {
        return IsPreferred;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public int getTime() {
        return Time;
    }



    public String getName() {
        return Name;
    }


    public Location(String id, String name, double latitude, double longitude, double rating, String category, boolean IsPreferred, String ImageUrl, int time){
        this.Id =id;
        this.category=category;
        this.ImageUrl= ImageUrl;
        this.IsPreferred= IsPreferred;
        this.Latitude=latitude;
        this.Longitude=longitude;
        this.rating=rating;
        this.Time= time;
        this.Name = name;
    }


}
