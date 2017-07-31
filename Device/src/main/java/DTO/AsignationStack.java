/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.Date;

/**
 *
 * @author susan
 */
public class AsignationStack {

    private int remainTime;
    private int hours;
    private long startTime;
    private User user;
    private boolean status;
    private String lastUserName;
    private boolean send = false;
    private int asignationdID;
    private int deviceID;

    public AsignationStack(int deviceID, int asignationdID, int hours, long startTime, User user, boolean status, String lastUserName) {

        this.asignationdID = asignationdID;
        this.deviceID = deviceID;
        this.remainTime = 999;
        this.hours = hours;
        this.startTime = startTime;
        this.user = user;
        this.status = status;
        this.lastUserName = lastUserName;
    }

    public void updateTime() {
        this.remainTime = ((hours * 1000) - (int) (new Date().getTime() - this.startTime)) / 1000;
    }

    public int getTime() {
        return this.remainTime;
    }

    public User getCurrentUser() {
        return this.user;
    }

    public boolean getStatus() {
        return this.status;
    }

    public String getLastUserName() {
        return this.lastUserName;
    }

    public void next() {
        this.lastUserName = this.user.getName();
        this.user = new User(2, 0, "ss", "skype", "ss@");
        this.send = false;
        //conxion save end time
        // accepted by user
        this.status = true;
        this.remainTime = 5;
        this.startTime = new Date().getTime();
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

}
