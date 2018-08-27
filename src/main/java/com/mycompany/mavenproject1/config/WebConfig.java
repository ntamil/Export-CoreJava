/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.config;

import javax.sql.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

/**
 *
 * @author tam
 */
public class WebConfig {

    public static final String DRIVER = "com.mysql.jdbc.Driver";

    public static final String URL = "jdbc:mysql://localhost:3306/tamil_20_08_2018";

    public static final String USERNAME = "root";

    public static final String PASSWORD = "root";

    public DataSource getDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/tamil_20_08_2018");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
        return dataSource;
    }
}
