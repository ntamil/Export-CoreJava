/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mavenproject1.controller;

import com.mycompany.mavenproject1.util.MyTableInfo;
import com.mycompany.mavenproject1.config.WebConfig;
import org.springframework.beans.factory.annotation.Autowired;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import org.springframework.jdbc.core.JdbcTemplate;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;

//import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
//import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author tam
 */

@RestController
//@RequestMapping ("/")
public class ExportController {
    //private JdbcTemplate jdbcTemplate = new JdbcTemplate(WebConfig.getDataSource());
    /*@Autowired
    private WebConfig WebConfig;*/


    @RequestMapping(value="/welcome", method = RequestMethod.GET)
    public String getHomePage(ModelMap model) {
        return "welcome1";
    }

    @RequestMapping(value="/download", method = RequestMethod.GET)
    public void  generateExcel() throws SQLException, ClassNotFoundException, FileNotFoundException, IOException{
            Workbook wb = null;
            Connection conn = null;
            PreparedStatement stmt = null;
            ResultSet rs = null;
            Class.forName("com.mysql.jdbc.Driver");
        try {
            conn = DriverManager.getConnection(WebConfig.URL,WebConfig.USERNAME,WebConfig.PASSWORD);
            //New Workbook
            wb = new SXSSFWorkbook(2000);
            //Cell style for header row
            CellStyle cs = wb.createCellStyle();
            cs.setFillForegroundColor(IndexedColors.LIME.getIndex());
            //cs.setFillPattern(XSSFCellStyle.SOLID_FOREGROUND);
            Font f = wb.createFont();
            //f.setBoldweight(Font.BOLDWEIGHT_BOLD);
            f.setFontHeightInPoints((short) 12);
            cs.setFont(f);
            //New Sheet
            Sheet sheet1 = wb.createSheet("myData");
            String sql = "SELECT * FROM member_data WHERE 1";
            stmt = conn.prepareStatement(sql);
            rs = stmt.executeQuery();
           // System.out.println(rs);
            ResultSetMetaData metaData = rs.getMetaData();
            int colCount = metaData.getColumnCount();
            //Create Hash Map of Field Definitions
            LinkedHashMap<Integer, MyTableInfo> tableInfoMap = new LinkedHashMap<Integer, MyTableInfo>(colCount);
            for (int i = 0; i < colCount; i++) {
                MyTableInfo db2TableInfo = new MyTableInfo();
                db2TableInfo.setFieldName(metaData.getColumnName(i + 1).trim());
                db2TableInfo.setFieldText(metaData.getColumnLabel(i + 1));
                db2TableInfo.setFieldSize(metaData.getPrecision(i + 1));
                db2TableInfo.setFieldDecimal(metaData.getScale(i + 1));
                db2TableInfo.setFieldType(metaData.getColumnType(i + 1));
                tableInfoMap.put(i, db2TableInfo);
            }
            // Row and column indexes
            int idx = 0;
            int idy = 0;
            // Generate column headings
            Row row = sheet1.createRow(idx);
            Iterator<Integer> iterator = tableInfoMap.keySet().iterator();
            while (iterator.hasNext()) {
                Integer key = (Integer) iterator.next();
                MyTableInfo db2TableInfo = tableInfoMap.get(key);
                Cell c = row.createCell(idy);
                c.setCellValue(db2TableInfo.getFieldText());
                c.setCellStyle(cs);
                idy++;
            }
            while (rs.next()) {
                idx++;
                Row currentRow = sheet1.createRow(idx);
                for (int i = 0; i < colCount; i++) {
                  Object val = rs.getObject(i+1);
                  if( val != null ){
                	// System.out.println(val.toString());
                	 Cell  currentCell = currentRow.createCell(i);
                	  //currentCell.setCellType(Cell.CELL_TYPE_STRING);
                	  currentCell.setCellValue(val.toString());
                  } 
                }
            }

        /*File file = new File("Foo.txt");
        try (PrintStream ps = new PrintStream(file)) {
           ps.println("Bar");
        }
        response.setContentType("application/octet-stream");
        response.setContentLength((int) file.length());
        response.setHeader( "Content-Disposition",String.format("attachment; filename=\"%s\"", file.getName()));

        OutputStream out = response.getOutputStream();
        try (FileInputStream in = new FileInputStream(file)) {
            byte[] buffer = new byte[4096];
            int length;
            while ((length = in.read(buffer)) > 0) {
                //out.write(buffer, 0, length);
                wb.write(buffer, 0, length);
            }
        }
        out.flush();*/

  // Write the output to a file
        FileOutputStream fileOut = new FileOutputStream("/home/tam/Documents/poi-generated-file.xlsx");
        wb.write(fileOut);
        fileOut.close();

            /*System.out.println("data written");
            String excelFile = "/home/tam/Documents/export.xlsx";
            wb.write(new FileOutputStream(excelFile));*/
            System.out.println("Completed");	
        }
        finally{
            try{
        	if(rs != null)
                    rs.close();
        	if(stmt != null)
                    stmt.close();
        	if(conn != null)
                    conn.close();
        	if(wb != null)
                    wb.close();	
            }catch(Exception e){
                System.out.println("inside finally exception");
            }
        }
    }

}
