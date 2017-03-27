package com.ittalents.myfirstaplication;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.ittalents.myfirstaplication.model.ImageArrayAdapter;
import com.ittalents.myfirstaplication.model.RegularUser;

import java.util.ArrayList;
import java.util.List;

import static android.R.layout;
import static com.ittalents.myfirstaplication.R.drawable.home;


public class NoticeActivity extends AppCompatActivity {

    private EditText title;
    private Spinner category;
    private Spinner type;
    private EditText price;
    private EditText description;
    private Spinner state;
    private Button addButton;
    private Button previewButton;

    private String adTitle;
    private RegularUser.Category adCategory;
    private RegularUser.Type adType;
    private int adPrice;
    private String adDescription;
    private RegularUser.StateGood adState;
    private int imageId;

    private Spinner imageSpinner;
    private static Integer[] imageIcon = { R.drawable.dress1, R.drawable.dress2,
    home, R.drawable.house, R.drawable.bird, R.drawable.rabbit, R.drawable.fish,
    R.drawable.suit, R.drawable.dog, R.drawable.tshirt};


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


        addButton = (Button) findViewById(R.id.add);
        previewButton = (Button) findViewById(R.id.notice_preview_button);

        imageSpinner = (Spinner) findViewById(R.id.spinner4);
        ImageArrayAdapter adapter4 = new ImageArrayAdapter(this, imageIcon);
        imageSpinner.setAdapter(adapter4);

        imageSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                imageId =  (int) imageSpinner.getSelectedItem();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        List<RegularUser.Category> spinnerArray1 = new ArrayList<>();
        spinnerArray1.add(RegularUser.Category.ESTATES);
        spinnerArray1.add(RegularUser.Category.ANIMALS);
        spinnerArray1.add(RegularUser.Category.FASHION);
        spinnerArray1.add(RegularUser.Category.ELECTRONICS);
        ArrayAdapter<RegularUser.Category> adapter1 = new ArrayAdapter<>(
                this, layout.simple_spinner_item, spinnerArray1);

        adapter1.setDropDownViewResource(layout.simple_spinner_dropdown_item);

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
                this, layout.simple_spinner_item, spinnerArray2);

        adapter2.setDropDownViewResource(layout.simple_spinner_dropdown_item);

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
                this, layout.simple_spinner_item, spinnerArray3);

        adapter3.setDropDownViewResource(layout.simple_spinner_dropdown_item);

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

        addButton.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View v) {
                if (dataValid()) {
                    adTitle = title.getText().toString();
                    adPrice = Integer.parseInt(price.getText().toString());
                    adDescription = description.getText().toString();

                    RegularUser.Notice n = null;

                    n = MainActivity.loggedRegularUser.new Notice(adTitle, adCategory, adType, adPrice, adDescription, adState, imageId);

                    MainActivity.loggedRegularUser.addNotice(n);

                        //????
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

        previewButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (dataValid()) {
                    Intent intent = new Intent(NoticeActivity.this, AdActivity.class);
                    RegularUser.Notice n = MainActivity.loggedRegularUser.new Notice(title.getText().toString(),
                            adCategory, adType, Integer.parseInt(price.getText().toString()),
                            description.getText().toString(), adState, imageId);
                    intent.putExtra("notice", n);
                    startActivity(intent);
                } else {
                    Toast.makeText(NoticeActivity.this, "Data are not valid!", Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    private boolean dataValid() {
        boolean isValid = true;
        String title1 = title.getText().toString();
        if (title1.isEmpty()) {
            isValid = false;
            title.requestFocus();
            title.setError("Title must be not empty!");
        }
        String price1 = price.getText().toString();
        if (price1.isEmpty()) {
            isValid = false;
            price.requestFocus();
            price.setError("Price must be not empty");
        }
        String descr = description.getText().toString();
        if (descr.isEmpty()) {
            isValid = false;
            description.requestFocus();
            description.setError("Description ,ust be not empty!");
        }
        if (adCategory == null) {
            isValid = false;
            Toast.makeText(this, "You have to choose category of notice", Toast.LENGTH_SHORT).show();
        }
        if (adType == null) {
            Toast.makeText(this, "You have to choose type of notice", Toast.LENGTH_SHORT).show();
        }
        if (adState == null) {
            Toast.makeText(this, "You have to choose state of notice", Toast.LENGTH_SHORT).show();
        }
        return isValid;
    }

}