package com.example.hikemanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.ActionBar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AddHike extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private EditText nameEn, locationEn, dateEn, lengthEn, levelEn, descriptionEn, memberQuanEn;
    private Spinner parkingEn, typeEn;
    private String id, name, location, date, length, level, description, memberQuan, parking, type;

    private DbHelper dbHelper;
    private ActionBar actionBar;
    private FloatingActionButton fab;

    private Boolean isEditMode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_hiker);


        dbHelper = new DbHelper(this);

        actionBar = getSupportActionBar();

        actionBar.setHomeButtonEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        nameEn = findViewById(R.id.name);
        locationEn = findViewById(R.id.location);
        dateEn = findViewById(R.id.date);
        lengthEn = findViewById(R.id.length);
        levelEn = findViewById(R.id.level);
        descriptionEn = findViewById(R.id.description);
        memberQuanEn = findViewById(R.id.memberQuan);
        parkingEn = findViewById(R.id.parking);
        typeEn = findViewById(R.id.type);

        fab = findViewById(R.id.fab);

        Intent intent = getIntent();
        isEditMode = intent.getBooleanExtra("isEditMode", false);

        if (isEditMode) {
            actionBar.setTitle("Update Hike");

            id = intent.getStringExtra("ID");
            name = intent.getStringExtra("NAME");
            location = intent.getStringExtra("LOCATION");
            //parking=intent.getSelector().toString();
            date = intent.getStringExtra("DATE");
            length = intent.getStringExtra("LENGTH");
            level = intent.getStringExtra("LEVEL");
            description = intent.getStringExtra("DESCRIPTION");
            memberQuan = intent.getStringExtra("QUANTITY");

            nameEn.setText(name);
            locationEn.setText(location);
            parkingEn.getSelectedItem().toString();
            dateEn.setText(date);
            lengthEn.setText(length);
            levelEn.setText(level);
            descriptionEn.setText(description);
            memberQuanEn.setText(memberQuan);
            typeEn.getSelectedItem().toString();
        } else {
            actionBar.setTitle("Add hike");
        }

        Spinner dropdown = findViewById(R.id.parking);
        ArrayAdapter adapter = ArrayAdapter.createFromResource(this, R.array.options, R.layout.color_spinner_layout);
        adapter.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        dropdown.setAdapter(adapter);
        dropdown.setOnItemSelectedListener(this);

        Spinner dropdownType = findViewById(R.id.type);
        ArrayAdapter adapterType = ArrayAdapter.createFromResource(this, R.array.types, R.layout.color_spinner_layout);
        adapterType.setDropDownViewResource(R.layout.spinner_dropdown_layout);
        dropdownType.setAdapter(adapterType);
        dropdownType.setOnItemSelectedListener(this);


        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveData();
            }
        });
    }


    public void saveData() {
        name = nameEn.getText().toString();
        location = locationEn.getText().toString();
        date = dateEn.getText().toString();
        length = lengthEn.getText().toString();
        level = levelEn.getText().toString();
        description = descriptionEn.getText().toString();
        memberQuan = memberQuanEn.getText().toString();
        parking = parkingEn.getSelectedItem().toString();
        type = typeEn.getSelectedItem().toString();
        if (!name.isEmpty() && !location.isEmpty() && !date.isEmpty() && !length.isEmpty() && !memberQuan.isEmpty() && !"Parking available".equals(parking) && !"Type of hike".equals(type) && !level.isEmpty()) {
            if (isEditMode) {
                //save data
                dbHelper.updateHike("" + id, "" + name, "" + location, "" + parking, "" + date, "" + length, "" + level, "" + description, "" + memberQuan, "" + type);

                Toast.makeText(getApplicationContext(), "Updated " + id, Toast.LENGTH_SHORT).show();

            } else {
                long id = dbHelper.insertHike("" + name, "" + location, "" + parking, "" + date, "" + length, "" + level, "" + description, "" + memberQuan, "" + type);
                Toast.makeText(getApplicationContext(), "Add  successfully hike ID: " + id, Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getApplicationContext(), "Please enter all fields", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        if (!"Parking available".equals(parkingEn.getSelectedItem().toString())) {
            Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        }
        if (!"Type of hike".equals(typeEn.getSelectedItem().toString())) {
            Toast.makeText(this, adapterView.getSelectedItem().toString(), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}