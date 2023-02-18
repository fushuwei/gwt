package com.mochousoft.gwt.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class GWTApplication {

    public static void main(String[] args) {
        SpringApplication.run(GWTApplication.class, args);
    }

}
