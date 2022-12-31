package com.example.signup_activity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.signup_activity.databinding.ActivitySigninBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class Signinactivity extends AppCompatActivity {
ActivitySigninBinding binding;
FirebaseAuth auth;
ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();

        binding = ActivitySigninBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth= FirebaseAuth.getInstance();
        progressDialog =  new ProgressDialog(Signinactivity.this);
        progressDialog.setTitle("Login");
        progressDialog.setMessage("Please wait");

        binding.btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            if(!binding.edtemail.getText().toString().equalsIgnoreCase("") && !binding.edtpassword.getText().toString().equalsIgnoreCase(""))
            {
                progressDialog.show();
                auth.signInWithEmailAndPassword(binding.edtemail.getText().toString(),binding.edtpassword.getText().toString())
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful()){

                                    Intent intent= new Intent(Signinactivity.this, Activity4.class);
                                    startActivity(intent);
                                    Toast.makeText(getApplicationContext(),"Successfully Login", Toast.LENGTH_SHORT).show();
                                    finish();
                                }

                                else{

                                    Toast.makeText(getApplicationContext(), task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }


                            }
                        });
            }
            else{
                Toast.makeText(getApplicationContext(),"Please Enter Details First", Toast.LENGTH_SHORT).show();
            }
            }
        });



        binding.donthaveanaccount.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                if (true){
                    progressDialog.setMessage("Please Wait");
                    progressDialog.show();
                    Intent intent = new Intent(Signinactivity.this, SignupActivity.class);
                    startActivity(intent);
                    finish();
                }
              else{
                  progressDialog.dismiss();
                }
            }

        });


        if(auth.getCurrentUser()!= null)
        {
            Intent intent = new Intent(Signinactivity.this, Activity4.class);
            startActivity(intent);
            finish();
        }

    }

}