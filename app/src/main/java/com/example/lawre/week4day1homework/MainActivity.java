package com.example.lawre.week4day1homework;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.lawre.week4day1homework.datasource.retrofit.RetrofitHelper;
import com.example.lawre.week4day1homework.user.Item;
import com.example.lawre.week4day1homework.user.User;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity
{
    ImageView myPicture;
    TextView myId, myUserName, myUrl;
    Button btNextAct, btRxJava;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myPicture = findViewById(R.id.imgPicture);
        myId = findViewById(R.id.tvId);
        myUserName = findViewById(R.id.tvUserName);
        myUrl = findViewById(R.id.tvUrl);
        btNextAct = findViewById(R.id.btNextScreen);
        btNextAct.setText("Go to Repositories");
        btRxJava = findViewById(R.id.btRxJava);
        EventBus.getDefault().register(this);
        Call<User> responseCall = RetrofitHelper.getUser("user:trostbd");
        responseCall.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Item myUser = response.body().getItems().get(0);
                Glide.with(getApplicationContext()).load(myUser.getAvatarUrl()).into(myPicture);
                myId.setText(""+myUser.getId());
                myUserName.setText(myUser.getLogin());
                myUrl.setText(myUser.getUrl());
            }

            @Override
            public void onFailure(Call<User> call, Throwable t)
            {
                Log.d("TAG_", "onFailure: REQUEST FAILED");
            }
        });
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void fillFromUser(Item myUser)
    {

    }

    public void onClick(View view)
    {
        Intent intent;
        if(view.getId() == R.id.btNextScreen) {
            intent = new Intent(this, SecondScreen.class);
            startActivity(intent);
        }
    }
}
