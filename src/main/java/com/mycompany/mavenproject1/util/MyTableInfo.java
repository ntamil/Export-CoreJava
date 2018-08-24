package com.mycompany.mavenproject1.util;

import org.apache.poi.ss.usermodel.CellStyle;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author tam
 */
public class MyTableInfo {
        String fieldName = null;
    String fieldText = null;
    int fieldType = 0;
    int fieldSize = 0;
    int fieldDecimal = 0;
    CellStyle cellStyle = null;
    
    public String getFieldName() {
        return fieldName;
    }
    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }
    public String getFieldText() {
        return fieldText;
    }
    public void setFieldText(String fieldText) {
        this.fieldText = fieldText;
    }
    public int getFieldType() {
        return fieldType;
    }
    public void setFieldType(int fieldType) {
        this.fieldType = fieldType;
    }
    public int getFieldSize() {
        return fieldSize;
    }
    public void setFieldSize(int fieldSize) {
        this.fieldSize = fieldSize;
    }
    public int getFieldDecimal() {
        return fieldDecimal;
    }
    public void setFieldDecimal(int fieldDecimal) {
        this.fieldDecimal = fieldDecimal;
    }
    public CellStyle getCellStyle() {
        return cellStyle;
    }
    public void setCellStyle(CellStyle cellStyle) {
        this.cellStyle = cellStyle;
    }
}
