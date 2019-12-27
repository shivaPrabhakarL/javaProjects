package com.shivaPrabhakar;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;


public class DataBase implements TaskRepository {
    Random rand = new Random();
    SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    // ResultSet results = stmt.executeQuery("SELECT * FROM TaskList");
    List<TaskObj> task;
    private static Connection con;
    private static Statement stmt;
    private static String query;


    private  boolean isNumeric(String q) {
        try {
            Integer.parseInt(q);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public void dbConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/testdb", "testuser", "Pramati@123");
            stmt = con.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public DataBase() {
        dbConnection();

    }

    public void createAndUpdateTable(TaskObj to) throws SQLException {
        String date = format.format(to.getDate());
        try {
           // stmt.executeUpdate("truncate table TaskList");
            query = "create table TasksList(" +
                    "Id INT PRIMARY KEY, Name VARCHAR(255),Description VARCHAR(255), Status VARCHAR(255), DDate DATE )";
            stmt.executeUpdate(query);
            query = "insert into TaskList values(" + to.getId() + ",'" + to.getName() + "','" + to.getDesc() + "', '"+date+"','" + to.getStatus().toString() + "' )";
            stmt.executeUpdate(query);
        } catch (Exception e) {
            //System.out.println("hia");
            query = "insert into TaskList values(" + to.getId() + ",'" + to.getName() + "','" + to.getDesc() + "', '"+date+"','" + to.getStatus().toString() + "' )" ;
            System.out.println(query);
            stmt.executeUpdate(query);
        }
    }

    //public

    public List<TaskObj> returnList(ResultSet results) throws SQLException, ParseException {
       task = new ArrayList<>();
        while(results.next()) {
            TaskObj to = new TaskObj();
            String data = results.getString(1);
           // System.out.println("Id " + " : " + data);
            to.setId(Integer.parseInt(data));
           // System.out.println(results.next());
            data = results.getString("Name");
           // System.out.println("Name"  + " : " + data);
            to.setName(data);
            data = results.getString("Description");
           // System.out.println("Description"  + " : " + data);
            to.setDesc(data);
            data = results.getString("Ddate");
           // System.out.println("Date"  + " : " + data);
            to.setDate(format.parse(data));
            data = results.getString("Status");
            //System.out.println("Status"  + " : " + data+"\n");
            if (data.equalsIgnoreCase("initial"))
                to.setStatus(Status.INITIAL);
            if (data.equalsIgnoreCase("inprogress")) {
                to.setStatus(Status.INPROGRESS);
                to.setDate(new Date());
            }
            if (data.equalsIgnoreCase("done")) {
                to.setStatus(Status.DONE);
                to.setDate(new Date());
            }
            task.add(to);
        }
        return task;
    }

    private TaskObj returnObject(ResultSet results)throws SQLException, ParseException {

        TaskObj to = new TaskObj();
        while(results.next()) {

            String data = results.getString(1);
            //System.out.println("Id " + " : " + data);
            to.setId(Integer.parseInt(data));
            // System.out.println(results.next());
            data = results.getString("Name");
           // System.out.println("Name" + " : " + data);
            to.setName(data);
            data = results.getString("Description");
            //System.out.println("Description" + " : " + data);
            to.setDesc(data);
            data = results.getString("Ddate");
            //System.out.println("Date" + " : " + data);
            to.setDate(format.parse(data));
            data = results.getString("Status");
            System.out.println("Status" + " : " + data + "\n");
            if (data.equalsIgnoreCase("initial"))
                to.setStatus(Status.INITIAL);
            if (data.equalsIgnoreCase("inprogress")) {
                to.setStatus(Status.INPROGRESS);
                to.setDate(new Date());
            }
            if (data.equalsIgnoreCase("done")) {
                to.setStatus(Status.DONE);
                to.setDate(new Date());
            }
        }
        return to;
    }


    @Override
    public TaskObj addTask(String name, String des, String date) throws ParseException, SQLException {
        TaskObj to = new TaskObj();
        to.setName(name);
        to.setDesc(des);
        to.setId(rand.nextInt(10000));
        to.setDate(format.parse(date));
        createAndUpdateTable(to);
        return to;
    }

    @Override
    public TaskObj updateTask(String name, String updatedDesc) throws SQLException, ParseException {
        changeStatus(name, "INPROGRESS");
        if(!isNumeric(name)) {
            query = "update TaskList set Description = '"+updatedDesc+"' where Name = '"+name+"'";
        }
        else{
            query = "update TaskList set Description = '"+updatedDesc+"' where Id = '"+name+"'";
        }
        stmt.executeUpdate(query);

        return searchData(name);
    }

    @Override
    public TaskObj delete(String name) throws SQLException, ParseException {
        if(!isNumeric(name)) {
            query = "delete FROM TaskList where Name ='"+name+"'";
            //showData(results);
        }
        else{
            query ="delete FROM TaskList where Id ='"+name+"'" ;
            //showData(results);
        }
        stmt.executeUpdate(query);
        return searchData(name);
    }

    @Override
    public TaskObj findById(Integer taskId) throws SQLException, ParseException {
        query = "SELECT * FROM TaskList where Id ='"+String.valueOf(taskId)+"'";
        ResultSet results = stmt.executeQuery(query);
        return returnObject(results);
    }

    @Override
    public List<TaskObj> findAll() throws SQLException, ParseException {
        query = "SELECT * FROM TaskList";
        ResultSet results = stmt.executeQuery(query);

        return returnList(results);
    }

    @Override
    public List<TaskObj> findAllByStatus(String status) throws SQLException, ParseException {
        query = "SELECT * FROM TaskList where Status ='"+status+"'";
        ResultSet results = stmt.executeQuery(query);

        return returnList(results);
    }

    @Override
    public TaskObj searchData(String name) throws SQLException, ParseException {
        if(!isNumeric(name)) {
            query = "SELECT * FROM TaskList where Name ='"+name+"'";
            ResultSet results = stmt.executeQuery(query);
            return returnObject(results);
            }

        else if(isNumeric(name)){
             return findById(Integer.parseInt(name));
        }
        return null;
    }

    @Override
    public TaskObj changeStatus(String name, String status) throws SQLException, ParseException {
        if(!isNumeric(name)) {
            query = "update TaskList set Status = '" + status + "' where Name ='" + name + "'";
        }
        else{
            query = "update TaskList set Status = '" + status + "' where Id = '" + name + "'";
        }
        stmt.executeUpdate(query);
        //showData(results);

        return searchData(name);
    }

    @Override
    public boolean checkData() {
        return true;
    }

    @Override
    public List<TaskObj> sortByDate() throws SQLException, ParseException {
        query = "SELECT * FROM TaskList order by Ddate";
        ResultSet rs = stmt.executeQuery(query);

        return returnList(rs);
    }

    @Override
    public List<TaskObj> getPendingTasks() throws SQLException, ParseException {
        query = "SELECT * FROM TaskList where Status !='DONE'";
        ResultSet results =  stmt.executeQuery(query);

        return returnList(results);
    }

    @Override
    public List<TaskObj> getTodayTasks() throws ParseException, SQLException {
        String date =  format.format(new Date());
        query = "select * from TaskList where Ddate = '"+date+"'";
        ResultSet results = stmt.executeQuery(query);

        return returnList(results);
    }
}
