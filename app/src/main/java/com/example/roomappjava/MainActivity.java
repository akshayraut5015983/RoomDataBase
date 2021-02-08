package com.example.roomappjava;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;

import com.example.roomappjava.model.DatabaseClient;
import com.example.roomappjava.model.Task;
import com.example.roomappjava.model.TaskAk;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private FloatingActionButton buttonAddTask;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView = findViewById(R.id.recyclerview_tasks);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        buttonAddTask = findViewById(R.id.floating_button_add);
        buttonAddTask.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, AddTaskActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.floatingBtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this, AkshayTaskActivity.class);
                startActivity(intent);
            }
        });
        findViewById(R.id.floatingBtnnew).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getTasksB();
            }
        });

        getTasks();

    }

    private void getTasksB() {

        class GetTasks extends AsyncTask<Void, Void, List<TaskAk>> {

            @Override
            protected List<TaskAk> doInBackground(Void... voids) {
                List<TaskAk> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskAkDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<TaskAk> tasks) {
                super.onPostExecute(tasks);
               /* TasksAdapter adapter = new TasksAdapter(MainActivity.this, tasks);
                recyclerView.setAdapter(adapter);*/
                for (TaskAk taskAk : tasks) {
                    System.out.println(taskAk.getTask());
                    System.out.println(taskAk.getId());
                }

            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

    private void getTasks() {

        class GetTasks extends AsyncTask<Void, Void, List<Task>> {

            @Override
            protected List<Task> doInBackground(Void... voids) {
                List<Task> taskList = DatabaseClient
                        .getInstance(getApplicationContext())
                        .getAppDatabase()
                        .taskDao()
                        .getAll();
                return taskList;
            }

            @Override
            protected void onPostExecute(List<Task> tasks) {
                super.onPostExecute(tasks);
                TasksAdapter adapter = new TasksAdapter(MainActivity.this, tasks);
                recyclerView.setAdapter(adapter);
            }
        }

        GetTasks gt = new GetTasks();
        gt.execute();
    }

}