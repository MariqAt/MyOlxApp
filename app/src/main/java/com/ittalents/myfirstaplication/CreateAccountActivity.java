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

public class CreateAccountActivity extends AppCompatActivity {

    public static final int RESULT_CODE_SUCCESS = 2;
    public static final int RESULT_CODE_CANCELED = 3;
    private EditText registName;
    private EditText registAddress;
    private EditText registEmail;
    private EditText registPassword;
    private EditText registGsm;
    private Button registButton;
    private Button cancelButton;

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
                if (isValidDate()) {
                    MainActivity.loggedRegularUser = RegularUser.createUser(registName.getText().toString(), registAddress.getText().toString(),
                            registEmail.getText().toString(), registPassword.getText().toString(), registGsm.getText().toString());
                    OLX.loggedRegularUsers.add((RegularUser) MainActivity.loggedUser);
                    Toast.makeText(CreateAccountActivity.this, "You are logged successfull", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent();
                    intent.putExtra("email", MainActivity.loggedRegularUser.getMail());
                    intent.putExtra("pass", MainActivity.loggedRegularUser.getPassword());
                    setResult(RESULT_CODE_SUCCESS, intent);
                    finish();
                } else {
                    Toast.makeText(CreateAccountActivity.this, "The entered data are not valid!", Toast.LENGTH_SHORT).show();
                }
            }
        });

        cancelButton = (Button) this.findViewById(R.id.button_cancle);


        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setResult(RESULT_CODE_CANCELED); //canceled
                finish();
            }
        });

}

    private boolean isValidDate() {
        boolean isValid = true;
        String name = registName.getText().toString();
        if (name.isEmpty()) {
            isValid = false;
            registName.setError("Username must be not empty!");
        }
        String address = registAddress.getText().toString();
        if (address.isEmpty()) {
            isValid = false;
            registAddress.setError("User address must be not empty!");
        }
        String email = registEmail.getText().toString();
        if (email.isEmpty()) {
            isValid = false;
            registEmail.setError("User mail must be not empty!");
        }
        String pass = registPassword.getText().toString();
        if (pass.isEmpty()) {
            isValid = false;
            registPassword.setError("The password must be not empty!");
        } else
           if (pass.length() < 8) {
               isValid = false;
               registPassword.setError("The password must be larger than 8 symbols!");
        } else
           if (!RegularUser.isValidPassword(pass)) {
               isValid = false;
               registPassword.setError("The password must contain at least one capital letter, one lowercase and one lucky number!");

           }

        String gsm = registGsm.getText().toString();
        if (gsm.isEmpty()) {
            isValid = false;
            registGsm.setError("GSM number must be not empty!");
        }
        return isValid;
    }
    }
