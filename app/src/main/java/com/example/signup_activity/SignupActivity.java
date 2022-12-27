package com.example.signup_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.signup_activity.databinding.ActivitySignupBinding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignupActivity extends AppCompatActivity {
    ActivitySignupBinding binding;
    FirebaseAuth auth;
    FirebaseDatabase database;
    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivitySignupBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

    progressDialog = new ProgressDialog(SignupActivity.this);
    progressDialog.setTitle("Creating your account");
    progressDialog.setMessage("We're creating your account, HOLD ON!");

        binding.signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                progressDialog.show();
                auth.createUserWithEmailAndPassword(binding.Email.getText().toString(), binding.password.getText().toString()).
                        addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();

                        if(task.isSuccessful()){
                        Users user = new Users(binding.name.getText().toString(),binding.Email.getText().toString(),
                                binding.password.getText().toString());

                        String id = task.getResult().getUser().getUid();
                        database.getReference().child("Users Login Details").child(id).setValue(user);
                        Toast.makeText(SignupActivity.this,"Successfully Created your account",Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(SignupActivity.this, Activity4.class));
                        finish();
                        }

                        else
                        {
                       Toast.makeText(SignupActivity.this,task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                        }

                    }
                });
            }
        });

        binding.txtsignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(true){
                    progressDialog.setMessage("Please wait");
                    progressDialog.show();

                    Intent intent = new Intent(SignupActivity.this, Signinactivity.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    progressDialog.dismiss();
                }

            }
        });
    }}







