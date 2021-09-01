package com.getir.order;

import com.getir.order.init.LoadInitialData;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class OrderApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(OrderApplication.class, args);
        LoadInitialData loadInitialData = context.getBean(LoadInitialData.class);
        loadInitialData.loadOrderList();
    }

}
