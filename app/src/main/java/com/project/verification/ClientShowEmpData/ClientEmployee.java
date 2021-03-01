package com.project.verification.ClientShowEmpData;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;

import com.google.android.material.textfield.TextInputEditText;
import com.project.verification.R;

public class ClientEmployee extends AppCompatActivity {

    TextInputEditText inEdName,inEdAge,inEdSalary;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_employee);

        inEdName = findViewById(R.id.tvName);
        inEdAge = findViewById(R.id.tvAge);
        inEdSalary = findViewById(R.id.tvSalary);

        ActionBar ab = getSupportActionBar();

      //  String id = getIntent().getStringExtra("id");
        String name = getIntent().getStringExtra("name");
        String age = getIntent().getStringExtra("age");
        String salary = getIntent().getStringExtra("salary");

//        Log.d("ID",id);
//        ab.setTitle(name);

        inEdName.setText(name);
        inEdAge.setText(age);
        inEdSalary.setText(salary);
    }
}