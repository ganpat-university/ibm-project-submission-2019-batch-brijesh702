package com.example.myapplication1;

import com.example.myapplication1.Models.NewsHeadlines;

import java.util.List;

public interface OnFetchDataListener<NewsApiResponse> {
    void onFetchData(List<NewsHeadlines> list,String message);
    void onError(String message);
}
