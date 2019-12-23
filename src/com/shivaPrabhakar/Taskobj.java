package com.shivaPrabhakar;
import java.util.Date;
public class Taskobj{

    enum Status{
        INITIAL,
        INPROGRESS,
        DONE
    }

    String name,desc;
    Status status;
    Date date;
    Taskobj(String s,String de){
        date = new Date();
        this.name = s;
        this.desc = de;
        this.status  = Status.INITIAL;
       // this.status = s1;
    }

    @Override
    public String toString() {
        return  ('\n'+
                "name : " + name + '\n' +
                "desc : " + desc + '\n' +
                "status : " + status + '\n' +
                "date : " + date )
                ;
    }

}
