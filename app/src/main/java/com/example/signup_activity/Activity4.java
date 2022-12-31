package com.example.signup_activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.signup_activity.databinding.Activity4Binding;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


import java.util.HashMap;

public class Activity4 extends AppCompatActivity {
    FirebaseAuth auth;
    Activity4Binding binding;
    ProgressDialog progressDialog;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = Activity4Binding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        auth = FirebaseAuth.getInstance();
        FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
        progressDialog= new ProgressDialog(this);
        progressDialog.setTitle("Processing");
        progressDialog.setMessage("Please wait a bit!");


        binding.update.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                if(user!=null) {
                    if (!binding.updateemail.getText().toString().equalsIgnoreCase("")) {
                        progressDialog.show();
                        String email = binding.updateemail.getText().toString();
                        updateData(email);
                        user.updateEmail(binding.updateemail.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.dismiss();
                                if (task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Email updated", Toast.LENGTH_SHORT).show();


                                    auth.signOut();
                                    startActivity(new Intent(Activity4.this, Signinactivity.class));
                                    finish();
                                } else {
                                    Toast.makeText(Activity4.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();

                                }

                            }
                        });
                    } else {
                        Toast.makeText(getApplicationContext(), "Please enter details.", Toast.LENGTH_SHORT).show();
                    }

                }
            }

        });

        binding.btnpassword.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if(user!=null){
                    if(!binding.updatepassword.getText().toString().equalsIgnoreCase("")){
                        progressDialog.show();
                        String password = binding.updatepassword.getText().toString();
                        upDatepassword(password);
                        user.updatePassword(binding.updatepassword.getText().toString()).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                progressDialog.dismiss();
                                if(task.isSuccessful()){

                                    Toast.makeText(getApplicationContext(),"Password Updated",Toast.LENGTH_SHORT).show();
                                    auth.signOut();
                                    startActivity(new Intent(Activity4.this, Signinactivity.class));
                                }
                                else{
                                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                    else {
                        Toast.makeText(getApplicationContext(),"Please enter the details", Toast.LENGTH_SHORT).show();
                    }
                }
                else {Toast.makeText(getApplicationContext(),"User is already logged in",Toast.LENGTH_SHORT).show();}


            }
        });


    }

    private void upDatepassword(String password) {
        HashMap User = new HashMap();
        User.put("password",password);
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users Login Details");
        reference.child(auth.getUid()).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    binding.updatepassword.setText("");
                }
                else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void updateData(String email) {
        HashMap User = new HashMap();
        User.put("mail",email);

        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Users Login Details");
        reference.child(auth.getUid()).updateChildren(User).addOnCompleteListener(new OnCompleteListener() {
            @Override
            public void onComplete(@NonNull Task task) {
                if(task.isSuccessful()){
                    binding.updateemail.setText("");

                }
                else{
                    Toast.makeText(getApplicationContext(),task.getException().getMessage(),Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){

            case R.id.settings:
                Toast.makeText(this, "Settings closed", Toast.LENGTH_SHORT).show();
             break;

            case R.id.logout:
                auth.signOut();
                Intent intent = new Intent(Activity4.this, Signinactivity.class);
                startActivity(intent);
                finish();
                break;
        }

        return true;
    }


}