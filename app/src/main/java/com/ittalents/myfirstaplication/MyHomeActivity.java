package com.ittalents.myfirstaplication;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.ittalents.myfirstaplication.model.RegularUser;

import java.net.URI;

public class MyHomeActivity extends AppCompatActivity {

    private EditText textName;
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
        textName = (EditText) this.findViewById(R.id.text_name);
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
                Intent intent = new Intent(MyHomeActivity.this, MessageActivity.class);
                MyHomeActivity.this.startActivity(intent);
            }
        });

        contactUsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("0899511111"));
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
}
