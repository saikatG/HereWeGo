package project.hackathon.herewego.models;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by kabaska on 26-Jul-16.
 */
public class HWGSharedPreferences {
    private static SharedPreferences sharedPreferences;
    private HWGSharedPreferences() {

    }
    public static void init(Context context){
        if(sharedPreferences == null)
            sharedPreferences = context.getSharedPreferences("project.hackathon.herewego", Context.MODE_PRIVATE);
    }

    public static SharedPreferences getSharedPreferences() {
        return sharedPreferences;
    }
}
