package com.ittalents.myfirstaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
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
    public void showPopup(View v) {
        final PopupMenu popup = new PopupMenu(this, v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.menu, popup.getMenu());
        popup.show();
    }

    public void showMenu(View v) {
        final PopupMenu popup = new PopupMenu(this, v);
        popup.setOnMenuItemClickListener((PopupMenu.OnMenuItemClickListener) this);
        popup.inflate(R.menu.menu);
        popup.show();
    }

    public void showPopup (MenuItem item) {
        switch (item.getItemId()) {
            case R.id.pageOlx:
                break;
            case R.id.searches:
                Intent intent2 = new Intent(this, SearchesActivity.class);
                this.startActivity(intent2);
                break;
            case R.id.add_notices:
                Intent intent3 = new Intent(this, AdActivity.class);
                this.startActivity(intent3);
                break;
            case R.id.messages:
                Intent intent4 = new Intent(this, NoticeActivity.class);
                this.startActivity(intent4);
                break;
            case R.id.my_home:
                Intent intent5 = new Intent(this, MyHomeActivity.class);
                startActivity(intent5);
                break;
            case R.id.exit:
                RegularUser user = (RegularUser) MainActivity.loggedUser;
                user.logOutOlx();
                Intent intent6 = new Intent(this, MainActivity.class);
                this.startActivity(intent6);
                break;
        }
    }

}
