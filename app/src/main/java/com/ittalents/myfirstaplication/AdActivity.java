package com.ittalents.myfirstaplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

public class AdActivity extends AppCompatActivity {

    private TextView priceView;
    private TextView gsm;

    private TextView titleView;
    private TextView descriptionView;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        priceView = (TextView) findViewById(R.id.ad_price);
        gsm = (TextView) findViewById(R.id.ad_contact_info);

        titleView = (TextView) findViewById(R.id.ad_title);
        descriptionView = (TextView) findViewById(R.id.ad_description);

        Bundle bd = getIntent().getExtras();

            priceView.setText(bd.getInt("PRICE"));
            gsm.setText(bd.getString("GSM"));

            titleView.setText(bd.getString("TITLE"));
            descriptionView.setText(bd.getString("DESCRIPTION"));



    }
}

