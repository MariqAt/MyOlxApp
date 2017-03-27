package com.ittalents.myfirstaplication;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.ittalents.myfirstaplication.model.RegularUser;

public class AdActivity extends AppCompatActivity {

    private ImageView image;
    private TextView priceView;
    private TextView gsm;
    private TextView titleView;
    private TextView descriptionView;
    private TextView sellerNameView;
    private TextView emailView;
    private TextView dataView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad);

        image = (ImageView) findViewById(R.id.image_view);
        priceView = (TextView) findViewById(R.id.ad_price);
        gsm = (TextView) findViewById(R.id.ad_contact_info);
        titleView = (TextView) findViewById(R.id.ad_title);
        descriptionView = (TextView) findViewById(R.id.ad_description);
        sellerNameView = (TextView) findViewById(R.id.ad_seller_name);
        emailView = (TextView) findViewById(R.id.ad_mail);
        dataView = (TextView) findViewById(R.id.ad_data);


        RegularUser.Notice n = (RegularUser.Notice) getIntent().getExtras().getSerializable("notice");

        image.setImageResource(n.getPictureID());
        priceView.setText(n.getPrice() + " лв");
        gsm.setText(n.getGsm());
        titleView.setText(n.getTitle());
        descriptionView.setText(n.getDescription());
        sellerNameView.setText(n.getName());
        emailView.setText(n.getMail());
        dataView.setText(n.getDate().toString());
    }

}

