package project.hackathon.herewego.Models;

import android.graphics.Bitmap;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.net.URL;
import java.util.Date;

/**
 * Created by parroy on 25-Jul-16.
 */
public class Review implements Serializable {
    public String UserId ;

    public String Statement;

    public DateTime date ;

    public float Rating;

    public Review(String url, String comment, float rating, String userid, DateTime date){
        Statement = comment;
        Rating = rating;
        UserId = userid;
        this.date = date;
    }

    @Override
    public String toString() {
        return this.UserId + this.Statement + this.Rating;
    }
}
