package com.ittalents.myfirstaplication;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.widget.TextView;

public class AdActivity extends AppCompatActivity {
    private static final String PRICE =
            "com.ittalents.myfirstaplication.price";
    private static final String GSM =
            "com.ittalents.myfirstaplication.price";

    private static final String TITLE =
            "com.ittalents.myfirstaplication.price";
    private static final String DESCRIPTION =
            "com.ittalents.myfirstaplication.price";
    private TextView priceView;
    private TextView gsm;

    private TextView titleView;
    private TextView descriptionView;


    public static Intent newIntent(Context packageContext, int price, String gsm, String title, String description) {
        Intent i = new Intent(packageContext, AdActivity.class);

        i.putExtra(PRICE, price);
        i.putExtra(GSM, gsm);

        i.putExtra(TITLE, title);
        i.putExtra(DESCRIPTION, description);

        return i;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        priceView = (TextView) findViewById(R.id.price);
        gsm = (TextView) findViewById(R.id.contact_info);

        titleView = (TextView) findViewById(R.id.title);
        descriptionView = (TextView) findViewById(R.id.description);

        priceView.setText(getIntent().getIntExtra(PRICE, 0));
        gsm.setText(getIntent().getStringExtra(GSM));

        titleView.setText(getIntent().getStringExtra(TITLE));
        descriptionView.setText(getIntent().getStringExtra(DESCRIPTION));
    }
}

