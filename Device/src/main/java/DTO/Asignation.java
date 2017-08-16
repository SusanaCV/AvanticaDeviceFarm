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
public class Asignation {

    int id;
    int idUser;
    int idDevice;
    String status;
    String name;
    String email;
    int time;
    String priority;
    String resquetDate;
    String startDate;
    String endDate;

    
    public Asignation(int id, int idUser, int idDevice, String status, String name, String email, int time, String priority, String resquetDate, String startDate, String endDate) {
        this.id = id;
        this.idUser = idUser;
        this.idDevice = idDevice;
        this.status = status;
        this.name = name;
        this.email = email;
        this.time = time;
        this.priority = priority;
        this.resquetDate = resquetDate;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public int getIdDevice() {
        return idDevice;
    }

    public void setIdDevice(int idDevice) {
        this.idDevice = idDevice;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
    
}
