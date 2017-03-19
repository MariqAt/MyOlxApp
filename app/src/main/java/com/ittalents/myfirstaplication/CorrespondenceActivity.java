package com.ittalents.myfirstaplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.ittalents.myfirstaplication.model.Message;
import com.ittalents.myfirstaplication.model.User;
import com.ittalents.myfirstaplication.model.OLX;

import java.util.ArrayList;
import java.util.List;


public class CorrespondenceActivity extends AppCompatActivity {
    private Spinner messagedUsers;
    private TextView messageHistory;
    private TextView inputMessage;
    private TextView receiverName;
    private Button send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correspondence);

        messageHistory = (TextView) findViewById(R.id.message_history);
        messagedUsers = (Spinner) findViewById(R.id.messaged_users);
        inputMessage = (TextView) findViewById(R.id.input_message);
        receiverName = (TextView) findViewById(R.id.receiver_name);
        send = (Button) findViewById(R.id.button_send);

        List<String> spinnerArray = new ArrayList<String>();
        if(!MainActivity.loggedUser.messages.isEmpty()) {
            for (User user : MainActivity.loggedUser.messages.keySet()) {
                spinnerArray.add(user.getName());
            }

            ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                    this, android.R.layout.simple_spinner_item, spinnerArray);

            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

            messagedUsers.setAdapter(adapter);

            messagedUsers.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = messagedUsers.getSelectedItem().toString();
                    StringBuilder history = new StringBuilder("");
                    for (User user : MainActivity.loggedUser.messages.keySet()) {
                        if (user.getName().equals(text)) {
                            for (Message m : MainActivity.loggedUser.messages.get(user)) {
                                history.append(m.toString());
                            }
                        }
                    }

                    messageHistory.setText(history);
                }
            });

            send.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for (User user : MainActivity.loggedUser.messages.keySet()) {
                        if (user.getName().equals(receiverName.getText())) {
                            MainActivity.loggedUser.sendMessage(user, inputMessage.getText().toString());
                        }
                    }
                }
            });
        } else{
            Toast.makeText(CorrespondenceActivity.this, "You have no messages yet.", Toast.LENGTH_SHORT).show();
        }
    }
    }
