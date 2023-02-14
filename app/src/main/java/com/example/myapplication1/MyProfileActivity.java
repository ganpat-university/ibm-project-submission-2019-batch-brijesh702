package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class MyProfileActivity extends AppCompatActivity {
    private ImageView profileImage;
    private TextView emailText;
    private TextView nameText;
    private Button logoutButton;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_profile);
        profileImage = findViewById(R.id.profile_image);
        emailText = findViewById(R.id.email_text);
        nameText= findViewById(R.id.Name_text1);
        //passwordText = findViewById(R.id.password_text);
        logoutButton = findViewById(R.id.logout_button);

        firebaseAuth = firebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();

        emailText.setText(firebaseUser.getEmail());
        nameText.setText(firebaseUser.getUid());
      //  passwordText.setText("Password:"+ firebaseUser.getPa());


        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                firebaseAuth.signOut();
                startActivity(new Intent(MyProfileActivity.this,MainActivity.class));
                Toast.makeText(MyProfileActivity.this,"User logged out successfully", Toast.LENGTH_SHORT).show();

            }
        });

    }
}