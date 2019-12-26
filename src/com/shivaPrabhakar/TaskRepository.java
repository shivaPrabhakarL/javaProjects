package com.shivaPrabhakar;

//import java.util.ArrayList;
import java.text.ParseException;
import java.util.List;

public interface TaskRepository {
    TaskObj addTask(String name,String des,String date) throws ParseException;
    TaskObj updateTask(String name, String updatedDesc);
    TaskObj delete(String name);
    TaskObj findById(Integer taskId);
    List<TaskObj> findAll();
    List<TaskObj> findAllByStatus(String status);
    TaskObj searchData(String name);
    TaskObj changeStatus(String name, String status);
    boolean checkData();
    List<TaskObj> sortByDate();
    List<TaskObj> getPendingTasks();
    List<TaskObj> getTodayTasks()throws ParseException;

}
