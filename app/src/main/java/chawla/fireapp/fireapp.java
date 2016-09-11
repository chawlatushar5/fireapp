package chawla.fireapp;

import android.app.Application;

import com.firebase.client.Firebase;

/**
 * Created by Tushar on 9/10/16.
 */
public class fireapp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        Firebase.setAndroidContext(this);




    }
}
