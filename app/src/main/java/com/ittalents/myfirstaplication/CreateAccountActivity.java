package com.ittalents.myfirstaplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreateAccountActivity extends AppCompatActivity {

    private OLX olx;
    private EditText registName;
    private EditText registAddress;
    private EditText registEmail;
    private EditText registPassword;
    private EditText registGsm;
    private Button registButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        registName = (EditText) this.findViewById(R.id.edit_name);
        registAddress = (EditText) this.findViewById(R.id.edit_address);
        registEmail = (EditText) this.findViewById(R.id.edit_email);
        registPassword = (EditText) this.findViewById(R.id.edit_pass);
        registGsm = (EditText) this.findViewById(R.id.edit_gsm);

        registButton = (Button) this.findViewById(R.id.button_registration);

        registButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isValiDate()) {
                    OLX.User.RegularUser user = OLX.User.RegularUser.createUser(registName.getText().toString(),
                        registEmail.getText().toString(),registGsm.getText().toString(), olx);
                    Intent intent = new Intent(CreateAccountActivity.this, MyHomeActivity.class);
                    Bundle luggage = new Bundle();
                    luggage.putString("registName", registName.getText().toString());
                } else {
                    Toast.makeText(CreateAccountActivity.this, "The entered data are not valid!", Toast.LENGTH_SHORT).show();
                }
            }
        });
}

    private boolean isValiDate() {
        boolean valid = true;
        String name = registName.getText().toString();
        if (name.isEmpty()) {
            valid = false;
            registName.setError("Username must be not empty!");
        }
        String address = registAddress.getText().toString();
        if (address.isEmpty()) {
            valid = false;
            registAddress.setError("User address must be not empty!");
        }
        String email = registEmail.getText().toString();
        if (email.isEmpty()) {
            valid = false;
            registEmail.setError("User mail must be not empty!");
        }
        String pass = registPassword.getText().toString();
        if (pass.isEmpty()) {
            valid = false;
            registPassword.setError("The password must be not empty!");
        }
        String gsm = registGsm.getText().toString();
        if (gsm.isEmpty()) {
            valid = false;
            registGsm.setError("GSM number must be not empty!");
        }
        return valid;
    }
    }
