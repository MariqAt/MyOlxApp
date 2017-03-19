package com.ittalents.myfirstaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.ittalents.myfirstaplication.model.Admin;
import com.ittalents.myfirstaplication.model.Message;
import com.ittalents.myfirstaplication.model.OLX;
import com.ittalents.myfirstaplication.model.RegularUser;
import com.ittalents.myfirstaplication.model.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;


public class AdminControlPanelActivity extends AppCompatActivity {

    private Spinner approvePendingAds;
    private Spinner deleteAd;
    private Spinner archiveAd;

    private Button reviewMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_control_panel);

        approvePendingAds = (Spinner) findViewById(R.id.approve_pending_ads);
        deleteAd = (Spinner) findViewById(R.id.delete_ad);
        archiveAd = (Spinner) findViewById(R.id.archive_ad);
        reviewMessages = (Button) findViewById(R.id.review_messages);

        List<String> spinnerArray1 = new ArrayList<String>();
        final Admin admin = (Admin) MainActivity.loggedUser;
        for (RegularUser.Notice notice : admin.pendingAds) {
            spinnerArray1.add(notice.getTitle());
        }

        ArrayAdapter<String> adapter1 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray1);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        approvePendingAds.setAdapter(adapter1);

        approvePendingAds.setOnClickListener(new View.OnClickListener() {

            String text = approvePendingAds.getSelectedItem().toString();

            @Override
            public void onClick(View v) {
                for (RegularUser.Notice notice : admin.pendingAds) {
                    if (notice.getTitle().equals(text)) {
                        admin.approveAd(notice);
                        break;
                    }
                }
            }
        });

        List<String> spinnerArray2 = new ArrayList<String>();

        for (Map.Entry<RegularUser.Category, ArrayList<RegularUser.Notice>> e : OLX.ads.entrySet()) {
            for (RegularUser.Notice notice : e.getValue()) {
                spinnerArray2.add(notice.getTitle());
            }
        }

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, spinnerArray2);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        deleteAd.setAdapter(adapter2);

        deleteAd.setOnClickListener(new View.OnClickListener() {
            String text = deleteAd.getSelectedItem().toString();

            @Override
            public void onClick(View v) {
                for (Map.Entry<RegularUser.Category, ArrayList<RegularUser.Notice>> e : OLX.ads.entrySet()) {
                    for (RegularUser.Notice notice : e.getValue()) {
                        if (notice.getTitle().equals(text)) {
                            admin.deleteAd(notice);
                            break;
                        }
                    }
                }
            }
        });

        archiveAd.setAdapter(adapter2);

        archiveAd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = deleteAd.getSelectedItem().toString();
                for (Map.Entry<RegularUser.Category, ArrayList<RegularUser.Notice>> e : OLX.ads.entrySet()) {
                    for (RegularUser.Notice notice : e.getValue()) {
                        if (notice.getTitle().equals(text)) {
                            admin.archiveAd(notice);
                            break;
                        }
                    }
                }
            }
        });

        reviewMessages.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(AdminControlPanelActivity.this, MessageActivity.class);
                startActivity(i);
            }
        });

    }
}
