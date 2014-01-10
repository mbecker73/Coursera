package com.coursera.todolist;

public class Task extends Object{
    private String name;
    private String desc;
    private boolean isComplete;
    
    public Task(){
        super();
    }
    
    public Task(String n, String d) {
        super();
        this.name = n;
        this.desc = d;
        this.isComplete = false;
    }
    
    public void setComplete(boolean c){
    	this.isComplete = c;
    }
    
    public boolean isCompleted(){
    	return this.isComplete;
    }
    
    public void setName(String n){
    	this.name = n;
    }
    
    public String getName(){
    	return this.name;
    }
    
    public void setDesc(String d){
        this.desc = d;
    }
    
    public String getDesc(){
    	return this.desc;
    }
    
    public String toString(){
		return name + " : " + desc;
    }
}