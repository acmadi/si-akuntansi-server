package com.devteam.accounting.spring;


import org.springframework.context.annotation.*;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EnableTransactionManagement
@ComponentScan({"com.devteam.accounting.dao", "com.devteam.accounting.service"})
@ImportResource({"classpath:spring/beans.xml.bak"})
public class PersistenceConfig {

    public PersistenceConfig() {
        super();
    }


}