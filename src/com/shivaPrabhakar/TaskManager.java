package com.shivaPrabhakar;
import java.text.ParseException;
import java.util.List;

public class TaskManager  {

    TaskFileRepostory tr;
    public TaskManager() {
        System.out.println("Initializing TaskFileRepo");
       this.tr = new TaskFileRepostory();
    }

    public boolean checkData(){
        return tr.checkData();
    }


    public TaskObj addTask(String name, String des, String date) throws ParseException {
        return tr.addTask(name, des, date);

    }


    public  List<TaskObj>  findAll(){
        return tr.findAll();
    }


    public  TaskObj searchData(String name) {
        return tr.searchData(name);
    }


    public  TaskObj delete(String name){
           return tr.delete(name);
    }


    public  List<TaskObj> findAllByStatus(String qq){
        return tr.findAllByStatus(qq);
    }


    public  TaskObj changeStatus(String name, String st) {
        return tr.changeStatus(name, st);
    }


    public  TaskObj updateTask(String name, String updatedDesc) {
        //System.out.println(updatedDesc);
        return tr.updateTask(name, updatedDesc);
    }

    public List<TaskObj> sortByDate(){
        return tr.sortByDate();
    }

    public List<TaskObj> getPendingTasks(){
        return tr.getPendingTasks();
    }

    public List<TaskObj> getTodayTask()throws ParseException{
        return tr.getTodayTasks();
    }
}
