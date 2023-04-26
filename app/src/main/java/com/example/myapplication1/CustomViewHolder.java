package com.example.myapplication1;

import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

public class CustomViewHolder extends RecyclerView.ViewHolder {

    TextView text_title,text_source,text_content,text_pub_date;

    ImageView img_headline,share_url;
    CardView cardView;
    public CustomViewHolder(@NonNull View itemView) {
        super(itemView);
        text_title =itemView.findViewById(R.id.text_title);
        text_source =itemView.findViewById(R.id.text_source);
        text_content=itemView.findViewById(R.id.text_content);
        text_pub_date=itemView.findViewById(R.id.text_pub_date);
        img_headline =itemView.findViewById(R.id.img_headline);
        cardView =itemView.findViewById(R.id.main_container);
        share_url=itemView.findViewById(R.id.share_url);


    }
}
