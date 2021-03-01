package com.project.verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.project.verification.EmpPkg.CriminalPojo;
import com.project.verification.EmpPkg.Employee;
import com.project.verification.Preferences.PreferenceData;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CriminalRecordStored extends AppCompatActivity
{

    private EditText title;//, description, author;
    private Button save;

    FirebaseDatabase database;
    DatabaseReference reff;
    Spinner spinner;
    int maxid = 0;
    TextView selected;

    String item = null;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_criminal_record_stored);

        String ct = PreferenceData.getCriminalRecordType(this);
        String ct2 = PreferenceData.getCriminalRecord(this);

        Toast.makeText(this, ct+"\n"+ct2, Toast.LENGTH_LONG).show();

        title = findViewById(R.id.title);
        save = findViewById(R.id.save);
        selected = findViewById(R.id.selected);


        spinner = findViewById(R.id.spinner);
        reff = database.getInstance().getReference().child("Post");

        Member member = new Member();

        List<String> categories = new ArrayList<>();
        categories.add(0, "Choose");
        categories.add("Criminal Record Found");
        categories.add("Criminal Record Not Found");
        ArrayAdapter<String> dataAdapter;
        dataAdapter = new ArrayAdapter(this, android.R.layout.simple_spinner_item, categories);

        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (adapterView.getItemAtPosition(i).equals("choose event")) {

                } else {
                    item = spinner.getSelectedItem().toString();
                    if(PreferenceData.getCriminalRecordType(getApplicationContext()) == null){
                        PreferenceData.setCriminalRecordType(getApplicationContext(),item);
                    }
//                    selected.setText(adapterView.getSelectedItem().toString());
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });


        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                    maxid = (int) dataSnapshot.getChildrenCount();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


        save = findViewById(R.id.save);

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(title.getText().toString().trim().isEmpty()) {
                    title.setError("Enter criminal record here");
                } else {
                    savePrefData();
                }
//                String spin = spinner.getSelectedItem().toString();
//                if (spinner != null) {
//
//                    member.setSpinner(spin);
//                    Toast.makeText(MainActivity.this, "Registration Successfull", Toast.LENGTH_LONG).show();
//                    //toast.show();

                reff.child(String.valueOf(maxid + 1)).setValue(member);

                HashMap<String, Object> map = new HashMap<>();
                map.put("title", title.getText().toString());

                map.put("spinner",spinner.getSelectedItem().toString());

                FirebaseDatabase.getInstance().getReference().child("Post").push()
                        .setValue(map)
                        .addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                Log.i("ugugug", "onComplete");
                                Toast.makeText(CriminalRecordStored.this, "Data Uploaded Successfull!!!", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(CriminalRecordStored.this, Employee.class);
                                startActivity(intent);
                            }
                        }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.i("ugugug", "onComplete" + e.toString());
                    }
                });
                //}
//                sendUsingEventBus();
            }
        });
    }

    private void getBack() {

    }

//    private void sendUsingEventBus() {
//        CriminalPojo criminalPojo = new CriminalPojo();
//        criminalPojo.setCriminal_record_type(PreferenceData.getCriminalRecordType(this));
//        criminalPojo.setCriminal_record(PreferenceData.getCriminalRecord(this));
//        EventBus.getDefault().post(criminalPojo);
//        finish();
//    }

    private void savePrefData() {
        if(PreferenceData.getCriminalRecord(this).isEmpty()){
            PreferenceData.setCriminalRecord(this, title.getText().toString().trim());
        } else {
            PreferenceData.getCriminalRecord(this);
        }
    }
}
