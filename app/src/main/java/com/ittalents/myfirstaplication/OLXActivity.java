package com.ittalents.myfirstaplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;

import com.ittalents.myfirstaplication.model.OLX;
import com.ittalents.myfirstaplication.model.RegularUser;

import java.util.ArrayList;

public class OLXActivity extends AppCompatActivity {

    private Button notice1;
    private ImageButton star1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olx);


        for (ArrayList<RegularUser.Notice> notice : OLX.ads.values()) {
           if (notice != null) {

           }
        }
    }
}
