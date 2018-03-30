package com.example.md1250.todolist2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    //declarations
    //ArrayList<Task> myList = new ArrayList<Task>();
    public Task tasks;
    private ListView todo_list;
    private String[] title;
    private String[] description;
    CustomAdapter adapter;
    private EditText task_title;
    private EditText task_description;
    private Button add_button;
    private static int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialize
        todo_list = (ListView) findViewById(R.id.todo_list);
        title = new String[100];
        description = new String[100];
        adapter = new CustomAdapter(this, R.layout.item_list);

        //check if the activity is already created
        if (savedInstanceState != null){

            //restore all the saved instances
            adapter.list = savedInstanceState.getParcelableArrayList("adapterlist");
        }
        todo_list.setAdapter(adapter);

        todo_list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                //remove
                adapter.remove(adapter.getItem(position));
                //notify the adapter of the removal
                adapter.notifyDataSetChanged();
                return false;
            }
        });

        add_button = (Button) findViewById(R.id.add_button);
        task_title = (EditText) findViewById(R.id.input_task_title);
        task_description = (EditText) findViewById(R.id.input_task_description);

    }

    @Override
    protected void onStop() {
        super.onStop();

     }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        if (savedInstanceState != null){

            //restore all the saved instances
            adapter.list = savedInstanceState.getParcelableArrayList("adapterlist");
        }
    }

    //saved instance state called when activity is terminated
    @Override
    public void onSaveInstanceState(Bundle outState) {
        Log.d("onSaveInstanceState", "onSaveInstanceState called");
        super.onSaveInstanceState(outState);

        //save all instances
        outState.putParcelableArrayList("adapterlist", adapter.list);
    }

    //ADD button
    public void onClickAdd(View view) throws FileNotFoundException {
        // gets text from Edit Text
        title[i] = task_title.getText().toString();
        description[i] = task_description.getText().toString();
        tasks = new Task(title[i], description[i]);     //create a Task ObjecT
        PrintStream output = new PrintStream(openFileOutput("tasks.txt", MODE_APPEND));        //creates a text file
        output.println("Task" + i + ": "+ "Title = " + title[i] + "Description = " + i + ": " + description[i]);
        adapter.add(tasks);
        task_title.setText("");
        task_description.setText("");
        i++;



    }

}


