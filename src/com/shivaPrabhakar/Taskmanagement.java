package com.shivaPrabhakar;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import  java.io.IOException;

public class Taskmanagement {



    public static boolean isNumeric(String q){
        try{
            int w = Integer.parseInt(q);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static void addData(String s,ArrayList<Taskobj> task)throws IOException{
        if (s.equals("Add") || s.equals("ADD") || s.equals("add")){
            System.out.println("enter task name");
            String name = br.readLine();

            System.out.println("enter description");
            String des = br.readLine();
             task.add(new Taskobj(name,des));

        }
    }

    public static void listData(String s,ArrayList<Taskobj> task)throws IOException{
        if (s.equals("List") || s.equals("list")){
            for (Taskobj obj : task) {
                System.out.println(obj);
            }
        }

    }

    public static void searchData(String s,ArrayList<Taskobj> task)throws IOException{
        if(s.equals("Search") || s.equals("search")){
            System.out.println("enter task name or number");
            String nam = br.readLine();
            if(!isNumeric(nam)) {
                for (Taskobj obj : task) {
                    if (obj.name.equals(nam)) {
                        System.out.println("task found");
                        System.out.println(obj);
                        break;
                    }
                }
            }
            else {

                System.out.println(task.get(Integer.parseInt(nam)));
            }
        }
    }

    public static void deleteData(String s,ArrayList<Taskobj> task)throws IOException{
        if(s.equals("Delete") || s.equals("delete")){
            System.out.println("enter task name");
            boolean qw = false;
            String nam = br.readLine();
            for(Taskobj obj:task) {
                if (obj.name.equals(nam)) {
                    System.out.println("task found");
                    System.out.println("if task is completed enter (y) else (n)");
                    String as = br.readLine();
                    if(as.equals("y") || as.equals("Y"))
                        obj.status = Taskobj.Status.DONE;
                    System.out.println(obj);
                    task.remove(obj);
                    qw = true;
                    break;
                }
            }
            if(qw == true)
                System.out.println("task deleted");
            else
                System.out.println("task not present");

        }
    }

    public static void main(String[] args)throws IOException{

        ArrayList<Taskobj> task = new ArrayList<Taskobj>();
        System.out.println("enter function");
        System.out.println("Add,  List,  Search,  Delete,  Quit");
        String s = br.readLine();

        while(true) {
            addData(s,task);
            listData(s,task);
            searchData(s,task);
            deleteData(s,task);
            if(s.equals("Quit")|| s.equals("quit"))
                System.exit(0);
            s = br.readLine();
        }

    }
}
