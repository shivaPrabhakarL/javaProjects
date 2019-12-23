package com.shivaPrabhakar;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import  java.io.IOException;
import java.util.Date;

class Taskmanagement extends Taskobj {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    protected static ArrayList<Taskobj> task = new ArrayList<Taskobj>();

    protected static boolean isNumeric(String q){
        try{
            int w = Integer.parseInt(q);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    protected static void addData()throws IOException{

        System.out.println("enter task name");
        String name = br.readLine();

        System.out.println("enter description");
        String des = br.readLine();
        Taskobj to = new Taskobj();
        to.setName(name);
        to.setDesc(des);
        to.setDate(new Date());
        task.add(to);


    }

    protected static void listData()throws IOException{

        System.out.println(task);
    }

    protected static Taskobj searchData()throws IOException{

            System.out.println("enter task name or number");
            String nam = br.readLine();
            if(!isNumeric(nam)) {
                for (Taskobj obj : task) {
                    String qury = obj.getName();
                    if (qury.equalsIgnoreCase(nam)) {
                        System.out.println("task found");
                        System.out.println(obj);
                        return obj;
                    }
                }
            }
            else {
                System.out.println(task.get(Integer.parseInt(nam)));
                return (task.get(Integer.parseInt(nam)));
            }
        return null;
    }

    protected static void deleteData()throws IOException{
            boolean qw = false;
            Taskobj obj = searchData();
            System.out.println("if task is completed enter (y) else (n)");
            String as = br.readLine();
            if(as.equalsIgnoreCase("y") )
                obj.setStatus(Status.DONE);
            System.out.println(obj);
            task.remove(obj);
            qw = true;


            if(qw == false){
                System.out.println("Task not found.");
            }


    }

      protected static  String menu ()throws IOException{
        System.out.println("\nenter function");
        System.out.println("Add,  List,  Search,  Delete,  Quit, ListByStatus, ChangeStatus");
        String s = br.readLine();
        return s;
    }

    protected static ArrayList<Taskobj> listByStatus()throws IOException{
        System.out.println("\nenter a status code to filter");
        ArrayList<Taskobj> arr = new ArrayList<Taskobj>();
        String qq = br.readLine();
        for(Taskobj obj : task){
            String stat = obj.getStatus().toString();
            if(stat.equalsIgnoreCase(qq)){
                arr.add(obj);
            }
        }
        return arr;
    }

    protected static void changeStatus(){
        Taskobj obj = searchData();
        System.out.println("enter status (INITIAL | INPROGRESS | DONE)");
        String st = br.readLine();
        if(st.equalsIgnoreCase("initial"))
            obj.setStatus(Status.INITIAL);
        if(st.equalsIgnoreCase("inprogress"))
            obj.setStatus(Status.INPROGRESS);
        if(st.equalsIgnoreCase("done"))
            obj.setStatus(Status.DONE);
        System.out.println(obj);
    }
}
