package chawla.fireapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.Firebase;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutionException;

public class Main2Activity extends AppCompatActivity implements AdapterView.OnItemSelectedListener{

    private static final String TAG = "Adding Chores";
    private TextView myText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.e("H:", "I am here");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        final EditText Job_title = (EditText) findViewById(R.id.editText);
        final EditText Job_des = (EditText) findViewById(R.id.editText2);
        final EditText Location = (EditText) findViewById(R.id.location);
        final EditText Cost = (EditText) findViewById(R.id.cost);
        final EditText Time = (EditText) findViewById(R.id.time);


        Spinner spinner = (Spinner) findViewById(R.id.planets_spinner);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
// Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
// Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);


        Button btn = (Button) findViewById(R.id.button2);
        final Firebase mrootf = new Firebase("https://fireapp-1914a.firebaseio.com/");
        final Firebase jobroot = mrootf.child("Jobs");

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Log.e("The E", "number is testqwe:" );

                Map<String, String > Job = new HashMap<String, String>();
                Job.put("Job Title",Job_title.getText().toString());
                Job.put("Job Description", Job_des.getText().toString());
                Job.put("Location", Location.getText().toString());
                Job.put("Cost", Cost.getText().toString());
                Job.put("Time", Time.getText().toString());
                Job.put("Job Status", "False");
                Job.put("Uploader name", "Tusky");
                Job.put("Category", myText.getText().toString());
                Job.put("Assignee", "Null");
                jobroot.push().setValue(Job);

                String code = null;

                Job_class rest = new Job_class(Job_title.getText().toString(),Job_des.getText().toString(),Location.getText().toString(), Cost.getText().toString(),  Time.getText().toString(), "false","Tusky", "null", myText.getText().toString() );

                try {
                    code = new send_text().execute(rest).get();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (ExecutionException e) {
                    e.printStackTrace();
                }

                    Log.d(TAG,"The messege result: " + code );



                startActivity(new Intent(Main2Activity.this, MainActivity.class));



            }
        });

    }





    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        myText =(TextView) view;
        Toast.makeText(this, "Text will be sent to all " + myText.getText()+" of this area.", Toast.LENGTH_SHORT).show();
        // An item was selected. You can retrieve the selected item using
        // parent.getItemAtPosition(pos)
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {
        // Another interface callback
    }
}
