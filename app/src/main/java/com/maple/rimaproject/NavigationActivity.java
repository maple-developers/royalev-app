package com.maple.rimaproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.maple.rimaproject.fragments.HomeFragment;
import com.maple.rimaproject.fragments.SearchFragment;
import com.maple.rimaproject.util.BottomNavigationViewHelper;

public class NavigationActivity extends AppCompatActivity implements View.OnClickListener, NavigationView.OnNavigationItemSelectedListener {

    private FragmentManager fragmentManager;
    public static BottomNavigationView bottomNavigation;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    Toast.makeText(HomeActivity.this, "Home", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_dashboard:
                    changeFragmentMethod(new SearchFragment(), "test");

                    Toast.makeText(NavigationActivity.this, "navigation_dashboard", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_notifications:
//                    Toast.makeText(HomeActivity.this, "navigation_notifications", Toast.LENGTH_SHORT).show();
                    return true;
                case R.id.navigation_chat:
//                    startActivity(new Intent(HomeActivity.this,LoginActivity.class));

//                    Toast.makeText(HomeActivity.this, "navigation_notifications", Toast.LENGTH_SHORT).show();
                    return true;
            }
            return false;
        }
    };


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
        BottomNavigationViewHelper.disableShiftMode(bottomNavigation);

        changeFragmentMethod(new HomeFragment(), "test");
        bottomNavigation.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_home:
//                            changeFragmentMethod(new HomeFragment(), getString(R.string.home_title));
                            changeFragmentMethod(new HomeFragment(), "test");
                                Toast.makeText(NavigationActivity.this, "navigation_dashboard", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.navigation_dashboard:
                               changeFragmentMethod(new SearchFragment(), "test");

                                Toast.makeText(NavigationActivity.this, "navigation_dashboard", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.navigation_notifications:
//                            changeFragmentMethod(new FavoriteFragment(), getString(R.string.favorite));
                                Toast.makeText(NavigationActivity.this, "navigation_dashboard", Toast.LENGTH_SHORT).show();
                                break;
                            case R.id.navigation_chat:
                                Toast.makeText(NavigationActivity.this, "navigation_dashboard", Toast.LENGTH_SHORT).show();
                                NavigationActivity.this.changeFragmentMethod(new SearchFragment(), "");
                                break;
                        }
                        return true;
                    }
                });


        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
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
        getMenuInflater().inflate(R.menu.navigation, menu);
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

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

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