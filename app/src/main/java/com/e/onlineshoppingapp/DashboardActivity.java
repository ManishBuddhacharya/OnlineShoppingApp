package com.e.onlineshoppingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import adapter.ItemsAdapter;
import modal.Items;

public class DashboardActivity extends AppCompatActivity {
    private RecyclerView rvContact;
    private Button btnAddItem;
    List<Items> contactList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        rvContact = findViewById(R.id.rvContact);
        readFromFile();

        ItemsAdapter itemsAdapter = new ItemsAdapter(this, contactList);
        rvContact.setAdapter(itemsAdapter);
        rvContact.setLayoutManager(new GridLayoutManager(this, 2));

        btnAddItem = findViewById(R.id.btnAddItem);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DashboardActivity.this, AddItemActivity.class);
                startActivity(intent);
            }
        });



    }

    private void readFromFile(){
        try {
            FileInputStream fos = openFileInput("items.txt");
            InputStreamReader isr = new InputStreamReader(fos);
            BufferedReader br = new BufferedReader(isr);
            String line ="";

            while ((line = br.readLine()) != null){
                String[] parts = line.split("->");
                contactList.add(new Items(parts[0], parts[1], parts[2], this.getResources().getIdentifier(parts[3], "drawable", this.getPackageName())));
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
}
