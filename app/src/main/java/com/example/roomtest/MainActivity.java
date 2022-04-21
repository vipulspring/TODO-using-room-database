package com.example.roomtest;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.TaskStackBuilder;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private FloatingActionButton buttonAddTask, buttonDeleteTask;
    private RecyclerView recyclerView;
    MainActivityViewModel model;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        ListView listView = (ListView) findViewById(R.id.list);
        ProgressBar progressBar = (ProgressBar) findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);
         model =  new ViewModelProvider(this).get(MainActivityViewModel.class);
        model.getFruitList().observe(this, fruitlist -> {
            // update UI
            ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                    android.R.layout.simple_list_item_1, android.R.id.text1, fruitlist);
            // Assign adapter to ListView
            listView.setAdapter(adapter);
            progressBar.setVisibility(View.GONE);
        });

        buttonDeleteTask = findViewById(R.id.floating_button_delete);
        buttonDeleteTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                model.delete();
            }
        });




        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setVisibility(View.INVISIBLE);

        buttonAddTask = findViewById(R.id.floating_button_add);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);*/

                model.add();

            }
        });

        String abc = "Vipul Singh";
        String reverse = new StringBuffer(abc).reverse().toString();
        Log.i("TAG", "onReverse: " + reverse);

        getTasks();


        int a = 5;
        int b = 12;
        a = a + b;
        b = a - b;
        a = a - b;

        String abcd = "";
        int row = 6;
        for (int i = 0; i <row ; i++) {
            for (int j = row - i; j >1 ; j--) {
                System.out.print(" ");
            }

            for (int j = 0; j <=i ; j++) {
                System.out.print("* ");
                
            }
            System.out.println();
        }



        Integer[] arrayName = new Integer [] {1,2,1,3,1,4,2,5,3,6,3,4,5};
        Arrays.sort(arrayName, Collections.reverseOrder());

        ArrayList<Integer> list = new ArrayList<Integer>(Arrays.asList(arrayName));

        int az=0;
        String av = "";
        for (int i = 0; i < list.size() ; i++) {
            //Log.i(TAG, "onCreate: " + list.get(i));
            az=0;
            av = "";
            av = String.valueOf(list.get(i));
            for (int j = 0; j < list.size() ; j++) {
                if(list.get(i) == list.get(j)){
                 az= az+1;

                    //Log.i(TAG, "onCreate: " + list.get(i));

                }

            }
            ArrayList<Integer> remove = new  ArrayList<Integer>();
            remove.add(list.get(i));
            Log.i(TAG, "onCreate: " + av+ " : " + az);
            list.removeAll(remove);
            i--;
        }

        /*TaskStackBuilder tsb = TaskStackBuilder.from(this);
        tsb.addParentStack(this);
        tsb.addNextIntent(new Intent(this, MainActivity.class));
        tsb.addNextIntent(new Intent(this, MainActivity2.class));
        tsb.addNextIntent(new Intent(this, AddTaskActivity.class));
        tsb.addNextIntent(new Intent(this, AddTaskActivity.class));
        tsb.getIntents();
        tsb.startActivities();*/

    }

    private void getTasks() {
        class GetTasks extends AsyncTask<Void, Void, List<Tasks>> {

            @Override
            protected List<Tasks> doInBackground(Void... voids) {
                List<Tasks> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getSome();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Tasks> tasks) {
                super.onPostExecute(tasks);
                TasksAdapter adapter = new TasksAdapter(MainActivity.this, tasks);
                recyclerView.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, "onRestart: ");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, "onResume: ");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, "onStop: ");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy: ");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, "onPause: ");
    }
}