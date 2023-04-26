package com.example.myapplication1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myapplication1.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

public class NewsDetailsActivity extends AppCompatActivity {
    NewsHeadlines headlines;
    TextView title,pubDate,description,content;
    ImageView imageView;
    ImageButton btnBack;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        title=findViewById(R.id.de_title);
        pubDate=findViewById(R.id.de_pubDate);
       // description=findViewById(R.id.de_description);
        content=findViewById(R.id.de_content);
        imageView=findViewById(R.id.de_img);
        headlines=(NewsHeadlines)getIntent().getSerializableExtra("data");

        title.setText(headlines.getTitle());
        pubDate.setText(headlines.getPubDate());
       // description.setText(headlines.getDescription());
        content.setText(headlines.getContent());
        if (headlines.getImage_url() == null){
            imageView.setImageResource(R.drawable.noimg);
        }
        else{
            Picasso.get().load(headlines.getImage_url()).into(imageView);
        }
        btnBack=findViewById(R.id.btnBACK);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });


    }
}