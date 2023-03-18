package com.example.myapplication1.Models;

import java.io.Serializable;
import java.util.List;

public class NewsApiResponse implements Serializable {
    String status;
    int totalResults;
    List<NewsHeadlines> results;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<NewsHeadlines> getResults() {
        return results;
    }

    public void setResults(List<NewsHeadlines> results) {
        this.results = results;
    }
}
