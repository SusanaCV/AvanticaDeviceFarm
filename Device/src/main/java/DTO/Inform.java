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
public class Inform {
    String Name;
    ArrayList<Integer> Data;

    public Inform(String Name) {
        this.Name = Name;
        this.Data = new ArrayList<>();
        for(int index=0 ;index<12;index++){
           Data.add(0);
        }
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public ArrayList<Integer> getData() {
        return Data;
    }

    public void setData(ArrayList<Integer> Data) {
        this.Data = Data;
    }
    
}
