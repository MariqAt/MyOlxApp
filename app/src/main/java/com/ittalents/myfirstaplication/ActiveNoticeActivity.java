package com.ittalents.myfirstaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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

    int price;

    String gsm;

    String title;
    String description;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_notice);

        adList = (Spinner) findViewById(R.id.ad_list);
        if (MainActivity.loggedRegularUser != null) {
            final RegularUser ru = (RegularUser) MainActivity.loggedRegularUser;

            if (!ru.poster.get(RegularUser.SortNotice.ACTIVE).isEmpty()) {

                List<String> spinnerArray = new ArrayList<>();


                for (RegularUser.Notice n : ru.poster.get(RegularUser.SortNotice.ACTIVE)) {
                    spinnerArray.add(n.getTitle());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        this, android.R.layout.simple_spinner_item, spinnerArray);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                adList.setAdapter(adapter);

                adList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

                    @Override
                    public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                        String text = adList.getSelectedItem().toString();
                        price = 0;
                        gsm = null;

                        title = null;
                        description = null;
                        RegularUser.Notice notice = null;
                        for (RegularUser.Notice n : ru.poster.get(RegularUser.SortNotice.ACTIVE)) {
                            if (n.getTitle().equals(text)) {
                                price = n.getPrice();
                                gsm = n.getGsm();
                                title = n.getTitle();
                                description = n.getDescription();
                                notice = n;
                                break;
                            }
                        }


                        Intent i = new Intent(ActiveNoticeActivity.this, AdActivity.class);
                        Bundle b = new Bundle();
                        b.putSerializable("notice", notice);
                        b.putInt("PRICE", price);
                        b.putString("GSM", gsm);

                        b.putString("TITLE", title);
                        b.putString("DESCRIPTION", description);
                        i.putExtras(b);
                        startActivity(i);



                    }
                    @Override
                    public void onNothingSelected(AdapterView<?> parentView) {
                        // your code here
                    }
                });
            } else{
                Toast.makeText(ActiveNoticeActivity.this, "You have no active ads yet.", Toast.LENGTH_SHORT).show();
            }
        }



    }
}
