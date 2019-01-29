package com.example.lawre.week4day1homework.datasource.retrofit;

import com.example.lawre.week4day1homework.repository.Repository;
import com.example.lawre.week4day1homework.user.User;

import io.reactivex.Observable;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.lawre.week4day1homework.Constants.BASE_URL;
import static com.example.lawre.week4day1homework.Constants.PATH;
import static com.example.lawre.week4day1homework.Constants.QUERY_USER;
import static com.example.lawre.week4day1homework.Constants.REPO_PATH;

public class RetrofitHelper
{
    public static Retrofit createRetrofitInstance()
    {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(BASE_URL).addConverterFactory(GsonConverterFactory.create()).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();
        return retrofit;
    }

    public static Call<User> getUser(String userName)
    {
        Retrofit retrofit = createRetrofitInstance();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        return remoteService.getUserList(userName);
    }

    public static Call<Repository> getRepository(String userName)
    {
        Retrofit retrofit = createRetrofitInstance();
        RemoteService remoteService = retrofit.create(RemoteService.class);
        return remoteService.getRepoList(userName);
    }

    public interface RemoteService{
        @GET(PATH)
        Call<User> getUserList(@Query(QUERY_USER) String userName);

        @GET(PATH)
        Observable<User> getUserObservable(@Query(QUERY_USER) String userName);

        @GET(REPO_PATH)
        Call<Repository> getRepoList(@Query(QUERY_USER) String userName);
    }
}
