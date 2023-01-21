package com.example.myapplication1;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.appcompat.widget.Toolbar;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.myapplication1.databinding.ActivityHomeBinding;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class home extends AppCompatActivity {
    ActivityHomeBinding binding;
    private androidx.appcompat.widget.Toolbar Toolbar;
    Button btnLogOut;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //setContentView(R.layout.activity_home);
        btnLogOut = findViewById(R.id.navigation_logout); //logout top navigation
        mAuth = FirebaseAuth.getInstance();

        Toolbar toolbar=findViewById(R.id.appbar);
        setSupportActionBar(Toolbar);
        binding=ActivityHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot()) ;
        replaceFragment(new HomeFragment());

        binding.bottomNavigationView.setOnItemSelectedListener(item -> {
            switch (item.getItemId()){
                case R.id.navigation_home:
                    replaceFragment(new HomeFragment());

                    break;
                case R.id.navigation_category:
                    replaceFragment(new CategoryFragment());

                    break;
                case R.id.navigation_bookmark:
                    replaceFragment(new BookmarkFragment());
                   // startActivity(new Intent(home.this,MyProfileActivity.class));
                    break;
                case R.id.navigation_logout:
                    mAuth.signOut();
                    startActivity(new Intent(home.this,MainActivity.class));
                    Toast.makeText(home.this,"User logged out successfully", Toast.LENGTH_SHORT).show();
                    break;
            }
            return  true;
        });

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_nev_menu,menu);
        return true;
    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction fragmentTransaction=fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.bottomframelayout,fragment);
        fragmentTransaction.commit();

    }

    protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(home.this, MainActivity.class));
        }
    }
}