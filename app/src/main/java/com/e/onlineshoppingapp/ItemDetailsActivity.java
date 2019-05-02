package com.e.onlineshoppingapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;

public class ItemDetailsActivity extends AppCompatActivity {
    CircleImageView circleImg;
    TextView tvName, tvPrice, tvDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_details);

        circleImg = findViewById(R.id.imgProfile);
        tvName= findViewById(R.id.tvName);
        tvPrice= findViewById(R.id.tvPrice);
        tvDescription= findViewById(R.id.tvDescription);

        Bundle bundle = getIntent().getExtras();

        if (bundle != null){
            circleImg.setImageResource(bundle.getInt("image"));
            tvName.setText("Name: "+bundle.getString("name"));
            tvPrice.setText("Price: "+bundle.getString("price"));
            tvDescription.setText(bundle.getString("description"));
        }
    }
}
