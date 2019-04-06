package com.maple.rimaproject.activites;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.maple.rimaproject.R;


public class Contactus_Activity extends AppCompatActivity {
TextView phoneNumber;
    TextView faceBook;
    TextView insegram;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contactus_);

    phoneNumber=findViewById(R.id.numberphone);
        faceBook=findViewById(R.id.facebook);
        insegram=findViewById(R.id.instegram);

        phoneNumber.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL);

                intent.setData(Uri.parse("tel:" + "905551965555"));
                startActivity(intent);
            }

        });

    faceBook.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            goToFacebook();
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
        String FACEBOOK_URL = "https://www.facebook.com";
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

}
