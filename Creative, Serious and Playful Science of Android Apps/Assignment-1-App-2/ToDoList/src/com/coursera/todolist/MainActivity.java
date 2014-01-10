package com.coursera.todolist;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Map;

import android.os.Bundle;
import android.preference.PreferenceManager;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.EditText;
import android.widget.ListView;

public class MainActivity extends Activity implements OnItemClickListener {

	EditText taskInput;
	ListView taskList;
    TaskAdapter adapter;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		taskInput = (EditText) findViewById(R.id.task_input);
		taskList = (ListView) findViewById(R.id.task_list);
		adapter = new TaskAdapter(this, R.layout.task_row,new ArrayList<Task>());
		taskList.setAdapter(adapter);
		taskList.setOnItemClickListener(this);
		
		// load any saved tasks from the SharedPreferences to the list and display them
		SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
		Map<String, ?> savedTasks = preferences.getAll();
		for(String t : savedTasks.keySet()){
			if(!t.equalsIgnoreCase("")) {
				// only load tasks that are not marked as completed and have proper format
				if(savedTasks.get(t).equals("todo") && t.contains(":")){
					
					String name = t.substring(0, t.indexOf(":")-1);
					String desc = t.substring(t.indexOf(":") +1, t.length());
			        adapter.add(new Task(name.trim(), desc.trim()));
			        Log.i("TODO", "Loaded from SharedPrefences saved task: " + t);
				} 
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void createTask(View v) {
        // a task is created from user input, stored, and displayed
	      if (taskInput.getText().length() > 0){
	    	  
	    	  String taskName = taskInput.getText().toString();
	    	  String time = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());

	    	  Task createdTask = new Task(taskName, time);
	    	  createdTask.setComplete(false);
	          adapter.add(createdTask);
	              
	          SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
	          SharedPreferences.Editor editor = preferences.edit();
	          editor.putString(createdTask.toString(),"todo").commit();
	          taskInput.setText(""); //reset input EditText 
	          
		      Log.i("TODO", "Created new task: " + createdTask.toString());
	      }
	  }
	
	@Override
	public void onItemClick(AdapterView<?> a, final View view, final int pos, long id) {
		
		AlertDialog.Builder builder = new AlertDialog.Builder(view.getContext());
		builder.setTitle("You Clicked a Task!").setMessage("What would you like to do?");

        // User clicked task as completed 
		builder.setPositiveButton("Toggle Task Complete", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   //toggle between task being marked as complete or todo
	        	   SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
		           SharedPreferences.Editor editor = preferences.edit();
		           Task current = adapter.getItem(pos);
	
		           if(!current.isCompleted()){
		        	   current.setComplete(true);
			           editor.putString(current.toString(),"finished");
		           } else{
		        	   current.setComplete(false);
			           editor.putString(current.toString(),"todo");
		           }
	        	   editor.commit();
	        	   adapter.notifyDataSetChanged();
	 		       Log.i("TODO", "Toggled status of task " + current.getName() + " to be isCompleted = " + current.isCompleted());

	           }
	       });
		
        // User cancelled out of dialog 
		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
	        public void onClick(DialogInterface dialog, int id) {
	        	dialog.cancel();
	        }
	    });
		
		// User clicked for task to be renamed
		builder.setNeutralButton("Rename Task", new DialogInterface.OnClickListener() {
	           public void onClick(DialogInterface dialog, int id) {
	        	   //create custom alert dialog to prompt user to input text (new task name)
	        	   LayoutInflater inflater = LayoutInflater.from(view.getContext());
	        	   View promptEditTask = inflater.inflate(R.layout.prompts, null);
	        	   AlertDialog.Builder renameDialogBuilder = new AlertDialog.Builder(view.getContext());
	        	   renameDialogBuilder.setView(promptEditTask);
	        	   final EditText userInput = (EditText) promptEditTask.findViewById(R.id.editTextDialogUserInput);

	        	   renameDialogBuilder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					    //create dialog to edit text 
						public void onClick(DialogInterface dialog,int id) {
							
							// get user input for new task name and store the change
							SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
							SharedPreferences.Editor editor = preferences.edit();
							
					        Task current = adapter.getItem(pos);
							editor.remove(current.toString()).commit();

							String newTaskName = userInput.getText().toString();
							current.setName(newTaskName);
							// update time description when name is edited
					    	String time = DateFormat.getDateTimeInstance().format(Calendar.getInstance().getTime());
					    	current.setDesc(time);
							adapter.set(pos, current);
							editor.putString(current.toString(),"todo").commit();

							taskInput.setText("");
				 		    Log.i("TODO", "Renamed task " + current + " to " + newTaskName);

					    }
					  });
					//user cancelled out of edit name dialog
					renameDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {
					    	dialog.cancel();
					    }
					});
					
					AlertDialog renameDialog = renameDialogBuilder.create(); 
					renameDialog.show();

	           }
	    });
	
		//show dialog once task first clicked
		AlertDialog dialog = builder.create();
		dialog.show();
		
	}

}
