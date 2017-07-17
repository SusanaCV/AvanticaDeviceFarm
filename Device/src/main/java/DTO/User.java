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
public class User {
    private int ID;
    private int Permission;
    private String Name;
    private String Skype;
    private String Email;

    public User(int ID, int Permission, String Name, String Skype, String Email) {
        this.ID = ID;
        this.Permission = Permission;
        this.Name = Name;
        this.Skype = Skype;
        this.Email = Email;
    }

    public int getPermision() {
        return Permission;
    }

    public void setPermision(int permision) {
        this.Permission = permision;
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

    public String getSkype() {
        return Skype;
    }

    public void setSkype(String Skype) {
        this.Skype = Skype;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String Email) {
        this.Email = Email;
    }
    
}
