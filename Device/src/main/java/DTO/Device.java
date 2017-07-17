/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import java.util.ArrayList;

/**
 *
 * @author susan
 */
public class Device {

    int ID;
    String Name;
    String Src;
    String Status;
    Feactures feactures;
    ArrayList<Request> stack;

    public Device(int ID, String Name,String Src, String Status, Feactures feactures, ArrayList<Request> stack) {
        this.ID = ID;
        this.Name = Name;
        this.Src = Src;
        this.Status = Status;
        this.feactures = feactures;
        this.stack = stack;
    }

    public String getSrc() {
        return Src;
    }

    public void setSrc(String Src) {
        this.Src = Src;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String Status) {
        this.Status = Status;
    }

    public Feactures getFeactures() {
        return feactures;
    }

    public void setFeactures(Feactures feactures) {
        this.feactures = feactures;
    }

    public ArrayList<Request> getStack() {
        return stack;
    }

    public void setStack(ArrayList<Request> stack) {
        this.stack = stack;
    }


    

}
