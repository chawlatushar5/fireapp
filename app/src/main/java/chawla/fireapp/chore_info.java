package chawla.fireapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.Map;

public class chore_info extends AppCompatActivity {

    private static final String TAG = "Info for the Chore";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chore_info);
        final String bla = getIntent().getExtras().getString("my_object");
        Log.e("Reason", bla);
        Firebase main = new Firebase("https://fireapp-1914a.firebaseio.com/Jobs");

        main.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot postsnapsht : dataSnapshot.getChildren()) {
                    String the_KEY = postsnapsht.getKey().toString();
                    if (the_KEY.equalsIgnoreCase(bla.toString())) {
                        Map<String, String> map= postsnapsht.getValue(Map.class);
                        Log.e(TAG, "JOB TITLE"+ map.get("Job Title"));
                        Log.e(TAG, "Job Description"+ map.get("Job Description"));
                        Log.e(TAG, "Location"+ map.get("Location"));
                        Log.e(TAG, "Cost"+ map.get("Cost"));

                        Log.e(TAG, "Time"+ map.get("Time"));
                        Log.e(TAG, "JOB status"+ map.get("Job Status"));
                        Log.e(TAG, "JOB uploader"+ map.get("Uploader name"));
                        Log.e(TAG, "JOB Assignee"+ map.get("Assignee"));
                        Log.e(TAG, "JOB Catagory"+ map.get("Category"));


                        //, map.get("Time"), map.get("Job Status"), map.get("Uploader name"), map.get("Assignee"), map.get("Category")


                    }
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });


    }
}
