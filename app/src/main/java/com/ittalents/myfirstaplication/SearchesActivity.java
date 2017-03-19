package com.ittalents.myfirstaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.ittalents.myfirstaplication.model.RegularUser;

public class SearchesActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searches);
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
                Intent intent1 = new Intent(this, OLXActivity.class);
                this.startActivity(intent1);
                break;
            case R.id.searches:
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
