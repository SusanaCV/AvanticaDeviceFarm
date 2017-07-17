package com.WS.Device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DeviceApplication {

	public static void main(String[] args) {
            adClass ad = new adClass();
        

        ad.getUserBasicAttributes(ad.pool("a"), ad.ADConeection());
        ad.getUserBasicAttributes(ad.pool("e"), ad.ADConeection());
        ad.getUserBasicAttributes(ad.pool("i"), ad.ADConeection());
        ad.getUserBasicAttributes(ad.pool("o"), ad.ADConeection());
        ad.getUserBasicAttributes(ad.pool("u"), ad.ADConeection());
		SpringApplication.run(DeviceApplication.class, args);
	}
}
