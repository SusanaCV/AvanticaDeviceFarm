/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.WS.Device;

import Controller.ConectionDB;
import DTO.User;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author susan
 */
@RestController

    @CrossOrigin(origins = "*")
public class EndPointController {

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/device", method = RequestMethod.GET)
    public  @ResponseBody  ResponseEntity<ArrayList>  Device() {
        ArrayList<Object> list = ConectionDB.getDevicesList();
       
         return ResponseEntity.ok(list);
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/request", method = RequestMethod.POST)

    public  @ResponseBody ResponseEntity<ArrayList> request(
            @RequestParam(value = "idUser", defaultValue = "-1") int idUser,
            @RequestParam(value = "idDevice", defaultValue = "-1") int idDevice,
            @RequestParam(value = "time", defaultValue = "0") int time,
            @RequestParam(value = "priority", defaultValue = "") String priority
    ) {
        if (idUser==-1 ||idDevice==-1 || time < 1 || priority.equals("")) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
       
         if(!ConectionDB.Asignation(idUser, idDevice,time,new Date().getTime(),"Pending",priority)){
           return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
       }
        return ResponseEntity.ok(null);
    }

     @CrossOrigin(origins = "*")
    @RequestMapping(value = "/give", method = RequestMethod.POST)

    public  @ResponseBody ResponseEntity<ArrayList> give(
            @RequestParam(value = "id", defaultValue = "-1") int id
    ) {
        if (id==-1 ) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
       
         if(!ConectionDB.Give(id)){
           return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
       }
        return ResponseEntity.ok(null);
    }
     @CrossOrigin(origins = "*")
    @RequestMapping(value = "/free", method = RequestMethod.POST)

    public  @ResponseBody ResponseEntity<ArrayList> free(
            @RequestParam(value = "id", defaultValue = "-1") int id
    ) {
        if (id==-1 ) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
       
         if(!ConectionDB.free(id)){
           return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
       }
        return ResponseEntity.ok(null);
    }
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/login",params = {"username", "password"}, method = RequestMethod.GET)
    public  @ResponseBody ResponseEntity<Object> login(
            @RequestParam(value = "username", defaultValue = "") String username,
            @RequestParam(value = "password", defaultValue = "") String password
    ) {
        System.out.println(username + "/" + password);
        if (username.equals("") || password.equals("")) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
      //  adClass ad = new adClass();
      //  ad.getUserBasicAttributes(username,password, ad.ADConeection(username,password));        
        
       User response = ConectionDB.login(username);
       if(response==null){
         
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
                    }     
        return ResponseEntity.ok(response);
    }
    
    
    
    @CrossOrigin(origins = "*")
    @RequestMapping(value = "/NewDevice", method = RequestMethod.POST)
    public  @ResponseBody ResponseEntity<Object> NewDevice(
            @RequestParam(value = "code", defaultValue = "") String code,
            @RequestParam(value = "img", defaultValue = "") String img,
            @RequestParam(value = "brand", defaultValue = "") String brand,
            @RequestParam(value = "model", defaultValue = "") String model,
            @RequestParam(value = "os", defaultValue = "") String os,
            @RequestParam(value = "version", defaultValue = "") String version,
            @RequestParam(value = "ip", defaultValue = "") String ip,
            @RequestParam(value = "mac", defaultValue = "") String mac
    ) {
      
        if (code.equals("") || img.equals("")|| brand.equals("")
                || model.equals("")|| os.equals("")|| version.equals("")
                || ip.equals("")|| mac.equals("")) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
      
       if(!ConectionDB.NewDevice(code, img,brand,model,os,version,ip,mac)){
           return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
       }
        return ResponseEntity.ok("");
    }
    
    
       @CrossOrigin(origins = "*")
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public  @ResponseBody ResponseEntity<Object> delete(
            @RequestParam(value = "id", defaultValue = "-1") int id
    ) {
           System.out.println(1);
        if (id==-1) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        System.out.println(2);
       if(!ConectionDB.Delete(id)){
           return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body(null);
       }
       System.out.println(3);
        return ResponseEntity.ok("");
    }
}
