package com.ittalents.myfirstaplication;

import android.app.ListActivity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ittalents.myfirstaplication.model.RegularUser;

import java.util.ArrayList;
import java.util.List;

public class ArchivedNoticeActivity extends ListActivity {
    int price;
    private ListView adList;
    String gsm;

    String title;
    String description;
    private RegularUser ru = MainActivity.loggedRegularUser;;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_active_notice);

        adList = (ListView) findViewById(android.R.id.list);

        if (MainActivity.loggedRegularUser != null) {

            if (!ru.poster.get(RegularUser.SortNotice.ARCHIVE).isEmpty()) {

                List<String> listViewArray = new ArrayList<>();


                for (RegularUser.Notice n : ru.poster.get(RegularUser.SortNotice.ARCHIVE)) {
                    listViewArray.add(n.getTitle());
                }

                ArrayAdapter<String> adapter = new ArrayAdapter<>(
                        this, android.R.layout.simple_spinner_item, listViewArray);

                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

                adList.setAdapter(adapter);


            } else{
                Toast.makeText(ArchivedNoticeActivity.this, "You have no active ads yet.", Toast.LENGTH_SHORT).show();
            }
        }
    }
    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        String text = adList.getItemAtPosition(position).toString();
        price = 0;
        gsm = null;

        title = null;
        description = null;
        RegularUser.Notice notice = null;
        for (RegularUser.Notice n : ru.poster.get(RegularUser.SortNotice.ARCHIVE)) {
            if (n.getTitle().equals(text)) {
                price = n.getPrice();
                gsm = n.getGsm();
                title = n.getTitle();
                description = n.getDescription();
                notice = n;
                break;
            }
        }


        Intent i = new Intent(ArchivedNoticeActivity.this, AdActivity.class);
        Bundle b = new Bundle();
        b.putSerializable("notice", notice);
        b.putInt("PRICE", price);
        b.putString("GSM", gsm);

        b.putString("TITLE", title);
        b.putString("DESCRIPTION", description);
        i.putExtras(b);
        startActivity(i);
    }

}

