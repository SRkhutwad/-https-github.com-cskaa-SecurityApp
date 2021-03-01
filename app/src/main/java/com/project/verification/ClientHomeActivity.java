package com.project.verification;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.project.verification.securityHomeScreen.Model;
import com.project.verification.securityRegistration.myAdapter;

public class ClientHomeActivity extends AppCompatActivity {

    RecyclerView recview;

    Emp_Mstr_Adapter emp_mstr_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_home);

        recview = findViewById(R.id.recview);
        recview.setLayoutManager(new LinearLayoutManager(this));

        FirebaseRecyclerOptions<Emp_Model> options =
                new FirebaseRecyclerOptions.Builder<Emp_Model>()
                        .setQuery(FirebaseDatabase.getInstance().getReference().child("Employee_Master"), Emp_Model.class)
                        .build();

        emp_mstr_adapter = new Emp_Mstr_Adapter(getApplicationContext(), options);
        recview.setAdapter(emp_mstr_adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        emp_mstr_adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        emp_mstr_adapter.stopListening();
    }
}