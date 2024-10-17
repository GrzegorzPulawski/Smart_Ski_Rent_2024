package com.smart_ski_rent_ver1_2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
  public class Application {

    public static void main(String[] args)

    {
        System.setProperty("server.port", System.getenv("PORT") != null ? System.getenv("PORT") : "8080");
        SpringApplication.run(Application.class, args);}

}
