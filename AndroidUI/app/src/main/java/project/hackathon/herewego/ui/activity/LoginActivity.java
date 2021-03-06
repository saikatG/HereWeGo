package project.hackathon.herewego.ui.activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;
import android.widget.Toast;

import com.facebook.CallbackManager;
import com.facebook.FacebookCallback;
import com.facebook.FacebookException;
import com.facebook.GraphRequest;
import com.facebook.GraphResponse;
import com.facebook.login.LoginResult;
import com.facebook.login.widget.LoginButton;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import project.hackathon.herewego.R;
import project.hackathon.herewego.Models.HWGSharedPreferences;

public class LoginActivity extends AppCompatActivity {

    public CallbackManager callbackManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        overridePendingTransition(0,0);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        HWGSharedPreferences.init(this);
        callbackManager = CallbackManager.Factory.create();

        scaleAnimation();

        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        ArrayList<String> permissions = new ArrayList<String>();
        permissions.add("public_profile,user_photos,user_about_me,email");
        loginButton.setReadPermissions(permissions);

        loginButton.registerCallback(callbackManager, new FacebookCallback<LoginResult>() {
            @Override
            public void onSuccess(LoginResult loginResult) {
                LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
                loginButton.setVisibility(View.INVISIBLE);

                GraphRequest fetchUserDetails = GraphRequest.newMeRequest(
                    loginResult.getAccessToken(), new GraphRequest.GraphJSONObjectCallback() {
                        @Override
                        public void onCompleted(JSONObject json, GraphResponse response) {
                            if (response.getError() != null) {
                                // handle error
                                System.out.println("ERROR");
                            } else {
                                System.out.println("Success");
                                try {
                                    String email = json.getString("email");
                                    String id = json.getString("id");
                                    String firstname = json.getString("first_name");
                                    String lastname = json.getString("last_name");
                                    JSONObject profilePicture = json.getJSONObject("picture").getJSONObject("data");
                                    String profilePictureURL = profilePicture.getString("url");

                                    SharedPreferences preferences = getSharedPreferences("project.hackathon.herewego", MODE_PRIVATE);
                                    preferences.edit().putString("currentUserID", id).apply();
                                    preferences.edit().putString("currentUserFirstName", firstname).apply();
                                    preferences.edit().putString("currentUserLastName", lastname).apply();
                                    preferences.edit().putString("currentUserEMail", email).apply();
                                    preferences.edit().putString("currentUserProfilePictureURL", profilePictureURL).apply();
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }
                            }
                        }
                    });
                Bundle parameters = new Bundle();
                parameters.putString("fields", "id,email,first_name,last_name,gender,picture");
                fetchUserDetails.setParameters(parameters);
                fetchUserDetails.executeAsync();
                goToHome();
            }

            @Override
            public void onCancel() {
                onFBLoginFail();
            }

            @Override
            public void onError(FacebookException exception) {
                onFBLoginFail();
            }
        });

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        callbackManager.onActivityResult(requestCode, resultCode, data);
    }

    private void onFBLoginFail() {
        Toast.makeText(this, "Unable to login!", Toast.LENGTH_SHORT).show();
    }

    private void goToHome() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
    }

    private void scaleAnimation()
    {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.alpha);
        a.reset();
        LoginButton loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.clearAnimation();
        loginButton.startAnimation(a);
        a.reset();
    }
    private void alphaAnimation()
    {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.alpha);
        a.reset();
        TextView tv = (TextView) findViewById(R.id.appNameLogin);
        tv.clearAnimation();
        tv.startAnimation(a);
        a.reset();
    }


}
