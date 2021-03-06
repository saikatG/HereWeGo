package project.hackathon.herewego.Models;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

/**
 * Created by saikat on 7/24/2016.
 */

public class Location implements Serializable {

    String Id;
    double Latitude;
    double Longitude;
    double rating;
    String category;
    String ImageUrl;
    String Name;
    int time;
    int durationToVisit;
    LocationVisitingSchedule schedule;
    int Time;
    List<String> Images;
    String City;
    String Country;
    String Summary;
    String Address;
    String ContactNumber;
    String WebsiteUrl;
    HashMap<String, List<Tuple<DateTime, DateTime>>> OpenSchedule;
    List<Review> Reviews;
    CategoryType Category;
    float AverageRating;
    int DurationToVisit;
    boolean IsPreferred;

    public void setId(String id) {
        Id = id;
    }

    public String getScheduleTimes(String day){
        List<Tuple<DateTime,DateTime>> dates = this.getOpenSchedule().get(day);
        String time = "";
        for(Tuple<DateTime,DateTime> t:dates){
            time+=t.x.toString("HH:mm:ss")+" - "+t.y.toString("HH:mm:ss");
            time+=", ";
        }
        time = time.substring(0,time.length()-2);
        return time;
    }

    public void setLatitude(double latitude) {
        Latitude = latitude;
    }

    public void setLongitude(double longitude) {
        Longitude = longitude;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public void setPreferred(boolean preferred) {
        IsPreferred = preferred;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public void setName(String name) {
        Name = name;
    }

    public void setTime(int time) {
        Time = time;
    }

    public void setImages(List<String> images) {
        Images = images;
    }

    public void setCity(String city) {
        City = city;
    }

    public void setCountry(String country) {
        Country = country;
    }

    public void setSummary(String summary) {
        Summary = summary;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public void setContactNumber(String contactNumber) {
        ContactNumber = contactNumber;
    }

    public void setWebsiteUrl(String websiteUrl) {
        WebsiteUrl = websiteUrl;
    }

    public void setOpenSchedule(HashMap<String, List<Tuple<DateTime, DateTime>>> openSchedule) {
        OpenSchedule = openSchedule;
    }

    public void setReviews(List<Review> reviews) {
        Reviews = reviews;
    }

    public void setCategory(CategoryType category) {
        Category = category;
    }

    public void setAverageRating(float averageRating) {
        AverageRating = averageRating;
    }

    public void setDurationToVisit(int durationToVisit) {
        DurationToVisit = durationToVisit;
    }

    public int getDurationToVisit() {
        return DurationToVisit;
    }

    public List<String> getImages() {
        return Images;
    }

    public String getCity() {
        return City;
    }

    public String getCountry() {
        return Country;
    }

    public String getAddress() {
        return Address;
    }

    public String getContactNumber() {
        return ContactNumber;
    }

    public String getWebsiteUrl() {
        return WebsiteUrl;
    }

    public HashMap<String, List<Tuple<DateTime, DateTime>>> getOpenSchedule() {
        return OpenSchedule;
    }

    public List<Review> getReviews() {
        return Reviews;
    }

    public float getAverageRating() {
        return AverageRating;
    }



    public String getSummary() {  return Summary; }

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

    public String getImageUrl() {
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
                    String longitude, double rating, String category,
                    String ImageUrl, LocationVisitingSchedule schedule, int time) {
        this.Id = id;
        this.Name = name;
        this.Latitude = Double.parseDouble(latitude);
        this.Longitude = Double.parseDouble(longitude);
        this.rating = rating;
        this.category = category;
        this.ImageUrl = ImageUrl;
        this.schedule = schedule;
        this.time = time;
    }

    public Location(String id, String name, double latitude, double longitude, double rating, String category, boolean IsPreferred, String ImageUrl,String ImageUrl1,String ImageUrl2, int time){
        Images = new ArrayList<String>();
        this.Id =id;
        this.category=category;
        this.ImageUrl= ImageUrl;
        this.IsPreferred= IsPreferred;
        this.Latitude=latitude;
        this.Longitude=longitude;
        this.rating=rating;
        this.Time= time;
        this.Name = name;
        this.Images.add(ImageUrl1);
        this.Images.add(ImageUrl2);
        this.Images.add(ImageUrl1);
        this.durationToVisit = 1;
        this.time = 1;
    }

    public Location(Location location){
        this.Id =location.Id;
        this.category=location.category;
        this.ImageUrl= location.ImageUrl;
        this.IsPreferred= location.IsPreferred;
        this.Latitude=location.Latitude;
        this.Longitude=location.Longitude;
        this.rating=location.rating;
        this.Time= location.Time;
        this.Name = location.Name;
    }

    public Location(){

    }


}
