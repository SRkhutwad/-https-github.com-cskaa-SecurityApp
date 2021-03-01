package com.project.verification.ClientShowEmpData;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;

import com.project.verification.EmpPkg.AllEmp;
import com.project.verification.EmpPkg.Api;
import com.project.verification.EmpPkg.Connection;
import com.project.verification.EmpPkg.EmpAdapter;
import com.project.verification.EmpPkg.EmpData;
import com.project.verification.EmpPkg.EmpPojo;
import com.project.verification.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AllEmpShowClient extends AppCompatActivity {

    RecyclerView recView;
    RecyclerView.LayoutManager layoutManager;
    Apii api;
    EmpAdapterr adapter;
    List<EmpPojoo> empPojo = new ArrayList<>();
    List<EmpDataa> empData = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_emp_show_client);

        recView = findViewById(R.id.recView);
        layoutManager = new LinearLayoutManager(AllEmpShowClient.this);
        recView.setLayoutManager(layoutManager);
        recView.setHasFixedSize(true);

        empPojo.clear();

        getApiDetails();
    }

    private void getApiDetails() {

        api = (Apii) Connection.getApiCon().create(Apii.class);

        Call<EmpPojoo> call = api.getEmployees();
        call.enqueue(new Callback<EmpPojoo>() {
            @Override
            public void onResponse(Call<EmpPojoo> call, Response<EmpPojoo> response) {

                String status = response.body().getStatus();
                Log.d("STATUS",status);

                for(EmpDataa edata : response.body().getData()) {
                    EmpDataa edd = new EmpDataa(edata.getId(),edata.getEmployeeName(),edata.getEmployeeAge(), edata.getEmployeeSalary(),edata.getProfileImage());

                    empData.add(edd);
                }

                Log.d("LIST",""+empData);

                adapter = new EmpAdapterr(getApplicationContext(), empData);
                recView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<EmpPojoo> call, Throwable t) {
            }
        });

    }
}