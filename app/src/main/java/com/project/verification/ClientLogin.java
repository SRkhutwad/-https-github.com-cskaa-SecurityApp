package com.project.verification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class ClientLogin extends AppCompatActivity implements View.OnClickListener
{

    private EditText mail,pass;
    private Button button4;

    private FirebaseAuth mAuth;
    private ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client_login);

        mail = findViewById(R.id.mail);
        pass = findViewById(R.id.pass);

        button4 = findViewById(R.id.button4);
        button4.setOnClickListener(this);

        progressBar = findViewById(R.id.progressBar);

        mAuth = FirebaseAuth.getInstance();

    }

    public void createAcc(View view)
    {
        Intent intent=new Intent(getApplicationContext(),MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void onClick(View v)
    {
        switch (v.getId())
        {
            case R.id.button4:
                userLogin();
                break;
        }
    }

    private void userLogin()
    {
        final String email = mail.getText().toString().trim();
        String password = pass.getText().toString().trim();

        if (email.isEmpty())
        {
            mail.setError("Email is required");
            mail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            mail.setError("Please provide valid email!");
            mail.requestFocus();
            return;
        }

        if (password.isEmpty())
        {
            pass.setError("Password name is required");
            pass.requestFocus();
            return;
        }

        if (password.length()<6)
        {
            pass.setError("Min password lenght should be 6 char");
            pass.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful())
                {
                    //redirect to client home screen
                   startActivity(new Intent(getApplicationContext(),ClientHomeActivity.class));
                   // startActivity(new Intent(getApplicationContext(), EmpMasterShowDetails.class));
                    progressBar.setVisibility(View.GONE);
                }else
                {
                    Toast.makeText(ClientLogin.this, "Failed you login! Please check your creadiential!", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}