/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DTO.Device;
import DTO.Feactures;
import DTO.Asignation;
import DTO.Inform;
import DTO.Informs;
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

    public static User login(String Email) {
        open();
        User user = null;
        try {
            ResultSet resultSet = statement.executeQuery(
                    "select * from users where Email ='" + Email + "'");
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

    public static ArrayList<Device> getDevicesList() {
        open();//Conexion con base de datos
        ArrayList<Device> list = new ArrayList();
        ArrayList<Asignation> stack;
        try {
            ResultSet resultSet = statement.executeQuery(
                    "select * from devices");
            // Cargar informacion de device
            while (resultSet.next()) {
                int num = resultSet.getInt("Id_Device");
                Statement statement2 = connect.createStatement();
                ResultSet resultSetStack = statement2.executeQuery("call getStack(" + num + ");");
                stack = new ArrayList();
                while (resultSetStack.next()) {
                    stack.add(new Asignation(
                            resultSetStack.getInt("Id_Asignation"),
                            resultSetStack.getInt("Id_User"),
                            resultSetStack.getInt("Id_Device"),
                            resultSetStack.getString("Status"),
                            resultSetStack.getString("Name"),
                            resultSetStack.getString("Email"),
                            resultSetStack.getInt("Time"),
                            resultSetStack.getString("Priority"),
                            resultSetStack.getString("RequestDate"),
                            resultSetStack.getString("StartDate"),
                            resultSetStack.getString("EndDate")));
                }
                list.add(new Device(resultSet.getInt("Id_Device"), resultSet.getInt("AsignationID"), resultSet.getString("Image"), resultSet.getString("Status"),
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
        } catch (Exception e) {
            e.printStackTrace();

        }
        close();

        return list;
    }

    public static boolean Delete(int id) {
        open();
        try {
            statement.executeUpdate("DELETE FROM devices WHERE id_Device =" + id);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        close();
        return true;
    }

    public static boolean Asignation(int idUser, int idDevice, int time, long date, String pending, String priority, int month, int year) {
        open();
        try {
            statement.executeUpdate("INSERT INTO asignation("
                    + " Id_User, Id_Device, Time, Status, RequestDate, Priority,Month,Year) VALUES "
                    + "(" + idUser + "," + idDevice + "," + time + ",'" + pending + "','" + date + "','" + priority + "'," + month + "," + year + ")");

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        close();
        return true;
    }

    public static boolean add(int idDevice, int idAsignation) {
        open();
        try {
            statement.executeUpdate("Update asignation set Status='Given',StartDate ='" + new Date().getTime() + "' where Id_Asignation=" + idAsignation);
            Statement statement2 = connect.createStatement();
            statement2.executeUpdate("Update Devices set Status='In use' where Id_Device=" + idDevice);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        close();
        return true;
    }

    public static boolean Take(int idAsignation, int idDevice) {
        open();
        try {
            statement.executeUpdate("Update asignation set Status='Take' where Id_Asignation=" + idAsignation);
            Statement statement2 = connect.createStatement();
            statement2.executeUpdate("Update Devices set AsignationID=" + idAsignation + " where Id_Device=" + idDevice);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        close();
        return true;
    }

    public static boolean free(int idDevice, int idAsignation) {
        open();
        try {
            statement.executeUpdate("Update Devices set Status='Available', AsignationID=-1 where Id_Device=" + idDevice);

            Statement statement2 = connect.createStatement();
            statement2.executeUpdate("Update asignation set Status='Completed' , EndDate ='" + new Date().getTime() + "' where Id_Asignation=" + idAsignation);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        close();
        return true;
    }

    public static void addUser(String name, String email, int permission, String skype) {
        open();
        try {
            statement.executeUpdate(
                    "INSERT INTO users( Permission, Name, Usuario_Skype, Email, Password) "
                    + "VALUES (" + permission + ",'" + name + "','" + skype + "','" + email + "')");
        } catch (Exception e) {

        }
        close();
    }

    public static Asignation nextAsignation(int idDevice) {
        open();
        try {
            ArrayList<Asignation> stack;

            ResultSet resultSetStack = statement.executeQuery("call getStack(" + idDevice + ");");
            stack = new ArrayList();
            while (resultSetStack.next()) {
                stack.add(new Asignation(
                        resultSetStack.getInt("Id_Asignation"),
                        resultSetStack.getInt("Id_User"),
                        resultSetStack.getInt("Id_Device"),
                        resultSetStack.getString("Status"),
                        resultSetStack.getString("Name"),
                        resultSetStack.getString("Email"),
                        resultSetStack.getInt("Time"),
                        resultSetStack.getString("Priority"),
                        resultSetStack.getString("RequestDate"),
                        resultSetStack.getString("StartDate"),
                        resultSetStack.getString("EndDate")));
            }

            close();

            Asignation asignation = stack.get(0);//toma la primera asignation
            for (Asignation stackAsignation : stack) {// recorre toda la lista de asignations
                if (stackAsignation.getPriority().equals("High")) {//devuelve el primer elmento con priority Hing
                    return stackAsignation;
                }
                if (asignation.getPriority().equals("Low") && stackAsignation.getPriority().equals("Medium")) {
//si la primera asignation fue low y existe una medium la cambia por la primera Medium
                    asignation = stackAsignation;
                }
            }
            return asignation;
        } catch (Exception e) {
            close();
            return null;

        }
    }

    public static boolean UpdateDevice(int id, String code, String img, String brand, String model, String os, String version, String ip, String mac) {
        open();
        try {
            statement.executeUpdate("UPDATE devices set OperativeSystem='" + os + "',"
                    + " Brand='" + brand + "',"
                    + " Model='" + model + "',"
                    + " Code='" + code + "',"
                    + " Version='" + version + "',"
                    + " MAC_Address='" + mac + "',"
                    + " IP='" + ip + "',"
                    + " Image='" + img + " 'where Id_Device=" + id);

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        close();
        return true;
    }

    public static ArrayList<Informs> getInformList() {
        open();//Conexion con base de datos
        ArrayList<Informs> list = new ArrayList();
        int id;
        try {
            ResultSet resultSet = statement.executeQuery(
                    "SELECT year FROM informs GROUP BY Year");
            // Cargar informacion de device
            while (resultSet.next()) {
                Informs NI = new Informs(resultSet.getInt("year"), null);
                Statement statement2 = connect.createStatement();
                ResultSet resultSetStack = statement2.executeQuery(
                        "SELECT d.Brand,d.model,i.Id_Device FROM informs as i,devices as d WHERE i.year=" + NI.getYear() + " and i.id_device=d.Id_Device GROUP by Id_Device");

                ArrayList<Inform> series = new ArrayList();
                while (resultSetStack.next()) {
                    id=resultSetStack.getInt("Id_Device");
                    Inform NNI = new Inform(resultSetStack.getString("Brand") + " " + resultSetStack.getString("model"));
                    Statement statement3 = connect.createStatement();
                    ResultSet resultSet3 = statement3.executeQuery("SELECT Month, Count FROM informs WHERE year="+NI.getYear() +" and id_device="+id);
                    while (resultSet3.next()) {
                        NNI.getData().set(resultSet3.getInt("Month"), resultSet3.getInt("Count"));
                    }
                    series.add(NNI);
                }
                NI.setSeries(series);
                list.add(NI);
            }
        } catch (Exception e) {
            e.printStackTrace();

        }
        close();

        return list;
    }
}
