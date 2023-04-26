package com.example.myapplication1;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication1.Models.NewsHeadlines;
import com.squareup.picasso.Picasso;

import java.net.URI;
import java.net.URL;
import java.util.List;

public class CustomAdapter extends RecyclerView.Adapter<CustomViewHolder> {
    private Context context;
    private List<NewsHeadlines> headlines;
    private SelectListener listener;

    public CustomAdapter(Context context, List<NewsHeadlines> headlines, SelectListener listener) {
        this.context = context;
        this.headlines = headlines;
        this.listener =listener;
    }

    @NonNull
    @Override
    public CustomViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        return new CustomViewHolder(LayoutInflater.from(context).inflate(R.layout.cardview,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CustomViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.text_title.setText(headlines.get(position).getTitle());
        holder.text_content.setText(headlines.get(position).getContent());
        holder.text_source.setText(headlines.get(position).getSource_id());
        holder.text_pub_date.setText(headlines.get(position).getPubDate());
        String URL_LINK=headlines.get(position).getLink();
        holder.share_url.setOnClickListener(v -> {
            Intent share= new Intent(Intent.ACTION_SEND);
            share.setType("text/plan");
            share.putExtra(Intent.EXTRA_TEXT,"Tap To See Latest News Happening Around You!!  \n\n"+URL_LINK);
            context.startActivity(share);
        });
        if (headlines.get(position).getImage_url() == null){
            holder.img_headline.setImageResource(R.drawable.noimg);
        }
        else{
            Picasso.get().load(headlines.get(position).getImage_url()).into(holder.img_headline);
        }
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.OnNewsClicked(headlines.get(position));
            }
        });

    }

    @Override
    public int getItemCount() {
        return headlines.size();
    }
}
