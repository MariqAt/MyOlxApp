package com.ittalents.myfirstaplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import com.ittalents.myfirstaplication.model.RegularUser;

import java.util.ArrayList;
import java.util.List;


public class NoticeActivity extends AppCompatActivity {

    private EditText title;
    private Spinner category;
    private Spinner type;
    private EditText price;
    private EditText description;
    private Spinner state;
    private ImageView picture;
    private Button add;

    private String adTitle;
    private RegularUser.Category adCategory;
    private RegularUser.Type adType;
    private int adPrice;
    private String adDescription;
    private RegularUser.StateGood adState;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notice);

        title = (EditText) findViewById(R.id.title);
        category = (Spinner) findViewById(R.id.category);
        type = (Spinner) findViewById(R.id.type);
        price = (EditText) findViewById(R.id.price);
        description = (EditText) findViewById(R.id.description);
        state = (Spinner) findViewById(R.id.state);
        picture = (ImageView) findViewById(R.id.view_picture1);

        add = (Button) findViewById(R.id.add);

        List<RegularUser.Category> spinnerArray1 = new ArrayList<>();
        spinnerArray1.add(RegularUser.Category.ESTATES);
        spinnerArray1.add(RegularUser.Category.ANIMALS);
        spinnerArray1.add(RegularUser.Category.FASHION);
        spinnerArray1.add(RegularUser.Category.ELECTRONICS);
        ArrayAdapter<RegularUser.Category> adapter1 = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerArray1);

        adapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        category.setAdapter(adapter1);

        category.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {


            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {

                    adCategory =  (RegularUser.Category) category.getSelectedItem();


            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }
        });

        List<RegularUser.Type> spinnerArray2 = new ArrayList<>();
        spinnerArray2.add(RegularUser.Type.PRIVATE);
        spinnerArray2.add(RegularUser.Type.BUSINESS);


        ArrayAdapter<RegularUser.Type> adapter2 = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerArray2);

        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        type.setAdapter(adapter2);

        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                adType = (RegularUser.Type) type.getSelectedItem();

            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }


        });

        List<RegularUser.StateGood> spinnerArray3 = new ArrayList<>();
        spinnerArray3.add(RegularUser.StateGood.NEW);
        spinnerArray3.add(RegularUser.StateGood.USED);


        ArrayAdapter<RegularUser.StateGood> adapter3 = new ArrayAdapter<>(
                this, android.R.layout.simple_spinner_item, spinnerArray3);

        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        state.setAdapter(adapter3);

        state.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                adState = (RegularUser.StateGood) state.getSelectedItem();
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }


        });

        add.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (isNumber(price.getText().toString()) && adCategory != null && adType != null && adState != null && !title.getText().toString().isEmpty() && price.getText() != null && !description.getText().toString().isEmpty()) {
                    adTitle = title.getText().toString();
                    adPrice = Integer.parseInt(price.getText().toString());
                    adDescription = description.getText().toString();

                    RegularUser.Notice n = null;



                         n = MainActivity.loggedRegularUser.new Notice(adTitle, adCategory, adType, adPrice, adDescription, adState);



                        MainActivity.loggedRegularUser.addNotice(n);

                    Intent intent = new Intent(NoticeActivity.this, MyHomeActivity.class);
                    Bundle bagaj = new Bundle();
                    bagaj.putSerializable("user", MainActivity.loggedRegularUser);
                    intent.putExtras(bagaj);
                    startActivity(intent);
                } else {
                    Toast.makeText(NoticeActivity.this, "Incorrect data", Toast.LENGTH_SHORT).show();
                }


            }
        });
    }

    public boolean isNumber(String s){
        boolean itIsNumber = true;
        String p = null;
        for (int idx = 0; idx <= s.length() - 1; idx++){
            if(s.charAt(idx) < '0' || s.charAt(idx) > '9'){
                itIsNumber = false;
               p = Integer.toString(idx);
                break;
            }
        }
        if (itIsNumber){
            Toast.makeText(NoticeActivity.this, price.getText().toString(), Toast.LENGTH_SHORT).show();
        }
        return itIsNumber;
    }
}