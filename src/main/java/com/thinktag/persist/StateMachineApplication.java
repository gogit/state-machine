package com.thinktag.persist;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;

@SpringBootApplication
public class StateMachineApplication {

    public static void main(String[] args) {
        ApplicationContext ctx = SpringApplication.run(StateMachineApplication.class, args);
        //debug(ctx);
    }

    private static void debug(ApplicationContext ctx) {
        String[] beanNames = ctx.getBeanDefinitionNames();
        Arrays.sort(beanNames);
        for (String beanName : beanNames) {
            System.out.println("!!!!!!!!!!" + beanName);
        }
    }

}
