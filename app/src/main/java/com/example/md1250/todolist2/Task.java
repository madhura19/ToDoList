package com.example.md1250.todolist2;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by md1250 on 9/30/17.
 */


public class Task implements Parcelable {
    //declaration of variables
    private String title;
    private String desc;
    private boolean checkbox;

    //constructor
    public Task(String title, String desc) {
        this.title = title;
        this.desc = desc;
        //this.checkbox = checkbox;
    }

    protected Task(Parcel in) {
        title = in.readString();
        desc = in.readString();
        checkbox = in.readByte() != 0;
    }

    public static final Creator<Task> CREATOR = new Creator<Task>() {
        @Override
        public Task createFromParcel(Parcel in) {
            return new Task(in);
        }

        @Override
        public Task[] newArray(int size) {
            return new Task[size];
        }
    };

    //all getters and setters
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {

        dest.writeString(title);
        dest.writeString(desc);
        dest.writeByte((byte) (checkbox ? 1 : 0));
    }
}
