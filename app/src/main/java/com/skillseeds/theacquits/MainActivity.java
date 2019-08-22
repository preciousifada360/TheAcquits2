package com.skillseeds.theacquits;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import android.util.Log;
import android.view.View;

import androidx.core.view.GravityCompat;
import androidx.appcompat.app.ActionBarDrawerToggle;

import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.skillseeds.theacquits.Api.Api;
import com.skillseeds.theacquits.Models.trainingModel;
import com.skillseeds.theacquits.adaptors.trainingAdaptor;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.Menu;


import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,
        BottomNavigationView.OnNavigationItemSelectedListener {



  RecyclerView recyclerView;
//    trainingAdaptor adaptor;
//    List<trainingModel> trainingModelList;




//FloatingActionButton
    FloatingActionButton fab, fab_ic_mood_great, fab_ic_mood_good, fab_ic_mood_difficult, fab_ic_mood_awful, fab_ic_mood_meh;
    Animation fabOpen, fabClose, rotateForward, rotateBackward;
    boolean isOpen = false;




    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        //Navigation drawer
        setContentView(R.layout.activity_drawar_dashboard);

//        trainingModelList=new ArrayList<>();
//          recyclerView=(RecyclerView)findViewById(R.id.training_recyclerView);
//        recyclerView.setHasFixedSize(true);
//      recyclerView.setLayoutManager(new LinearLayoutManager(this));


        final ListView listView=(ListView) findViewById(R.id.list_view_training);
        //Build Retrofit API
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Api.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        //creating the api interface
        Api api = retrofit.create(Api.class);

        //now making the call object
        //Here we are using the api method that we created inside the api interface

        Call<List<trainingModel>> call = api.getTraining();
        call.enqueue(new Callback<List<trainingModel>>() {
                         @Override
                         public void onResponse(Call<List<trainingModel>> call, Response<List<trainingModel>> response) {
                             List<trainingModel> trainingModelList =response.body();
                         String[] traininglist = new String[trainingModelList.size()];
                         for (int i=0; i< trainingModelList.size(); i++){
                             traininglist[i] = trainingModelList.get(i).getTitle();
                         }


                         listView.setAdapter(
                                 new ArrayAdapter<String>(
                                         getApplicationContext(),
                                         android.R.layout.simple_list_item_1,
                                         traininglist

                                 )
                         );
                         }

                         @Override
                         public void onFailure(Call<List<trainingModel>> call, Throwable t) {

                         }
                     });


                //Toolbar
                Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        //BottomNavigationView
        BottomNavigationView bottomNavigationView= findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);


        //calling FloatingActionButton
        fab =  (FloatingActionButton)findViewById(R.id.fab);
        fab_ic_mood_great =  (FloatingActionButton)findViewById(R.id.fab_ic_mood_great);
        fab_ic_mood_good =  (FloatingActionButton)findViewById(R.id.fab_ic_mood_good);
        fab_ic_mood_difficult =  (FloatingActionButton)findViewById(R.id.fab_ic_mood_difficult);
        fab_ic_mood_awful =  (FloatingActionButton)findViewById(R.id.fab_ic_mood_awful);
        fab_ic_mood_meh =  (FloatingActionButton)findViewById(R.id.fab_ic_mood_meh);

        //calling animation for FloatingActionButton
        fabOpen = AnimationUtils.loadAnimation(this,R.anim.fab_open);
        fabClose = AnimationUtils.loadAnimation(this,R.anim.fab_close);

        //calling rotation animation for FloatingActionButton
        rotateForward = AnimationUtils.loadAnimation(this,R.anim.rotate_forward);
        rotateBackward = AnimationUtils.loadAnimation(this,R.anim.rotate_backward);



        //calling click listeners for main fab character FloatingActionButton
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            animateFab();

            }
        });
        //calling click listeners for fab_ic_mood_great character FloatingActionButton
        fab_ic_mood_great.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();

                Toast.makeText(MainActivity.this, "I am Feeling Great", Toast.LENGTH_SHORT).show();
            }
        });

        //calling click listeners for fab_ic_mood_good character FloatingActionButton
        fab_ic_mood_good.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();

                Toast.makeText(MainActivity.this, "I am in a Good Mood", Toast.LENGTH_SHORT).show();
            }
        });


        //calling click listeners for fab_ic_mood_difficult character FloatingActionButton
        fab_ic_mood_difficult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();

                Toast.makeText(MainActivity.this, "Thing's are kind of difficult right now!", Toast.LENGTH_SHORT).show();
            }
        });

        //calling click listeners for fab_ic_mood_awful character FloatingActionButton
        fab_ic_mood_awful.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();

                Toast.makeText(MainActivity.this, "I Feel Awful", Toast.LENGTH_SHORT).show();
            }
        });

        //calling click listeners for fab_ic_mood_meh character FloatingActionButton
        fab_ic_mood_meh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                animateFab();

                Toast.makeText(MainActivity.this, "Meeehhhh!", Toast.LENGTH_SHORT).show();
            }
        });





        //calling layout for DrawerLayout character from drawer_layout xml
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        //calling NavigationView for nav_view
        NavigationView navigationView = findViewById(R.id.nav_view);
        //calling ActionBarDrawerToggle
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        //setFragment(new dataFragment());
            setTitle("Home");
            home fragment = new home();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Home");
            fragmentTransaction.commit();

    }




    //Method for all the fab characters
    private void
    animateFab(){
        if(isOpen)
        {
            fab.startAnimation(rotateBackward);
            fab_ic_mood_meh.startAnimation(fabClose);
            fab_ic_mood_awful.startAnimation(fabClose);
            fab_ic_mood_difficult.startAnimation(fabClose);
            fab_ic_mood_good.startAnimation(fabClose);
            fab_ic_mood_great.startAnimation(fabClose);
            fab_ic_mood_meh.setClickable(false);
            fab_ic_mood_awful.setClickable(false);
            fab_ic_mood_difficult.setClickable(false);
            fab_ic_mood_good.setClickable(false);
            fab_ic_mood_great.setClickable(false);
            isOpen=false;

        }else
        {
            fab.startAnimation(rotateForward);
            fab_ic_mood_meh.startAnimation(fabOpen);
            fab_ic_mood_awful.startAnimation(fabOpen);
            fab_ic_mood_difficult.startAnimation(fabOpen);
            fab_ic_mood_good.startAnimation(fabOpen);
            fab_ic_mood_great.startAnimation(fabOpen);
            fab_ic_mood_meh.setClickable(true);
            fab_ic_mood_awful.setClickable(true);
            fab_ic_mood_difficult.setClickable(true);
            fab_ic_mood_good.setClickable(true);
            fab_ic_mood_great.setClickable(true);
            isOpen=true;
        }

    }



    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.drawar_dashboard, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();





        //noinspection SimplifiableIfStatement
       // if (id == R.id.action_settings) {
          //  return true;
        //}



        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_home) {
            setTitle("Home");
            home fragment = new home();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Home");
            fragmentTransaction.commit();
        } else if (id == R.id.nav_daily_tracker) {
            setTitle("Daily Tracker");
            daily_tracker fragment = new daily_tracker();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Daily Tracker");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_community) {
            setTitle("Community");
            community fragment = new community();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Community");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_training) {
            setTitle("Training");
            training fragment = new training();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Training");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_lifestyle) {
            setTitle("Lifestyle Metrics");
            life_metrics fragment = new life_metrics();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Lifestyle Metrics");
            fragmentTransaction.commit();

        } else if (id == R.id.nav_well_being) {
            setTitle("Well-being");
            well_being fragment = new well_being();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Well-being");
            fragmentTransaction.commit();

        }else if (id == R.id.nav_allies) {
            setTitle("Allies");
            allies fragment = new allies();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Allies");
            fragmentTransaction.commit();

        }else if (id == R.id.nav_freedom_plan) {
            setTitle("Freedom Plan");
            freedom_plan fragment = new freedom_plan();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Freedom Plan");
            fragmentTransaction.commit();

        }else if (id == R.id.nav_journal) {
            setTitle("Journal");
            journal fragment = new journal();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Journal");
            fragmentTransaction.commit();

        }else if (id == R.id.nav_coaching) {
            setTitle("Coaching");
            coaching fragment = new coaching();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Coaching");
            fragmentTransaction.commit();

        }else if (id == R.id.nav_support_groups) {
            setTitle("Support Groups");
            support_groups fragment = new support_groups();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Support Groups");
            fragmentTransaction.commit();

        }else if (id == R.id.nav_webinars) {
            setTitle("Webinars");
            webinars fragment = new webinars();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Webinars");
            fragmentTransaction.commit();

        }else if (id == R.id.nav_awards) {
            setTitle("Awards");
            awards fragment = new awards();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Awards");
            fragmentTransaction.commit();

        }else if (id == R.id.nav_resources) {
            setTitle("Resources");
            resources fragment = new resources();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Resources");
            fragmentTransaction.commit();

        }else if (id == R.id.nav_well_being) {
            setTitle("Well-Being Metrics");
            well_being fragment = new well_being();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Well-Being Metrics");
            fragmentTransaction.commit();

        }else if (id == R.id.nav_settings) {
            setTitle("Settings");
            settings fragment = new settings();
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            fragmentTransaction.replace(R.id.fram, fragment, "Settings");
            fragmentTransaction.commit();

        }else if (id == R.id.nav_customer_support) {
            setTitle("Customer Support");
           customer_support fragment = new customer_support();
           FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
           fragmentTransaction.replace(R.id.fram, fragment, "Customer Support");
            fragmentTransaction.commit();
            //setFragment(new dataFragment());

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


  /* public void setFragment (Fragment fragment){
        if(fragment!=null){
            FragmentTransaction ft= getSupportFragmentManager().beginTransaction();
            ft.replace(R.id.fram, fragment);
            ft.commit();
        }
        DrawerLayout drawer=(DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
    }*/


}
