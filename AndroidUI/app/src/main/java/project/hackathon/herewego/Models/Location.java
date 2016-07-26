package project.hackathon.herewego.models;

/**
 * Created by saikat on 7/24/2016.
 */
public class Location {

    String Id;
    double Latitude;
    double Longitude;
    double rating;
    String category;
    String[] ImageUrl;
    String Name;
    int time;
    int durationToVisit;
    LocationVisitingSchedule schedule;


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

    public String[] getImageUrl() {
        return ImageUrl;
    }

    public int getTime() {
        return time;
    }


    public String getName() {
        return Name;
    }


    public Location(String id, String name) {
        this.Id = id;
        this.category = null;
        this.ImageUrl = null;
        this.Latitude = 0.0;
        this.Longitude = 0.0;
        this.rating = 0.0;
        this.time = 0;
        this.Name = name;
    }

    public Location(String id, String name, String latitude,
                    String longitude, String rating, String category,
                    String[] ImageUrl, LocationVisitingSchedule schedule, String time) {
        this.Id = id;
        this.Name = name;
        this.Latitude = Double.parseDouble(latitude);
        this.Longitude = Double.parseDouble(longitude);
        this.rating = Double.parseDouble(rating);
        this.category = category;
        this.ImageUrl = ImageUrl;
        this.schedule = schedule;
        this.time = Integer.parseInt(time);
    }


}
