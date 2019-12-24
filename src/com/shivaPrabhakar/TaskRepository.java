package com.shivaPrabhakar;

//import java.util.ArrayList;
import java.util.List;

public interface TaskRepository {
    TaskObj addTask(TaskObj Task);
    void updateTask(Integer taskId,TaskObj task);
    void delete(Integer TaskId);
    TaskObj findById(Integer taskId);
    List<TaskObj> findAll();
    List<TaskObj> findAllByStatus(Status status);
}
