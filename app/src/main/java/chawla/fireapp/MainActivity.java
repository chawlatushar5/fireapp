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
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Vector;

public class MainActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_main);

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

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, Main2Activity.class));


            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {

        Vector<Job_class> temp;
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";
        private static final String TAG = "The Fragments";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
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
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 2) {
                final View rootView = inflater.inflate(R.layout.my_chores, container, false);
                //TextView text =(TextView) rootView.findViewById(R.id.textView);
                //text.setText("Hey BABES");
                Log.d(TAG, "The temp");





                return rootView;


            }
            if (getArguments().getInt(ARG_SECTION_NUMBER) == 1){
                Firebase mPref = new Firebase("https://fireapp-1914a.firebaseio.com/Jobs");
                final View rootView = inflater.inflate(R.layout.open_chores_data, container, false);




                mPref.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {


                        Log.d(TAG, "The Getting data counts:" + dataSnapshot.getChildrenCount());
                        temp = new Vector<Job_class>();
                        for (DataSnapshot postsnapsht : dataSnapshot.getChildren()){

                            Log.d(TAG, "The Getting formed data" + postsnapsht.getValue());
                            Map<String, String> map= postsnapsht.getValue(Map.class);


                            Job_class temp_object = new Job_class(map.get("Job Title"), map.get("Job Description"), map.get("Location"), map.get("Cost"), map.get("Time"), map.get("Job Status"), map.get("Uploader name"), map.get("Assignee"), map.get("Category"));


                            Log.d(TAG, "JOB TITLEH" + temp_object.getJob_title_());
                            temp.add(temp_object);

                        }
                        ListView lvProduct =(ListView) rootView.findViewById(R.id.listview_product);

                        List<Product> mProductList = new ArrayList<>();
                        for (int n=0;n<temp.size(); n++){
                            mProductList.add(new Product(temp.get(n).getCost(), "Bla", temp.get(n).getJob_title_(), temp.get(n).getJob_description_()));
                        }
                        ProductListAdapter adapter=new ProductListAdapter(getActivity().getApplicationContext(), mProductList);
                        lvProduct.setAdapter(adapter);

                        lvProduct.setOnItemClickListener(new AdapterView.OnItemClickListener(){
                            @Override
                            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                              //  Log.e("H", "THe var is :"+ (((TextView)view).getText().toString()));


                                    startActivity(new Intent(getActivity(), chore_info.class));


                            }
                        });


                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {
                        Log.v("E-Value", "Error from Import" + firebaseError);

                    }
                });






                return rootView;

            }
            else{
                View rootView = inflater.inflate(R.layout.fragment_main, container, false);
                return rootView;

            }

        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
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
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "Chores";
                case 1:
                    return "My Chores";
                case 2:
                    return "Profile";
            }
            return null;
        }
    }
}
