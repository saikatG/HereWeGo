package project.hackathon.herewego.Models;

import android.content.SharedPreferences;

/**
 * Created by kabaska on 26-Jul-16.
 */
public class User {
    private static User currentUser;
    private String userID;
    private String firstName;
    private String lastName;
    private String email;
    private String gender;
    private String profilePicURL;

    private User() {
        SharedPreferences preferences = HWGSharedPreferences.getSharedPreferences();
        this.userID = preferences.getString("currentUserID", "");
        this.firstName = preferences.getString("currentUserID", "");
        this.lastName = preferences.getString("currentUserID", "");
        this.email = preferences.getString("currentUserID", "");
        this.gender = preferences.getString("currentUserID", "");
        this.profilePicURL = preferences.getString("currentUserID", "");
    }
    public static User getCurrentUser() {
        if(currentUser == null)
            currentUser = new User();
        return currentUser;
    }
}
