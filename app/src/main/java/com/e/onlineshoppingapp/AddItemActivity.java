package com.e.onlineshoppingapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.io.PrintStream;

public class AddItemActivity extends AppCompatActivity {
    private EditText etName, etPrice, etDescription, etImage;
    private Button btnAddItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);

        etName = findViewById(R.id.etName);
        etPrice= findViewById(R.id.etPrice);
        etDescription= findViewById(R.id.etDescription);
        etImage= findViewById(R.id.etImage);
        btnAddItem = findViewById(R.id.btnAddItem);

        btnAddItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (validation()){
                    saveItems();
                }
            }
        });
    }

    private void saveItems() {
        try {
            PrintStream printstream = new PrintStream(openFileOutput("items.txt",MODE_PRIVATE |MODE_APPEND));
            printstream.println(etName.getText().toString() +"->"+etPrice.getText().toString()+"->"+etImage.getText().toString()+"->"+etDescription.getText().toString());
            Toast.makeText(this, "Saved to "+getFilesDir(), Toast.LENGTH_LONG).show();
            printstream.close();
            Intent intent = new Intent(AddItemActivity.this, DashboardActivity.class);
            startActivity(intent);
        }
        catch (IOException e){

        }
    }

    public boolean validation(){
        boolean flag = true;
        if (TextUtils.isEmpty(etName.getText().toString())){
            etName.setError("Please Enter Item Name");
            etName.requestFocus();
            flag = false;
        }
        else if (TextUtils.isEmpty(etPrice.getText().toString())){
            etPrice.setError("Please Enter Price");
            etPrice.requestFocus();
            flag = false;
        }
        else if (TextUtils.isEmpty(etImage.getText().toString())){
            etImage.setError("Please Enter Image Name");
            etImage.requestFocus();
            flag = false;
        }
        else if (TextUtils.isEmpty(etDescription.getText().toString())){
            etDescription.setError("Please Enter Description");
            etDescription.requestFocus();
            flag = false;
        }

        return flag;
    }
}
