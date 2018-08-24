/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.config;

/**
 *
 * @author 006862
 */

 
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
//import com.spi.exportexcel.services.ExportService;
 

 
public class App {
 
    public static void main(String args[]) {
 
        AbstractApplicationContext context = new AnnotationConfigApplicationContext(ApplicationConfig.class);
        //ExportService exportService = (ExportService) context.getBean("exportService");
        
         context.close();
        
    }
    
}
