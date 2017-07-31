/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import DTO.AsignationStack;
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

    public static void main(String[] args) {
        STACK.add(new AsignationStack(1, 1, 10, new Date().getTime(), new User(1, 0, "jj", "skype", "juanito"), true, ""));
        //    STACK.add(new Request("item2", 2, 0, "high", "12344"));
        //    STACK.add(new Request("item3", 3, 0, "high", "12344"));
        Thread updateAsignation = new Thread(new Runnable() {
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);// to 5 minutes currect 1 second
                    } catch (InterruptedException ex) {
                        Logger.getLogger(AsignationHandler.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    STACK.forEach((object) -> {
                        if (object.getStatus()) {
                            object.updateTime();
                            System.out.println(object.getTime());
                            if (!object.isSend() && object.getTime() <= 5 && object.getTime() > 0) {
                                sendMail(object.getCurrentUser(), "need more time?");
                                object.setSend(true);
                                System.out.println("send mail to " + object.getCurrentUser().getName() + " need more time?");
                            } else if (object.getTime() <= 0) {
                                object.next();
                                sendMail(object.getCurrentUser(), "device freed by " + object.getLastUserName());
                                System.out.println("send mail to " + object.getCurrentUser().getName() + " you have 5 min to claim device, last user " + object.getLastUserName());

                            }

                        }
                    });

                }
            }

        });
        updateAsignation.start();
        System.out.println("start");
    }

    private static void sendMail(User currentUser, String string) {

    }
}
