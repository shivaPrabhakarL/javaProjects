package com.shivaPrabhakar;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
//import java.util.HashMap;
import java.util.List;
import java.util.Random;

class TaskManager {
    Random rand = new Random();
    private List<TaskObj> task = new ArrayList<>();
    //private HashMap<Integer,TaskObj> taskObjHashMap =
    SimpleDateFormat format =new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
    private  boolean isNumeric(String q){
        try{
             Integer.parseInt(q);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }

    protected boolean checkData(){
        return (task.size() > 0);
    }

    protected  TaskObj addData(String name,String des,String date) throws ParseException {
        TaskObj to = new TaskObj();
        to.setName(name);
        to.setDesc(des);
        to.setId(rand.nextInt(10000));
        to.setDate(format.parse(date));
        task.add(to);
        return to;

    }

    protected  List<TaskObj>  listData(){
        if(task.size() > 0)
            return task;
        else
            return null;
    }

    protected  TaskObj searchData(String name) {
        if(task.size()>0){
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
            else
                return null;
        }
        return null;
    }

    protected  TaskObj deleteData(TaskObj obj, String que){

            if(obj != null) {

                if (que.equalsIgnoreCase("y")) {
                    obj.setStatus(Status.DONE);
                }

                task.remove(obj);
                return obj;
            }

           return null;


    }



    protected  List<TaskObj> listByStatus(String qq){

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
            if(!(obj.getStatus().toString()).equalsIgnoreCase("done"))
                return  changeStatus(name,"inprogress");
            else
                return obj;
        }
        else{
            return null;
        }
    }

}
