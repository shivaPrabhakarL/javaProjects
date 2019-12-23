package com.shivaPrabhakar;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import  java.io.IOException;
import java.util.Date;

public class Taskmanagement extends Taskobj {

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    private static ArrayList<Taskobj> task = new ArrayList<Taskobj>();

    public static boolean isNumeric(String q){
        try{
            int w = Integer.parseInt(q);
            return true;
        }
        catch (Exception e){
            return false;
        }
    }
    public static void addData()throws IOException{

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

    public static void listData()throws IOException{

//            for (Taskobj obj : task) {
//                System.out.println(obj);
//            }
        System.out.println(task);
    }

    public static Taskobj searchData()throws IOException{

            System.out.println("enter task name or number");
            String nam = br.readLine();
            if(!isNumeric(nam)) {
                for (Taskobj obj : task) {
                    if (obj.getName().equals(nam)) {
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

    public static void deleteData()throws IOException{

            System.out.println("enter task name");
            boolean qw = false;
            String nam = br.readLine();
            for(Taskobj obj:task) {
                if (obj.getName().equals(nam)) {
                    System.out.println("task found");
                    System.out.println("if task is completed enter (y) else (n)");
                    String as = br.readLine();
                    if(as.equals("y") || as.equals("Y"))
                        obj.setStatus(Status.DONE);
                    System.out.println(obj);
                    task.remove(obj);
                    qw = true;
                    break;
                }
            }
            if(qw == false){
                System.out.println("Task not found.");
            }


    }

    public static String menu ()throws IOException{
        System.out.println("\nenter function");
        System.out.println("Add,  List,  Search,  Delete,  Quit");
        String s = br.readLine();
        return s;
    }

//    public static void main(String[] args)throws IOException{
//
//        System.out.println("enter function");
//        System.out.println("Add,  List,  Search,  Delete,  Quit");
//        String s = br.readLine();
//
//        while(true) {
//            addData(s,task);
//            listData(s,task);
//            searchData(s,task);
//            deleteData(s,task);
//            if(s.equals("Quit")|| s.equals("quit"))
//                System.exit(0);
//            s = br.readLine();
//        }
//
//    }
}
