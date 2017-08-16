/*
 * To chane this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DTO.Asignation;
import DTO.AsignationStack;
import DTO.Device;
import DTO.User;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author susan
 */
public class AsignationHandler {

    static ArrayList<AsignationStack> STACK = new ArrayList<>();

    public static void startAsignationHandler() {

        updateStack();

        Thread updateAsignation = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        System.out.println("waint");
                        Thread.sleep(300000);//  5 minutes 

                        updateStack();
                        STACK.forEach((object) -> {
                            if (object.getAsignation() != null) {
                                System.out.println(object.getTime());
                                if (!object.getAsignation().getStartDate().equals("0")) {

                                    if (object.getAsignation().getStatus().equals("Given") && !object.isSend() && object.getTime() <= 300000 && object.getTime() > 0) {
                                        sendMail(object.getAsignation(), "need more time?");
                                        object.setSend(true);
                                        System.out.println("send mail to " + object.getAsignation().getName() + " need more time?");

                                    } else if (object.getTime() <= 0) {
                                        object.next();
                                     

                                        if (object.getAsignation().getStatus().equals("Pending")) {
                                            sendMail(object.getAsignation(), "device freed by " + object.getLastUserName());
                                            System.out.println("send mail to " + object.getAsignation().getName() + " you have 5 min to claim device, last user " + object.getLastUserName());
                                        }

                                    } 
                                    if (object.getTime() <= 0 && object.getAsignation().getStatus().equals("Take")) {
                                        System.out.println("free time out");
                                        ConectionDB.free(object.getAsignation().getIdDevice(), object.getAsignation().getId());

                                    }
                                } else {
                                   
                                        System.out.println("Not started");
                                        sendMail(object.getAsignation(), "device available");
                                        object.next();
                                       
                                   
                                }
                            }
                        });
                    } catch (Exception ex) {
                        Logger.getLogger(AsignationHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });
        updateAsignation.start();
        System.out.println("start");
    }

    private static void sendMail(Asignation asignation, String content) {
        SendHTMLEmail.sendMail(asignation.getEmail(), asignation.getName(), content);
    }

    private static void updateStack() {
        System.out.println("updating stack");
        STACK = new ArrayList<>();
        ArrayList<Device> list = ConectionDB.getDevicesList();
        for (Device device : list) {
            Asignation asignation = ConectionDB.nextAsignation(device.getID());
            STACK.add(new AsignationStack(asignation));
        }
        System.out.println("complete");
    }
}
