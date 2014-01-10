package com.coursera.todolist;

import java.util.ArrayList;
import android.app.Activity;
import android.content.Context;
import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class TaskAdapter extends ArrayAdapter<Task> {
    private Context context;
    private int layoutResouce;
    private ArrayList<Task> taskList;

	public TaskAdapter(Context context, int resource,  ArrayList<Task> objects) {
		super(context, resource, objects);
		this.context = context;
		this.taskList = objects;
		this.layoutResouce = resource;
	}

	// method to change value in list given an index
	public void set(int index, Task object){
		taskList.set(index, object);
		this.notifyDataSetChanged();
	}
	
	 @Override
	 public View getView(int position, View convertView, ViewGroup parent) {
		 View row = convertView;
	     TaskHolder holder = null;
	        
	     if(row == null) {
	     	LayoutInflater inflater = ((Activity)context).getLayoutInflater();
	        row = inflater.inflate(layoutResouce, parent, false);
	            
	        holder = new TaskHolder();
	        holder.name = (TextView)row.findViewById(R.id.task_name);
	        holder.desc = (TextView)row.findViewById(R.id.task_description);
	            
	        row.setTag(holder);
	     } else {
	        holder = (TaskHolder)row.getTag();
	     }
	        
	     Task t = taskList.get(position);
	     holder.name.setText(t.getName());
	     holder.desc.setText(t.getDesc());
	
	     // paint text strike-through if the task is marked as completed
         if(t.isCompleted()){
            holder.name.setPaintFlags(holder.name.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
            holder.desc.setPaintFlags(holder.desc.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
         }else{
        	holder.name.setPaintFlags(holder.name.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
        	holder.desc.setPaintFlags(holder.desc.getPaintFlags() & (~Paint.STRIKE_THRU_TEXT_FLAG));
         }
	        
	     return row;
	 }
	    
	 static class TaskHolder {
		 TextView name;
	     TextView desc;
	 }

}
