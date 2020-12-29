package com.example.onthick;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Build;
import android.os.Bundle;
import android.service.autofill.UserData;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText edit_username;
    EditText edit_adress;
    Button btn_add,btn_reset;
    RecyclerView recyclerView;
    UserAdapter adapter;
    UserDatabase database;
    List<User> mlist_user = new ArrayList<>();
    LinearLayoutManager linearLayout;

    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Anh Xa
        AnhXa();

        //Database
        database = UserDatabase.getInstance(this);



        mlist_user = database.userDao().getAllUser();


        linearLayout = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayout);
        adapter = new UserAdapter(mlist_user,this);
        recyclerView.setAdapter(adapter);

        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edit_username.getText().toString().trim();
                String useradress = edit_adress.getText().toString().trim();

                if(username.equals("")){
                    Toast.makeText(MainActivity.this, "name không rỗng", Toast.LENGTH_SHORT).show();
                }else if(useradress.equals("")){
                    Toast.makeText(MainActivity.this, "adress không rỗng", Toast.LENGTH_SHORT).show();
                }else  {
                    User u = new User();
                    u.setName_user(username);
                    u.setAdress_user(useradress);

                    database.userDao().InsertUser(u);
                    Toast.makeText(MainActivity.this, "them thanh cong", Toast.LENGTH_SHORT).show();
                    XoaRong();
                    mlist_user.clear();
                    mlist_user.addAll(database.userDao().getAllUser());
                    adapter.notifyDataSetChanged();
                }
            }
        });
        btn_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                XoaRong();

            }
        });
    }

    public void AnhXa(){
        edit_adress =findViewById(R.id.edit_adress);
        edit_username = findViewById(R.id.edit_username);
        btn_add = findViewById(R.id.btnAdd);
        btn_reset = findViewById(R.id.btnRemoveALL);
        recyclerView = findViewById(R.id.reyc_layout);
    }
    public void XoaRong(){
        edit_username.setText("");
        edit_adress.setText("");
    }

}