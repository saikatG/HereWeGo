package project.hackathon.herewego.models;

/**
 * Created by paverm on 27-07-2016.
 */

public class FeedEntity {
    public String UserImage;
    public String Comment;
    public String Date;
    public String UserName;
    public String TripImage;

    public FeedEntity(String image, String statement, String name, String date, String TripImage1)
    {
        UserImage=image;
        Comment=statement;
        UserName=name;
        Date=date;
        TripImage = TripImage1;
    };
}
