package com.ittalents.myfirstaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.ittalents.myfirstaplication.model.RegularUser;

import java.util.ArrayList;
import java.util.List;

public class ArchivedNoticeActivity extends AppCompatActivity {
    private Spinner adList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_notice);

        if (MainActivity.loggedRegularUser != null) {
            final RegularUser ru = (RegularUser) MainActivity.loggedRegularUser;

            if (!ru.poster.get(RegularUser.SortNotice.ARCHIVE).isEmpty()) {

                adList = (Spinner) findViewById(R.id.ad_list);

                List<String> spinnerArray = new ArrayList<String>();


                for (RegularUser.Notice n : ru.poster.get(RegularUser.SortNotice.ARCHIVE)) {
                    spinnerArray.add(n.getTitle());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                        this, android.R.layout.simple_spinner_item, spinnerArray);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                adList.setAdapter(adapter);

                adList.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String text = adList.getSelectedItem().toString();
                        int price = 0;
                        String gsm = null;

                        String title = null;
                        String description = null;
                        for (RegularUser.Notice n : ru.poster.get(RegularUser.SortNotice.ARCHIVE)) {
                            if (n.getTitle().equals(text)) {
                                price = n.getPrice();
                                gsm = n.getGsm();
                                title = n.getTitle();
                                description = n.getDescription();

                                break;
                            }
                        }

                        Intent i = new Intent(ArchivedNoticeActivity.this, AdActivity.class);

                        Bundle b = new Bundle();
                        b.putInt("PRICE", price);
                        b.putString("GSM", gsm);

                        b.putString("TITLE", title);
                        b.putString("DESCRIPTION", description);
                        i.putExtras(b);
                        startActivity(i);


                    }
                });
            } else{
                Toast.makeText(ArchivedNoticeActivity.this, "You have no archived ads yet.", Toast.LENGTH_SHORT).show();
            }

        }
    }
}

