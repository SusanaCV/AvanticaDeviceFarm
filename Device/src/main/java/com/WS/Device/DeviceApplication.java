package com.WS.Device;

import Controller.AsignationHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeviceApplication {

	public static void main(String[] args) {
          AsignationHandler.startAsignationHandler();
          SpringApplication.run(DeviceApplication.class, args);
	}
}
