package com.example.myapplication1;



import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout.OnRefreshListener;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication1.Models.NewsApiResponse;
import com.example.myapplication1.Models.NewsHeadlines;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

public class home extends AppCompatActivity implements SelectListener,View.OnClickListener{
    FirebaseAuth mAuth;
    RecyclerView recyclerView;
    CustomAdapter adapter;
    ProgressDialog dialog;
    Button b1,b2,b3,b4,b5,b6,b7,b8,b9,b10,b11;
    androidx.appcompat.widget.SearchView searchView;
    SwipeRefreshLayout swipeRefreshLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        searchView= findViewById(R.id.search_view);
        swipeRefreshLayout=findViewById(R.id.refresh_view);
        mAuth = FirebaseAuth.getInstance(); //database connection
        ImageView profileIcon = (ImageView) findViewById(R.id.profile_icon);
        b1=findViewById(R.id.btn1);
        b1.setOnClickListener(this);
        b2=findViewById(R.id.btn2);
        b2.setOnClickListener(this);
        b3=findViewById(R.id.btn3);
        b3.setOnClickListener(this);
        b4=findViewById(R.id.btn4);
        b4.setOnClickListener(this);
        b5=findViewById(R.id.btn5);
        b5.setOnClickListener(this);
        b6=findViewById(R.id.btn6);
        b6.setOnClickListener(this);
        b7=findViewById(R.id.btn7);
        b7.setOnClickListener(this);
        b8=findViewById(R.id.btn8);
        b8.setOnClickListener(this);
        b9=findViewById(R.id.btn9);
        b9.setOnClickListener(this);
        b10=findViewById(R.id.btn10);
        b10.setOnClickListener(this);
        b11=findViewById(R.id.btn11);
        b11.setOnClickListener(this);

        profileIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(home.this, MyProfileActivity.class);
                startActivity(intent);
            }
        });

        //search option
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                dialog.setTitle("Fetching News Articles For"+ query);
                dialog.show();
                RequestManager manager= new RequestManager(home.this);
                manager.getNewsHeadlines(listener,null,query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });

        /*
         * Sets up a SwipeRefreshLayout.OnRefreshListener that is invoked when the user
         * performs a swipe-to-refresh gesture.
         */
        swipeRefreshLayout.setOnRefreshListener(
                new SwipeRefreshLayout.OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        refreshnews();

                    }
                }
        );
        //default news print with random category
        dialog =new ProgressDialog(this);
        dialog.setTitle("Fetching News Articles....");
        dialog.show();

        RequestManager manager= new RequestManager(this);
        manager.getNewsHeadlines(listener,null,null);
    }

    private void refreshnews() {
        //dialog =new ProgressDialog(this);
        //dialog.setTitle("Fetching My Feed....");
        //dialog.show();
        RequestManager manager= new RequestManager(this);
        manager.getNewsHeadlines(listener,null,null);

    }

    //end on create option


    private final OnFetchDataListener<NewsApiResponse> listener= new OnFetchDataListener<NewsApiResponse>() {
        @Override
        public void onFetchData(List<NewsHeadlines> list, String message) {
            if(list.isEmpty()){
                Toast.makeText(home.this, "No Data Found", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
            else {
                showNews(list);
                swipeRefreshLayout.setRefreshing(false);
                dialog.dismiss();
            }

        }

        @Override
        public void onError(String message) {
            Toast.makeText(home.this,"Error While Fetching News!!",Toast.LENGTH_SHORT).show();


        }
    };
    //recyclerview
    private void showNews(List<NewsHeadlines> list) {
        recyclerView=findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(this,1));
        adapter=new CustomAdapter(this,list,this);
        recyclerView.setAdapter(adapter);
    }

    /*protected void onStart() {
        super.onStart();
        FirebaseUser user = mAuth.getCurrentUser();
        if (user == null){
            startActivity(new Intent(home.this, MainActivity.class));
        }
    }*/

    @Override
    public void OnNewsClicked(NewsHeadlines headlines) {
        startActivity(new Intent(home.this,NewsDetailsActivity.class )
                .putExtra("data",headlines));

    }

    @Override
    public void onClick(View v) {
        Button button=(Button) v;
        String category =button.getText().toString();
        dialog.setTitle("Fetching News Articles For "+category);
        dialog.show();
        RequestManager manager= new RequestManager(this);
        manager.getNewsHeadlines(listener,category,null);

    }

}