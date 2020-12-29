package com.example.onthick;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class test extends AppCompatActivity {
    private TextView name,dc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        name = findViewById(R.id.id_test1);
        dc = findViewById(R.id.id_test2);

        User user = (User) getIntent().getExtras().get("object_user");

        name.setText(user.getName_user());
        dc.setText(user.getAdress_user());

    }
//    private void update(){
//        String uname = name.getText().toString().trim();
//        String udc = dc.getText().toString().trim();
//        User user = new
//    }
}