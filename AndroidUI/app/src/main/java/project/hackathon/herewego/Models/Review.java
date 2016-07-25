package project.hackathon.herewego.Models;

import android.graphics.Bitmap;

import java.net.URL;

/**
 * Created by parroy on 25-Jul-16.
 */
public class Review {
    String UserImage;
    String Comment;
    Float Rating;
    String UserId;

    public Review(String url, String comment,float rating, String userid){
        UserImage = url;
        Comment = comment;
        Rating = rating;
        UserId = userid;
    }

    @Override
    public String toString() {
        return this.UserId + this.Comment + this.Rating;
    }
}
