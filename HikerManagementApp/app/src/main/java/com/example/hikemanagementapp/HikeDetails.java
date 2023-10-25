package com.example.hikemanagementapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.TextView;
import android.os.Bundle;

public class HikeDetails extends AppCompatActivity {

    private TextView nameTV, locationTV, parkingTV, lengthTV, levelTV, descriptionTV, memberTV, typeTV, addObser;

    private String id;
    private RecyclerView obserRv;
    private AdapterObservation adapterObservation;
    private DbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hike_details);
        dbHelper = new DbHelper(this);

        obserRv = findViewById(R.id.obserRv);

        obserRv.setHasFixedSize(true);
        LinearLayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext());
        obserRv.setLayoutManager(mLayoutManager);

        Intent intent = getIntent();
        id = intent.getStringExtra("hikeID");

        nameTV = findViewById(R.id.nameTV);
        locationTV = findViewById(R.id.locationTV);
        parkingTV = findViewById(R.id.parkingTV);
        lengthTV = findViewById(R.id.lengthTV);
        levelTV = findViewById(R.id.levelTV);
        descriptionTV = findViewById(R.id.descriptionTV);
        memberTV = findViewById(R.id.memberTV);
        typeTV = findViewById(R.id.typeTV);

        addObser = findViewById(R.id.addObser);
        addObser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HikeDetails.this, AddObservation.class);
                intent.putExtra("hikeID", id);
                intent.putExtra("isAddObservation", false);
                startActivity(intent);
            }
        });

        loadDataById();
        loadObser();
    }

    private void loadObser() {
        adapterObservation = new AdapterObservation(this, dbHelper.getAllObserData(id));
        obserRv.setAdapter(adapterObservation);
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadObser();
    }

    private void loadDataById() {
        String selectQuery = "SELECT * FROM " + Constants.TABLE_NAME + " WHERE " + Constants.H_ID + " =\"" + id + "\"";

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                String name = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_NAME));
                String location = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_LOCATION));
                String parking = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_PARKING));
                String length = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_LENGTH));
                String level = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_LEVEL));
                String description = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_DESCRIPTION));
                String quantity = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_QUANTITY));
                String type = "" + cursor.getString(cursor.getColumnIndexOrThrow(Constants.H_TYPE));
                nameTV.setText(name);
                locationTV.setText(location);
                parkingTV.setText(parking);
                lengthTV.setText(length);
                levelTV.setText(level);
                descriptionTV.setText(description);
                memberTV.setText(quantity);
                typeTV.setText(type);
            } while (cursor.moveToNext());
        }
        db.close();
    }

}