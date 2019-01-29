package com.example.lawre.week4day1homework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.lawre.week4day1homework.datasource.retrofit.RetrofitHelper;
import com.example.lawre.week4day1homework.repository.Repository;
import com.example.lawre.week4day1homework.repository.Item;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SecondScreen extends AppCompatActivity
{
    RecyclerView recyclerView;
    RecyclerViewAdapter recyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second_screen);
        recyclerView = findViewById(R.id.rvView);
        RecyclerView.LayoutManager layoutMan = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutMan);
        Call<Repository> responseCall = RetrofitHelper.getRepository("user:trostbd");
        responseCall.enqueue(new Callback<Repository>() {
            @Override
            public void onResponse(Call<Repository> call, Response<Repository> response) {
                List<Item> repos = response.body().getItems();
                ArrayList<Item> repoList = new ArrayList<Item>(repos);
                recyclerViewAdapter = new RecyclerViewAdapter(repoList);
                recyclerView.setAdapter(recyclerViewAdapter);
            }

            @Override
            public void onFailure(Call<Repository> call, Throwable t) {

            }
        });
    }
}
