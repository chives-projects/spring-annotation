package com.csc.demo1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.Scanner;

@SpringBootApplication
public class Demo1Application {
    public static void main(String[] args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(Demo1Application.class, args);

        Scanner scanner = new Scanner(System.in);
        while(true){
            if ("exit".equals(scanner.nextLine())){
                break;
            }
        }

        SpringApplication.exit(applicationContext);

    }
}
