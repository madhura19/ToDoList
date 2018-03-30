package com.example.md1250.todolist2;

import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by md1250 on 9/29/17.
 */


public class CustomAdapter extends ArrayAdapter {

    ArrayList<Task> list = new ArrayList<>();

    public CustomAdapter(Context context, int resource) {
        super(context, resource);
    }

    static class TaskHandler{
        TextView task_title;
        TextView task_description;
        CheckBox checkBox;
    }

    //override all array adapter methods
    @Override
    public void add(Object object) {
        super.add(object);
        list.add((Task) object);
    }

    @Override
    public int getCount() {
        return this.list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return this.list.get(position);
    }

    @Override
    public void remove(Object object) {
        list.remove(object);
    }

    @NonNull
    @Override
    public View getView(final int position, View convertView, ViewGroup parent){
        View list_item = convertView;
        TaskHandler handler;

        if (convertView == null){
            LayoutInflater inflater = (LayoutInflater) this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            list_item = inflater.inflate(R.layout.item_list, parent, false);
            handler = new TaskHandler();
            handler.task_title = (TextView) list_item.findViewById(R.id.task_title);
            handler.task_description = (TextView) list_item.findViewById(R.id.task_description);
            handler.checkBox = (CheckBox) list_item.findViewById(R.id.checkBox);
            list_item.setTag(handler);

            //remove when checkbox is clicked
            handler.checkBox.setOnClickListener(new View.OnClickListener(){
                public void onClick(View v){

                    //removes
                    remove(getItem(position));
                    //notifies adapter of the removal
                    notifyDataSetChanged();
                }
            });
        }
        else {
            handler = (TaskHandler) list_item.getTag();
        }
        Task task;
        task = (Task) this.getItem(position);
        handler.task_title.setText(task.getTitle());
        handler.task_description.setText(task.getDesc());
        handler.checkBox.setChecked(task.isCheckbox());
        handler.checkBox.setTag(task);

        return list_item;
    }
}
