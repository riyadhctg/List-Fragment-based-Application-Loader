package com.commongivinglabs.terriblydumblauncherpro;

import android.app.WallpaperManager;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.provider.Telephony;
import android.support.annotation.RequiresApi;
import android.support.v4.app.FragmentActivity;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends FragmentActivity {
    Intent selectedAppIntent;
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Window window = getWindow();
        window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        int uiOptions = View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_IMMERSIVE;
        window.getDecorView().setSystemUiVisibility(uiOptions);



        //getting system wallpaper as launcher background
        try {
            final WallpaperManager wallpaperManager = WallpaperManager.getInstance(this);
            final Drawable wallpaperDrawable = wallpaperManager.getDrawable();

            LinearLayout ll = (LinearLayout) findViewById(R.id.main_layout);//Substitute with your layout
            ll.setBackground(wallpaperDrawable);
        } catch (Exception e) {
            e.printStackTrace();
        }


        //this is menu button that loads the list of app.. basically loads homescreen... which loads AppListFragment
            ImageButton menuBtn = (ImageButton) findViewById(R.id.menu_btn);
            menuBtn.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    
                    LinearLayout layone= (LinearLayout) findViewById(R.id.map_layout);// change id here
                    layone.setVisibility(View.VISIBLE);
                }
            });


        Button closeFrag = (Button) findViewById(R.id.close_fragment);
        closeFrag.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {

                LinearLayout layone= (LinearLayout) findViewById(R.id.map_layout);// change id here
                layone.setVisibility(View.GONE);
            }
        });

            ImageButton addButton = (ImageButton) findViewById(R.id.add_button);
            addButton.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    
                    LinearLayout layone= (LinearLayout) findViewById(R.id.map_layout);// change id here
                    layone.setVisibility(View.VISIBLE);
                }
            });

         final ImageButton dialerBtn = (ImageButton) findViewById(R.id.dialer);

         dialerBtn.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {
                    Intent i = new Intent(Intent.ACTION_DIAL);
                    if(i != null) {
                        i.addCategory(Intent.CATEGORY_DEFAULT);
                        startActivity(i);
                    }
                    else {

                     //code from this block is intentionally deleted by the developed to prevent duplication
					 
                    }
                }
            });





        ImageButton smsBtn = (ImageButton) findViewById(R.id.sms);
        smsBtn.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_MAIN);
                try {
                    intent.addCategory(Intent.CATEGORY_DEFAULT);
                    intent.setType("vnd.android-dir/mms-sms");
                    startActivity(intent);
                } catch (ActivityNotFoundException e) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) // At least KitKat
                    {
                        //code from this block is intentionally deleted by the developed to prevent duplication
                    }
                    else
                    {
                        Toast.makeText(getApplicationContext(), "Default Messaging App Not Found ", Toast.LENGTH_SHORT).show();
                    }
                }



            }




        });

            ImageButton customBtn = (ImageButton) findViewById(R.id.custom_btn);
            customBtn.setOnClickListener(new View.OnClickListener() {

                public void onClick(View view) {

                    if(selectedAppIntent!=null)
                        startActivity(selectedAppIntent);

                    }

            });

    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.home_screen, menu);
        return true;
    }
}

