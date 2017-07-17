/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DTO.Device;
import DTO.Feactures;
import DTO.Request;
import DTO.User;
import com.WS.Device.DeviceApplication;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.directory.Attribute;
import org.springframework.boot.SpringApplication;

/**
 *
 * @author susan
 */
public class ConectionDB {

    private static Connection connect;
    private static Statement statement;

    public static void open() {
        try {
            // This will load the MySQL driver, each DB has its own driver
            Class.forName("com.mysql.jdbc.Driver");
            // Setup the connection with the DB

            connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/devicefarmdb", "root", null);
            // Statements allow to issue SQL queries to the database, "root"
            statement = connect.createStatement();
            // Result set get the result of the SQL query

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public static void close() {
        try {
            connect.close();
        } catch (SQLException ex) {
            Logger.getLogger(ConectionDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

   

    public static User login(String Email, String Password) {
        open();
        User user = null;
        try {
            ResultSet resultSet = statement.executeQuery(
                    "select * from users where Email ='" + Email + "' and Password ='" + Password + "'");
            //INSERT INTO users( Name, Usuario_Skype, Email) VALUES ('Jimmy','Jxskype','f@valverde')
            while (resultSet.next()) {
                user = new User(resultSet.getInt("ID_User"), resultSet.getInt("Permission"), resultSet.getString("Name"), resultSet.getString("Usuario_Skype"), resultSet.getString("Email"));
                System.out.println(resultSet.getString("Name"));
            }
        } catch (Exception e) {

        }
        close();
        return user;
    }

    public static boolean NewDevice(String code, String img, String brand, String model, String os, String version, String ip, String mac) {
        open();
        try {
            statement.executeUpdate("INSERT INTO devices( OperativeSystem, Brand, Model, Code, Version, MAC_Address, IP, Image) VALUES('" + os + "','" + brand + "','" + model + "','" + code + "','" + version + "','" + mac + "','" + ip + "','" + img + "')");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        close();
        return true;
    }

    public static ArrayList<Object> getDevicesList() {

        open();
        ArrayList<Object> list = new ArrayList();
        ArrayList<Request> stack;
        try {
            ResultSet resultSet = statement.executeQuery(
                    "select * from devices");
          
            while (resultSet.next()) {
                int num = resultSet.getInt("Id_Device");
                Statement statement2  = connect.createStatement();
                ResultSet resultSetStack = statement2.executeQuery("call getStack(" + num + ");");
                stack = new ArrayList();
                while (resultSetStack.next()) {
                    stack.add(new Request(resultSetStack.getInt("Id_Asignation"),resultSetStack.getString("Name"), resultSetStack.getInt("Time"), resultSetStack.getString("Priority")));
                }

                list.add(new Device(resultSet.getInt("Id_Device"), resultSet.getString("Name"), resultSet.getString("Image"), resultSet.getString("Status"),
                        new Feactures(resultSet.getString(
                                "OperativeSystem"),
                                resultSet.getString("Brand"),
                                resultSet.getString("Model"),
                                resultSet.getString("Code"),
                                resultSet.getString("Version"),
                                resultSet.getString("MAC_Address"),
                                resultSet.getString("IP")),
                        stack));
            }
        } catch (Exception e) 
        {
            e.printStackTrace();

        }
        close();

        return list;
    }

    public static boolean Delete(int id) {
        open();
        System.out.println(id + "dsfsdf");
        try {
            statement.executeUpdate("DELETE FROM devices WHERE id_Device =" + id);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        close();
        return true;
    }

    public static boolean Asignation(int idUser, int idDevice, int time, long date, String pending, String priority) {
        open();
        try {
            statement.executeUpdate("INSERT INTO asignation("
                    + " Id_User, Id_Device, Time, Status, Date, Priority) VALUES "
                    + "("+idUser+","+idDevice+","+time+",'"+pending+"','"+date+"','"+priority+"')");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        close();
        return true;
    }

    public static boolean Give(int id) {
     open();
        try {
            statement.executeUpdate("Update asignation set Status='Given' where Id_Asignation="+id);
            Statement statement2  = connect.createStatement();
             ResultSet result= statement2.executeQuery(
                     "SELECT Id_Device,Name from asignation as a,users as u "
                             + "where a.Id_User=u.Id_User and a.Id_Asignation="+id);
           result.next();
             Statement statement3  = connect.createStatement();
             statement3.executeUpdate("Update Devices set Status='In use', Name='"+result.getString("Name")+
                     "' where Id_Device="+result.getInt("Id_Device"));
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        close();
        return true;
    }

    public static boolean free(int id) {
         open();
        try {
            statement.executeUpdate("Update Devices set Status='Available', Name='' where Id_Device="+id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        close();
        return true;
    }

    public static void addUser(String name, String email, int permission, String skype, String password) {
        open();
        try {
            statement.executeUpdate(
                    "INSERT INTO users( Permission, Name, Usuario_Skype, Email, Password) "
                            + "VALUES ("+permission+",'"+name+"','"+skype+"','"+email+"','"+password+"')");
        } catch (Exception e) {
            
            
        }
        close();
    }
}
