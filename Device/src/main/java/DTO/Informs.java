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
public class Informs {
    int year;
    ArrayList<Inform> series;

    public Informs(int year, ArrayList<Inform> series) {
        this.year = year;
        this.series = series;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public ArrayList<Inform> getSeries() {
        return series;
    }

    public void setSeries(ArrayList<Inform> series) {
        this.series = series;
    }
    
    
}
