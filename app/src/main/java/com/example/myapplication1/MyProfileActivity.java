package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MyProfileActivity extends AppCompatActivity {
    private TextView emailText;
    ImageButton btnBack;
    private Button logoutButton;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);

        emailText = findViewById(R.id.email_text);

        logoutButton = findViewById(R.id.logout_button);
        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();



        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(MyProfileActivity.this,MainActivity.class));
                Toast.makeText(MyProfileActivity.this,"User logged out successfully", Toast.LENGTH_SHORT).show();

            }
        });

        btnBack=findViewById(R.id.btnBACK);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser user=firebaseAuth.getCurrentUser();
        if(user != null){
            emailText.setText(firebaseUser.getEmail());

        }
        else {
            startActivity(new Intent(MyProfileActivity.this,MainActivity.class));
        }
    }
}