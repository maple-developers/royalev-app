package com.maple.rimaproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.util.LruCache;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.maple.rimaproject.Retrofit.Project;
import com.maple.rimaproject.activites.Contactus_Activity;
import com.maple.rimaproject.fragments.FavoritesFragment;
import com.maple.rimaproject.fragments.HomeFragment;
import com.maple.rimaproject.fragments.ProjectsFragment;
import com.maple.rimaproject.fragments.SearchFragment;

import java.util.ArrayList;
import java.util.List;


public class NavigationActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    public static BottomNavigationView bottomNavigation;



//    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
//            = new BottomNavigationView.OnNavigationItemSelectedListener() {
//
//        @Override
//        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    changeFragmentMethod(new HomeFragment(), "test");
////                    Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.navigation_dashboard:
//                    changeFragmentMethod(new SearchFragment(), "test");
//
////                    Toast.makeText(NavigationActivity.this, "navigation_dashboard", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.navigation_projects:
////                    Toast.makeText(HomeActivity.this, "navigation_notifications", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.navigation_favorites:
////                    startActivity(new Intent(HomeActivity.this,LoginActivity.class));
////                    Toast.makeText(HomeActivity.this, "navigation_notifications", Toast.LENGTH_SHORT).show();
//                    return true;
//                case R.id.navigation_chats:
////                    startActivity(new Intent(HomeActivity.this,LoginActivity.class));
////                    Toast.makeText(HomeActivity.this, "navigation_notifications", Toast.LENGTH_SHORT).show();
//                    return true;
//            }
//            return false;
//        }
//    };

    List<Project> projectsList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_navigation);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        bottomNavigation = (BottomNavigationView) findViewById(R.id.navigation);
//        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);

        changeFragmentMethod(new HomeFragment(), "test");
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
//                            changeFragmentMethod(new HomeFragment(), getString(R.string.home_title));
                            changeFragmentMethod(new HomeFragment(), "test");
                             //   Toast.makeText(NavigationActivity.this, "navigation_dashboard", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.navigation_dashboard:
                               changeFragmentMethod(new SearchFragment(), "test");

//                                Toast.makeText(NavigationActivity.this, "navigation_dashboard", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.navigation_projects:
                            changeFragmentMethod(new ProjectsFragment(), "project");
                              //  Toast.makeText(NavigationActivity.this, "navigation_dashboard", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.navigation_favorites:
//                                Toast.makeText(NavigationActivity.this, "navigation_dashboard", Toast.LENGTH_SHORT).show();
                                NavigationActivity.this.changeFragmentMethod(new FavoritesFragment(), "");
                                break;
                            case R.id.navigation_alerts:
                              //  Toast.makeText(NavigationActivity.this, "navigation_dashboard", Toast.LENGTH_SHORT).show();
//                                NavigationActivity.this.changeFragmentMethod(new SearchFragment(), "");
                                break;
                        }
                        return true;
                    }
                });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


//        CacheUtils<String, List<Datum>> cache = new LruCache<>();
//        projectsList = cache.get("projects");



////        for(int i=0; i < projectsList.size(); i++){
//            Log.e("khaliddddd", String.valueOf(projectsList.size()));
//
        LruCache<String,String> test = new LruCache<>(10);
        Toast.makeText(this,test.get("khalid") , Toast.LENGTH_SHORT).show();


//        List<Datum> cachedEntries = null;
//        try {
//            cachedEntries = (List<Datum>) CacheManager.readObject(this, "sesoo");
//            for (Datum entry : cachedEntries) {
//                Log.d("rtertert", entry.getArea());
//            }
//
//        } catch (IOException e) {
//            e.printStackTrace();
//        } catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        // Display the items from the list retrieved.



//        }
    }


    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.navigation, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }

        return super.onOptionsItemSelected(item);
    }

    public void changeFragmentMethod(Fragment targetFragment, String title) {
        if (targetFragment != null) {
            fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
//            ft.setCustomAnimations(R.anim.slide_in, R.anim.slide_out);
            ft.replace(R.id.main_fragment, targetFragment);
            ft.addToBackStack("backStack");
            ft.commit();
//            textViewTitle.setText(title);

        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_homee) {
            // Handle the camera action
            changeFragmentMethod(new HomeFragment(),"");
        } else if (id == R.id.nav_our_projects) {
            changeFragmentMethod(new ProjectsFragment(),"");
        } else if (id == R.id.nav_favorites_projects) {

        } else if (id == R.id.nav_chat) {
            startActivity(new Intent(this,LoginActivity.class));

        } else if (id == R.id.nav_favor) {
            NavigationActivity.this.changeFragmentMethod(new FavoritesFragment(),"");
        } else if (id == R.id.nav_contactus) {
            Intent i = new Intent(this, Contactus_Activity.class);
            startActivity(i);
        }
        else if (id == R.id.nav_my_account) {
            Intent i = new Intent(this, detailsActivity.class);
            startActivity(i);


        }
        else if (id == R.id.nav_login_out) {

        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}
