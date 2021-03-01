package com.project.verification.EmpPkg;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.project.verification.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllEmp extends AppCompatActivity {

    RecyclerView recView;
    RecyclerView.LayoutManager layoutManager;
    Api api;
    EmpAdapter adapter;
    List<EmpPojo> empPojo = new ArrayList<>();
    List<EmpData> empData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_all_emp);
        recView = findViewById(R.id.recView);
        layoutManager = new LinearLayoutManager(AllEmp.this);
        recView.setLayoutManager(layoutManager);
        recView.setHasFixedSize(true);

        empPojo.clear();

        getApiDetails();
    }

    private void getApiDetails() {

        api = Connection.getApiCon().create(Api.class);

        Call<EmpPojo> call = api.getEmployees();
        call.enqueue(new Callback<EmpPojo>() {
            @Override
            public void onResponse(Call<EmpPojo> call, Response<EmpPojo> response) {

                String status = response.body().getStatus();
                Log.d("STATUS",status);

                for(EmpData edata : response.body().getData()) {
                    EmpData ed = new EmpData(edata.getId(),edata.getEmployeeName(),edata.getEmployeeAge(), edata.getEmployeeSalary(),edata.getProfileImage());
                    empData.add(ed);
                }
                Log.d("LIST",""+empData);

                adapter = new EmpAdapter(getApplicationContext(), empData);
                recView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<EmpPojo> call, Throwable t) {
            }
        });
    }
}