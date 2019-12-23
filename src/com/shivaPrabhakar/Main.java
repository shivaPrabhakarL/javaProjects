package com.shivaPrabhakar;

import java.io.IOException;

public class Main {

    public static void main(String[] args)throws IOException {


        while (true) {
            String s = Taskmanagement.menu();
            if (s.equalsIgnoreCase("Add"))
                Taskmanagement.addData();
            if (s.equalsIgnoreCase("list") )
                Taskmanagement.listData();
            if (s.equalsIgnoreCase("search") )
                Taskmanagement.searchData();
            if (s.equalsIgnoreCase("delete"))
                Taskmanagement.deleteData();
            if(s.equalsIgnoreCase("ListByStatus"))
                System.out.println(Taskmanagement.listByStatus());
            if(s.equalsIgnoreCase("changestatus"))
                Taskmanagement.changeStatus();
            if (s.equalsIgnoreCase("Quit"))
                System.exit(0);
        }
    }
}
