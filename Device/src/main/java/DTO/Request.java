/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

/**
 *
 * @author susan
 */
public class Request {

    String name;
    int time;
    int id;
    String priority;
    String resquetDate;

    public Request(String name, int time, int id, String priority, String resquetDate) {
        this.name = name;
        this.time = time;
        this.id = id;
        this.priority = priority;
        this.resquetDate = resquetDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPriority() {
        return priority;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public String getResquetDate() {
        return resquetDate;
    }

    public void setResquetDate(String resquetDate) {
        this.resquetDate = resquetDate;
    }

 

}
