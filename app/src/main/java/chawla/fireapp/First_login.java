package chawla.fireapp;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class First_login extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.first_login);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);



    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_first_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    public static class PlaceholderFragment extends Fragment {

        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }


        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            if (getArguments().getInt(ARG_SECTION_NUMBER)==2){
                View rootView = inflater.inflate(R.layout.fragment_register, container, false);
                final EditText f_name = (EditText) rootView.findViewById(R.id.first_name);
                final EditText l_name = (EditText) rootView.findViewById(R.id.last_name);
                final EditText email = (EditText) rootView.findViewById(R.id.input_email);
                final EditText phone_number = (EditText) rootView.findViewById(R.id.phone);
                final EditText pass = (EditText) rootView.findViewById(R.id.input_pass);
                Button reg = (Button) rootView.findViewById(R.id.register);

                Firebase mainbase = new Firebase("https://fireapp-1914a.firebaseio.com/");
                final Firebase mainchild = mainbase.child("Users");

                reg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Map<String, String> tempuser = new HashMap<String, String>();
                        tempuser.put("First Name", f_name.getText().toString());
                        tempuser.put("Last Name", l_name.getText().toString());
                        tempuser.put("Email", email.getText().toString());
                        tempuser.put("Phone Number", phone_number.getText().toString());
                        tempuser.put("Password", pass.getText().toString());
                        mainchild.push().setValue(tempuser);

                        Toast.makeText(getActivity().getApplicationContext(), f_name.getText().toString()+", your account has been created", Toast.LENGTH_SHORT).show();

                    }
                });

                return rootView;
            }
            else{
                View rootView = inflater.inflate(R.layout.fragment_login, container, false);
                final Firebase Usersbase = new Firebase("https://fireapp-1914a.firebaseio.com/Users");
                final EditText in_email = (EditText) rootView.findViewById(R.id.input_email);
                final EditText in_password = (EditText) rootView.findViewById(R.id.input_password);
                TextView textView = (TextView) rootView.findViewById(R.id.no_account);

                Button login_btn = (Button) rootView.findViewById(R.id.btn_login);

                login_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Usersbase.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                for (DataSnapshot snapusers: dataSnapshot.getChildren()){
                                    Map<String, String> usermap = snapusers.getValue(Map.class);
                                    if ((usermap.get("Email").equalsIgnoreCase(in_email.getText().toString()))){
                                        if ((usermap.get("Password").equalsIgnoreCase(in_password.getText().toString()))){
                                            Toast.makeText(getActivity().getApplicationContext(),"Login Successfull!", Toast.LENGTH_SHORT).show();
                                            Intent bla= new Intent(getActivity(),MainActivity.class );
                                            startActivity(bla);

                                        }
                                        else{
                                            Toast.makeText(getActivity().getApplicationContext(),"Wrong Password", Toast.LENGTH_SHORT).show();

                                        }
                                    }
                                    else{
                                        Toast.makeText(getActivity().getApplicationContext(),"Username does not exist", Toast.LENGTH_SHORT).show();

                                    }
                                }
                            }

                            @Override
                            public void onCancelled(FirebaseError firebaseError) {

                            }
                        });
                    }
                });





                return rootView;

            }
        }
    }


    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 2;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Login";
                case 1:
                    return "Register";

            }
            return null;
        }
    }
}
