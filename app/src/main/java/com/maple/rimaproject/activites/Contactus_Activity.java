package com.maple.rimaproject.activites;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.maple.rimaproject.R;
import com.maple.rimaproject.Retrofit.GetContactUs;
import com.maple.rimaproject.Retrofit.GetType;
import com.maple.rimaproject.Retrofit.RetrofitClient;
import com.maple.rimaproject.Retrofit.contactusmodel;
import com.maple.rimaproject.SplashScreen;
import com.maple.rimaproject.model.TypeModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class Contactus_Activity extends AppCompatActivity {
TextView phoneNumber;
    TextView faceBook;
    TextView insegram;
    String phone;
    String facebookUrl;
    String instUrl;
    List <contactusmodel>allType=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus_);
        fillcontact();
    phoneNumber=findViewById(R.id.numberphone);
        faceBook=findViewById(R.id.facebook);
        insegram=findViewById(R.id.instegram);

        phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL);

                intent.setData(Uri.parse("tel:" + phone));
                startActivity(intent);
            }

        });

    faceBook.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToFacebook();
        }
    });
    insegram.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToinsta();
        }
    });
    }

    private void goToFacebook() {
        try {
            String facebookUrl = getFacebookPageURL();
            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
            facebookIntent.setData(Uri.parse(facebookUrl));
            startActivity(facebookIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getFacebookPageURL() {
        String FACEBOOK_URL = "http://www"+facebookUrl+".com";
        String facebookurl = null;

        try {
            PackageManager packageManager = getPackageManager();

            if (packageManager != null) {
                Intent activated = packageManager.getLaunchIntentForPackage("com.facebook.katana");

                if (activated != null) {
                    int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;

                    if (versionCode >= 3002850) {
                        facebookurl = "fb://page/1548219792xxxxxx";
                    }
                } else {
                    facebookurl = FACEBOOK_URL;
                }
            } else {
                facebookurl = FACEBOOK_URL;
            }
        } catch (Exception e) {
            facebookurl = FACEBOOK_URL;
        }
        return facebookurl;
    }
    private void goToinsta() {
        try {
            String facebookUrl = getFacebookPageURL();
            Intent facebookIntent = new Intent(Intent.ACTION_VIEW);
            facebookIntent.setData(Uri.parse(facebookUrl));
            startActivity(facebookIntent);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String getinsta() {
        String FACEBOOK_URL = "http://www"+instUrl+".com";
        String facebookurl = null;

        try {
            PackageManager packageManager = getPackageManager();

            if (packageManager != null) {
                Intent activated = packageManager.getLaunchIntentForPackage("com.facebook.katana");

                if (activated != null) {
                    int versionCode = packageManager.getPackageInfo("com.facebook.katana", 0).versionCode;

                    if (versionCode >= 3002850) {
                        facebookurl = "fb://page/1548219792xxxxxx";
                    }
                } else {
                    facebookurl = FACEBOOK_URL;
                }
            } else {
                facebookurl = FACEBOOK_URL;
            }
        } catch (Exception e) {
            facebookurl = FACEBOOK_URL;
        }
        return facebookurl;
    }



    public void fillcontact() {

        GetContactUs service = RetrofitClient.getClient("http://138.201.220.66/royalevcms/").create(GetContactUs.class);
        Call<List<contactusmodel>> call = service.getinfo();

        Log.e("URL Called", call.request().url() + "");

        call.enqueue(new Callback<List<contactusmodel>>() {
            @Override
            public void onResponse(Call<List<contactusmodel>> call, Response<List<contactusmodel>> response) {
//                Log.e("ResponseLogIn", "onResponse: " + response.body().get(0).getArea());
                allType = response.body();
                for (int i=0;i<allType.size();i++){
                phone=allType.get(i).getPhone();
                facebookUrl=allType.get(i).getFacebook();
                instUrl=allType.get(i).getInstagram();
                    Log.e("type", "type: "+allType.get(i).getFacebook());

                }


            }

            @Override
            public void onFailure(Call<List<contactusmodel>> call, Throwable t) {
//                Toast.makeText(MainActivity.this,, Toast.LENGTH_SHORT).show();
                Log.e("SizeError", t.getLocalizedMessage() + t.getStackTrace() + t.getCause());
                Toast.makeText(Contactus_Activity.this, "Nooooo", Toast.LENGTH_SHORT).show();
            }
        });




    }

}
