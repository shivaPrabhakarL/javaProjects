package com.shivaPrabhakar;

import java.io.IOException;

public class Main {

    public static void main(String[] args)throws IOException {


        while (true) {
            String s = Taskmanagement.menu();
            if (s.equals("Add") || s.equals("add"))
                Taskmanagement.addData();
            if (s.equals("list") || s.equals("List"))
                Taskmanagement.listData();
            if (s.equals("search") || s.equals("Search"))
                Taskmanagement.searchData();
            if (s.equals("delete") || s.equals("Delete") )
                Taskmanagement.deleteData();
            if (s.equals("Quit") || s.equals("quit"))
                System.exit(0);
        }
    }
}
