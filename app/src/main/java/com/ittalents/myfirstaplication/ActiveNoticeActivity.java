package com.ittalents.myfirstaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.ittalents.myfirstaplication.model.Message;
import com.ittalents.myfirstaplication.model.RegularUser;
import com.ittalents.myfirstaplication.model.User;

import java.util.ArrayList;
import java.util.List;

public class ActiveNoticeActivity extends AppCompatActivity {
    private Spinner adList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_notice);

        adList = (Spinner) findViewById(R.id.ad_list);
        if (MainActivity.loggedUser != null) {
            final RegularUser ru = (RegularUser) MainActivity.loggedUser;

            if (!ru.poster.get(RegularUser.SortNotice.ACTIVE).isEmpty()) {

                List<String> spinnerArray = new ArrayList<>();


                for (RegularUser.Notice n : ru.poster.get(RegularUser.SortNotice.ACTIVE)) {
                    spinnerArray.add(n.getTitle());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
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
                        for (RegularUser.Notice n : ru.poster.get(RegularUser.SortNotice.ACTIVE)) {
                            if (n.getTitle().equals(text)) {
                                price = n.getPrice();
                                gsm = n.getGsm();
                                title = n.getTitle();
                                description = n.getDescription();

                                break;
                            }
                        }

                        Intent i = AdActivity.newIntent(ActiveNoticeActivity.this, price, gsm, title, description);
                        startActivity(i);


                    }
                });
            } else{
                Toast.makeText(ActiveNoticeActivity.this, "You have no active ads yet.", Toast.LENGTH_SHORT).show();
            }
        }



    }
}
