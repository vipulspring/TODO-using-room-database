package com.example.roomtest;

import android.os.Handler;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class MainActivityViewModel extends ViewModel {
    List<String> fruitsStringList = new ArrayList<>();
    private static final String TAG = MainActivityViewModel.class.getSimpleName();
    private MutableLiveData<List<String>> fruitList;
    LiveData<List<String>> getFruitList(){
        if(fruitList == null){
            fruitList = new MutableLiveData<>();
            loadFruit();
        }
        return fruitList;
    }

    private void loadFruit() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                fruitsStringList.add("Mango");
                fruitsStringList.add("Apple");
                fruitsStringList.add("Orange");
                fruitsStringList.add("Bannana");
                fruitsStringList.add("Grapes");
                long seed = System.nanoTime();
                Collections.shuffle(fruitsStringList, new Random(seed));
                fruitList.setValue(fruitsStringList);


            }
        }, 5000);

    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "on cleared called");
    }

    public void add(){

        fruitsStringList.add("Vipul");
        fruitList.setValue(fruitsStringList);


    }

    public void delete(){
        int a = fruitsStringList.size();
        if(a>0){
            fruitsStringList.remove(fruitsStringList.size()-1);
        }
        fruitList.setValue(fruitsStringList);

    }

}
