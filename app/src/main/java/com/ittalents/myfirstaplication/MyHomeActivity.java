package com.ittalents.myfirstaplication;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.PopupMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ittalents.myfirstaplication.model.RegularUser;

public class MyHomeActivity extends AppCompatActivity {

    private TextView textName;
    private Button addNoticeButton;
    private Button activeNoticeButton;
    private Button archiveNoticeButton;
    private Button allMessagesButton;
    private Button contactUsButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_home);


        RegularUser u = (RegularUser) getIntent().getExtras().getSerializable("user");
        textName = (TextView) this.findViewById(R.id.text_name);

        textName.setText(u.getName().toString());

        addNoticeButton = (Button) this.findViewById(R.id.add_notice_button);
        activeNoticeButton = (Button) this.findViewById(R.id.active_notice_button);
        archiveNoticeButton = (Button) this.findViewById(R.id.archive_notice_button);
        allMessagesButton = (Button) this.findViewById(R.id.all_messages_button);
        contactUsButton = (Button) this.findViewById(R.id.contact_us_button);

        addNoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyHomeActivity.this, NoticeActivity.class);
                MyHomeActivity.this.startActivity(intent);

            }
        });

        activeNoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyHomeActivity.this, ActiveNoticeActivity.class);
                MyHomeActivity.this.startActivity(intent);
            }
        });

        archiveNoticeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyHomeActivity.this, ArchivedNoticeActivity.class);
                MyHomeActivity.this.startActivity(intent);
            }
        });

        allMessagesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyHomeActivity.this, CorrespondenceActivity.class);
                MyHomeActivity.this.startActivity(intent);
            }
        });

        contactUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_CALL, Uri.parse("tel: 0887888888"));
                if (ActivityCompat.checkSelfPermission(MyHomeActivity.this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                    // TODO: Consider calling
                    //    ActivityCompat#requestPermissions
                    // here to request the missing permissions, and then overriding
                    //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                    //                                          int[] grantResults)
                    // to handle the case where the user grants the permission. See the documentation
                    // for ActivityCompat#requestPermissions for more details.
                    return;
                }
                MyHomeActivity.this.startActivity(intent);
            }
        });
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
                Intent intent = new Intent(MyHomeActivity.this, OLXActivity.class);
                this.startActivity(intent);
                break;
            case R.id.searches:
                Intent intent2 = new Intent(MyHomeActivity.this, SearchesActivity.class);
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
                break;
            case R.id.exit:
                RegularUser user = (RegularUser) MainActivity.loggedRegularUser;
                user.logOutOlx();
                Intent intent5 = new Intent(this, MainActivity.class);
                startActivity(intent5);
                break;
        }
    }

}
