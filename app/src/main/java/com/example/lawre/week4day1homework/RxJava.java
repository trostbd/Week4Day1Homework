package com.example.lawre.week4day1homework;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.FlowableSubscriber;
import io.reactivex.Observable;
import io.reactivex.Scheduler;
import io.reactivex.Single;
import io.reactivex.SingleEmitter;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

public class RxJava extends AppCompatActivity
{
    TextView tvRange, tvJust, tvInterval;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rx_java);
        tvRange = findViewById(R.id.tvRange);
        tvJust = findViewById(R.id.tvJust);
        tvInterval = findViewById(R.id.tvInterval);
        doRange();
        doJust();
        //   doInterval();
    }

    public void doRange()
    {
        Observable.range(1,10).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableObserver<Integer>() {
                    @Override
                    public void onNext(Integer integer) {
                        String updateText = tvRange.getText().toString() + integer + " ";
                        tvRange.setText(updateText);
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    public void doJust()
    {
        Flowable.just("abcdefghijklmnopqrstuvwxyz").subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Exception {
                        tvJust.setText(s);
                    }
                });
    }

    public void doInterval()
    {
        Flowable<Long> timer = Flowable.timer(1, TimeUnit.SECONDS);
        timer.subscribe(new FlowableSubscriber<Long>() {
            @Override
            public void onSubscribe(Subscription s) {
                tvInterval.setText("Interval start");
            }

            @Override
            public void onNext(Long aLong) {
                tvInterval.setText(aLong+"");
            }

            @Override
            public void onError(Throwable t) {

            }

            @Override
            public void onComplete() {

            }
        });
    }
}