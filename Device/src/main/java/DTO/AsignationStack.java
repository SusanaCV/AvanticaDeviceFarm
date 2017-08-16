/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DTO;

import Controller.ConectionDB;
import java.util.Date;

/**
 *
 * @author susan
 */
public class AsignationStack {

   
    private Asignation asignation;
    private String lastUserName;
    private boolean send = false;

    public AsignationStack( Asignation asignation) {
       
      
        this.asignation = asignation;
        this.lastUserName = "";
    }

   
    public int getTime() {
        return ((this.asignation.getTime() * 3600000) - (int) (new Date().getTime() -Long.parseLong(this.asignation.getStartDate(), 10) )) / 1000;
    }

  

    public String getLastUserName() {
        return this.lastUserName;
    }

    public void next() {
        this.lastUserName = this.asignation.getName();
        //load
        this.send = false;
        
        this.asignation=ConectionDB.nextAsignation(asignation.getIdDevice());
        if(this.asignation==null){
            return;
        }
        ConectionDB.Take(this.asignation.id, this.asignation.idDevice);      
     
    }

    public boolean isSend() {
        return send;
    }

    public void setSend(boolean send) {
        this.send = send;
    }

    public Asignation getAsignation() {
        return asignation;
    }

    public void setAsignation(Asignation asignation) {
        this.asignation = asignation;
    }

   

   
   
    
}
