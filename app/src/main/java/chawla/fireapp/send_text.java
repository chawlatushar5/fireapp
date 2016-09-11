package chawla.fireapp;

import android.location.Location;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Tushar on 9/11/16.
 */
public class send_text extends AsyncTask<Job_class, String, String> {

    @Override
    protected String doInBackground(Job_class... job_classes) {

        HttpURLConnection aurlConnection=null;
        BufferedReader areader = null;
        String Chetan_mobile= null;

        if (job_classes[0].getCatogory().equalsIgnoreCase("Handyman") || job_classes[0].getCatogory().equalsIgnoreCase("Movers") || job_classes[0].getCatogory().equalsIgnoreCase("Cleaning")) {

            Chetan_mobile = "17033888582&text=";
        }
        else {
            Chetan_mobile = "12672407109&text=";
        }


      //  Log.e("The E", "number is:" +Tushar_mobile);

        String url = "https://rest.nexmo.com/sms/json?api_key=2051add4&api_secret=6c6452d6d75cf15d&from=12182967184&to=";

        String Message = "Hey+we+have+a+great+opportunity+for+you+to+earn+some+extra+Bucks.+Here+is+the+info+of+the+Job+if+you+want+to+take+it.+Job+Title:+"
         +job_classes[0].getJob_title_() +"+Job+Description:+" + job_classes[0].getJob_description_().toString() + "+Location:+" +  job_classes[0].getLocation_().toString() +
        "+Time:+" + job_classes[0].getTime_().toString()+"+Cost:+" + job_classes[0].getCost().toString()+"+Uploader:+Tusky";
        String newMessage = Message.replaceAll(" ", "+");
        Log.e("The"," message is " + newMessage);

        url+=Chetan_mobile;
        url += newMessage;
        URL aurl = null;
        try {
            aurl = new URL(url);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        Log.e("The E", "URL is:" +url);

        try {
            aurlConnection = (HttpURLConnection) aurl.openConnection();
            aurlConnection.setRequestMethod("POST");
            aurlConnection.connect();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int responseCode = 0;
        try {
            responseCode = aurlConnection.getResponseCode();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.e("Sending", "'Get' request to URL : " + aurl);
        Log.e("Response"," Code : " + responseCode);

        String check = ""+responseCode;


        return check;
    }
}
