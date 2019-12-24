package com.shivaPrabhakar;
import java.util.ArrayList;
import java.util.Date;

class TaskManagement {

    protected  ArrayList<TaskObj> task = new ArrayList<>();

    protected  boolean isNumeric(String q){
        try{
             Integer.parseInt(q);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    protected  void addData(String name,String des, int i){

        TaskObj to = new TaskObj();
        to.setName(name);
        to.setDesc(des);
        to.setId(i);
        to.setDate(new Date());
        task.add(to);

    }

    protected  ArrayList<TaskObj>  listData(){
        if(task.size() > 0)
            return task;
        else
            return null;
    }

    protected  TaskObj searchData(String name) {
        if(!isNumeric(name)) {
            for (TaskObj obj : task) {
                String query = obj.getName();
                if (query.equalsIgnoreCase(name)) {
                    return obj;
                }
            }
        }
        else if(isNumeric(name)){
            for (TaskObj obj : task) {
                int query = obj.getId();
                if (query == Integer.parseInt(name)) {
                    return obj;
                }
            }
        }
        return null;
    }

    protected  TaskObj deleteData(TaskObj obj, String as){

            if(obj != null) {

                if (as.equalsIgnoreCase("y")) {
                    obj.setStatus(Status.DONE);
                }

                task.remove(obj);
                return obj;
            }

           return null;


    }



    protected  ArrayList<TaskObj> listByStatus(String qq){

        ArrayList<TaskObj> arr = new ArrayList<>();

        for (TaskObj obj : task) {
            String stat = obj.getStatus().toString();
            if (stat.equalsIgnoreCase(qq)) {
                arr.add(obj);
            }
        }
        return arr;
    }

    protected  TaskObj changeStatus(String name, String st) {
        TaskObj obj = searchData(name);
        if(obj != null) {
            if (st.equalsIgnoreCase("initial"))
                obj.setStatus(Status.INITIAL);
            if (st.equalsIgnoreCase("inprogress")) {
                obj.setStatus(Status.INPROGRESS);
                obj.setDate(new Date());
            }
            if (st.equalsIgnoreCase("done")) {
                obj.setStatus(Status.DONE);
                obj.setDate(new Date());
            }
        }
        return obj;
    }

    protected  TaskObj updateDescription(String name, String updatedDesc) {
        TaskObj obj = searchData(name);
        if(updatedDesc != null){
            obj.setDesc(updatedDesc);
          return  changeStatus(name,"inprogress");
        }
        else{
            return null;
        }
    }

}
