package com.shivaPrabhakar;
import java.util.Date;
public class Taskobj{

    enum Status{
        INITIAL,
        INPROGRESS,
        DONE
    }
    Taskobj(){
        this.setStatus(Status.INITIAL);
    }
    private String name,desc;
    private Status status;
    private Date date;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

//    Taskobj(String s, String de){
//        date = new Date();
//        this.name = s;
//        this.desc = de;
//        this.status  = Status.INITIAL;
//       // this.status = s1;
//    }

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
