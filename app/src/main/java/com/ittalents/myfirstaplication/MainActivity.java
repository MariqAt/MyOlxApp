package com.ittalents.myfirstaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ittalents.myfirstaplication.model.OLX;
import com.ittalents.myfirstaplication.model.RegularUser;
import com.ittalents.myfirstaplication.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText email;
    private EditText password;
    private Button loginButton;
    private Button createAccountButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        email = (EditText) this.findViewById(R.id.email_text);
        password = (EditText) this.findViewById(R.id.password_text);

        loginButton = (Button) this.findViewById(R.id.button_log_in);

       loginButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (validDate()) {
                   if (!RegularUser.logInOlx(email.getText().toString(), password.getText().toString())) {
                       Toast.makeText(MainActivity.this, "Your mail or your password aren't correct! Please, try again!", Toast.LENGTH_LONG).show();
                   } else {
                       Toast.makeText(MainActivity.this, "You are logged", Toast.LENGTH_SHORT).show();
                       //Intent intent = new Intent(MainActivity.this, MyHomeActivity.class);
                       //MainActivity.this.startActivity(intent);
                   }
               } else {
                   Toast.makeText(MainActivity.this, "Your mail or your password aren't correct! Please, try again!", Toast.LENGTH_LONG).show();
               }
           }
       });

        createAccountButton = (Button) this.findViewById(R.id.button_create_acount);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                MainActivity.this.startActivity(intent);
            }
        });
    }

    private boolean validDate() {
        boolean valid = true;
        String mail = email.getText().toString();
            if (mail.isEmpty()) {
                valid = false;
                loginButton.setError("Please, enter you e-mail.!");
            }
        String pass = password.getText().toString();
            if (pass.isEmpty()) {
                valid = false;
                loginButton.setError("The password must be not empty! Please, enter your password!");
            }
        return valid;
    };


}
