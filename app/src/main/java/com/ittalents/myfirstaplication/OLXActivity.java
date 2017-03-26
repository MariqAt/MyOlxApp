package com.ittalents.myfirstaplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.HorizontalScrollView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.ittalents.myfirstaplication.model.RegularUser;

public class OLXActivity extends AppCompatActivity {

    private ImageView image1;
    private TextView title1;
    private TextView description1;
    private TextView price1;
    private Button viewButton1;
    private ImageButton star1;
    private HorizontalScrollView scView;

    private ImageView image2;
    private TextView title2;
    private TextView description2;
    private TextView price2;
    private Button viewButton2;
    private ImageButton star2;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_olx);

        image1 = (ImageView) findViewById(R.id.image_notice1);
        title1 = (TextView) findViewById(R.id.title_notice1);
        description1 = (TextView) findViewById(R.id.desc_notice1);
        price1 = (TextView) findViewById(R.id.price_notice1);
        viewButton1 = (Button) findViewById(R.id.view_button1);
        star1 = (ImageButton) findViewById(R.id.button_star1);

        image2 = (ImageView) findViewById(R.id.image_notice2);
        title2 = (TextView) findViewById(R.id.title_notice2);
        description2 = (TextView) findViewById(R.id.desc_notice2);
        price2 = (TextView) findViewById(R.id.price_notice2);
        viewButton2 = (Button) findViewById(R.id.view_button2);
        star2 = (ImageButton) findViewById(R.id.button_star2);

        if (MainActivity.allNotices.size() > 0 && MainActivity.allNotices.get(0) != null) {
            final RegularUser.Notice n = MainActivity.allNotices.get(0);
            image1.setImageResource(n.getPictureID());
            title1.setText(n.getTitle());
            description1.setText(n.getDescription());
            price1.setText(Integer.toString(n.getPrice()));

            viewButton1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OLXActivity.this, AdActivity.class);
                    intent.putExtra("notice", n);
                    startActivity(intent);
                }
            });

            star1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.loggedRegularUser.viewNotice(n);
                }
            });

        }

        if (MainActivity.allNotices.size() > 1 && MainActivity.allNotices.get(1) != null) {
            final RegularUser.Notice n = MainActivity.allNotices.get(1);
            image2.setImageResource(n.getPictureID());
            title2.setText(n.getTitle());
            description2.setText(n.getDescription());
            price2.setText(Integer.toString(n.getPrice()));

            viewButton2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(OLXActivity.this, AdActivity.class);
                    intent.putExtra("notice", n);
                    startActivity(intent);
                }
            });

            star2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    MainActivity.loggedRegularUser.viewNotice(n);
                }
            });
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
                Intent intent3 = new Intent(this, NoticeActivity.class);
                this.startActivity(intent3);
                break;
            case R.id.messages:
                Intent intent4 = new Intent(this, CorrespondenceActivity.class);
                this.startActivity(intent4);
                break;
            case R.id.my_home:
                Intent intent5 = new Intent(this, MyHomeActivity.class);
                startActivity(intent5);
                break;
            case R.id.exit:
                RegularUser user = (RegularUser) MainActivity.loggedRegularUser;
                user.logOutOlx();
                Intent intent6 = new Intent(this, MainActivity.class);
                this.startActivity(intent6);
                break;
        }
    }

}
