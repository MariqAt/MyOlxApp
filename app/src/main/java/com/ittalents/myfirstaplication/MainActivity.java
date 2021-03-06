package com.ittalents.myfirstaplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.ittalents.myfirstaplication.model.OLX;
import com.ittalents.myfirstaplication.model.RegularUser;
import com.ittalents.myfirstaplication.model.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE_ACCOUNT = 1;
    private EditText email;
    private EditText password;
    private Button loginButton;
    private Button createAccountButton;

    public static ArrayList<RegularUser.Notice> allNotices = new ArrayList<>();

    public static RegularUser loggedRegularUser;
    public static User loggedUser;

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
                       Toast.makeText(MainActivity.this, "Your mail or your password aren't correct! Please, try again to create your account!", Toast.LENGTH_LONG).show();
                   } else {
                       Toast.makeText(MainActivity.this, "Login successfull!", Toast.LENGTH_SHORT).show();


                       Intent intent = new Intent(MainActivity.this, MyHomeActivity.class);
                       Bundle bagaj = new Bundle();
                       bagaj.putSerializable("user", loggedRegularUser);
                       intent.putExtras(bagaj);
                       startActivity(intent);
                   }
               }
           }
       });

        createAccountButton = (Button) this.findViewById(R.id.button_create_acount);

        createAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CreateAccountActivity.class);
                startActivityForResult(intent, REQUEST_CODE_ACCOUNT);
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
            if(resultCode == CreateAccountActivity.RESULT_CODE_CANCELED) {
                Toast.makeText(this, "Your registration isn't successfull!", Toast.LENGTH_SHORT).show();
            }
            if (resultCode == CreateAccountActivity.RESULT_CODE_SUCCESS) {
                if (data != null) {
                    email.setText(data.getExtras().getString("email"));
                    password.setText(data.getExtras().getString("pass"));
                    loggedRegularUser = (RegularUser) data.getExtras().getSerializable("user");
                }
            }
    }

    private boolean validDate() {
        boolean valid = true;
        String mail = email.getText().toString();
            if (mail.isEmpty()) {
                valid = false;
                email.setError("Please, enter you e-mail!");
            }
        String pass = password.getText().toString();
            if (pass.isEmpty()) {
                valid = false;
                password.setError("The password must be not empty. Please, enter your password!");
            }
        return valid;
    };


}
