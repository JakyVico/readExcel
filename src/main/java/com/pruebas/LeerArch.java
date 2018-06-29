/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebas;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK;

/**
 *
 * @author VS1XFI7
 */
public class LeerArch {
     String nameSheetTwo;
     String nameSheetOne;


    public void readXLSFile(String fileName) {
        InputStream ExcelFileToRead = null;
        HSSFWorkbook workbook = null;
        String typePlan;
        int numSheet = 0;
        List TempList = null;
        //String nameSheet=null;
        
       
        try {
            ExcelFileToRead = new FileInputStream(fileName);
            //Getting the workbook instance for xls file
            workbook = new HSSFWorkbook(ExcelFileToRead);
            numSheet = workbook.getNumberOfSheets();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //getting the first sheet from the workbook using sheet name. 
        // We can also pass the index of the sheet which starts from '0'.
        //HSSFSheet sheet = workbook.getSheet(0);
        for (int i = 0; i < numSheet; i++) {
            HSSFSheet sheet = workbook.getSheetAt(i);
         
           System.out.println("::::::"+workbook.getSheetName(1)); 
           workbook.getSheetName(i);
         
            HSSFRow row;
            HSSFCell cell;
            Iterator rows = sheet.rowIterator();

            while (rows.hasNext()) {

                row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();

                while (cells.hasNext()) {
                    cell = (HSSFCell) cells.next();
                    readData(cell);
                }
                System.out.println();
                try {
                    ExcelFileToRead.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    


    public void readData(HSSFCell cell) {
        int fila= cell.getRowIndex();
        int colum=cell.getColumnIndex();
        String cadena= null;
        int numero= 0;
        Boolean bol=null;
        String form=null;
        List encabezado=null;
        
       // System.out.println(nameSheetOne+"Nombre hoja uno"+" "+nameSheetTwo+"Nombre hoja dos");
        /*
        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            System.out.println(cell.getStringCellValue() + "\t\t\t\t\t" + "Columna " + " " + cell.getColumnIndex() + " Fila: " + cell.getRowIndex());
            cadena= cell.getStringCellValue();      
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
             numero = (int) cell.getNumericCellValue();
            System.out.println(numero + "\t\t\t\t\t" + "Columna " + " " + cell.getColumnIndex() + " Fila: " + cell.getRowIndex());
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_BOOLEAN) {
            System.out.println(cell.getBooleanCellValue() + "\t\t\t\t\t" + "Columna " + " " + cell.getColumnIndex() + " Fila: " + cell.getRowIndex());
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_FORMULA) {
            if (cell.getCellFormula().equals("NOW()")) {
                System.out.println(cell.getDateCellValue() + "\t\t\t\t\t" + "Columna " + " " + cell.getColumnIndex() + " Fila: " + cell.getRowIndex());
            }

        } else { // //Here if require, we can also add below methods to
            // read the cell content
            // HSSFCell.CELL_TYPE_BLANK
            // HSSFCell.CELL_TYPE_FORMULA
            // HSSFCell.CELL_TYPE_ERROR
        }*/
        
        if(fila==9){
            //System.out.println(cell.getStringCellValue() + "\t\t\t\t\t" + "Columna " + " " + cell.getColumnIndex() + " Fila: " + cell.getRowIndex());
            System.out.println(cell.getStringCellValue()+"Datos cabecera");
        }
        
        if(fila>=10 ){
            //logica de 
            String claveProd,descCorta,descLarga,claveAjuste,descLargaAjuste,tipoCargo,prorrateo,inclPlan,facAdelantado;
            Number costProd ;
            
           
               if(colum==0 ){
                    claveProd=cell.getStringCellValue();
                    if(claveProd.length()<= 5  ){
                        System.out.println(claveProd+" Dato ok");
                    }else if (claveProd.isEmpty()){
                        System.out.println(claveProd+" Error");
                    }else{
                        System.out.println("Error");
                    }
                      
               }
    
               if(colum==1){
                   
                          
                   descCorta=cell.getStringCellValue();
                   if(descCorta.length() <= 10){
                       System.out.println(descCorta+" Dato ok");
                   }else if (descCorta.isEmpty()){
                        System.out.println(descCorta+" Error");
                    }else{
                        System.out.println("Error");
                    }
                   
               }
               if(colum==2){
                   descLarga=cell.getStringCellValue();
                   if(descLarga.length()<= 30){
                       System.out.println(descLarga+" Dato ok");
                   }else if (descLarga.isEmpty()){
                        System.out.println(descLarga+" Error");
                    }else{
                        System.out.println("Error");
                    }
                   
               }
                if(colum==3){
                    claveAjuste=cell.getStringCellValue();
                   if(claveAjuste.length() <= 5){
                       System.out.println(claveAjuste+" Dato ok");
                   }else if (claveAjuste.isEmpty()){
                        System.out.println(claveAjuste+" Error");
                    }else{
                        System.out.println("Error");
                    }
                   
               }
               if(colum==4){
                   descLargaAjuste=cell.getStringCellValue();
                   if(descLargaAjuste.length() <= 10){
                       System.out.println(descLargaAjuste+" Dato ok");
                   }else if (descLargaAjuste.isEmpty()){
                        System.out.println(descLargaAjuste+" Error");
                    }else{
                        System.out.println("Error");
                    }
                   
               }
               if(colum==5){
                   tipoCargo=cell.getStringCellValue();
                   if(tipoCargo.length() <= 20){
                       System.out.println(tipoCargo+" Dato ok");
                   }else if (tipoCargo.isEmpty()){
                        System.out.println(tipoCargo+" Error");
                    }else{
                        System.out.println("Error");
                    }
                   
               }
               if(colum==6){
                   prorrateo=cell.getStringCellValue();
                   if(prorrateo.length() <= 2){
                       System.out.println(prorrateo+" Dato ok");
                   }else if (prorrateo.isEmpty()){
                        System.out.println(prorrateo+" Error");
                    }else{
                        System.out.println("Error");
                    }
                   
               }
               if(colum==7){
                   inclPlan=cell.getStringCellValue();
                   if(inclPlan.length() <= 10){
                       System.out.println(inclPlan+"Dato ok");
                   }else if (inclPlan.isEmpty()){
                        System.out.println(inclPlan+" Error");
                    }else{
                        System.out.println("Error");
                    }
                   
               }
               if(colum==8){
                   facAdelantado=cell.getStringCellValue();
                   if(facAdelantado.length() <= 10){
                       System.out.println(facAdelantado+"Dato ok");
                   }else if (facAdelantado.isEmpty()){
                        System.out.println(facAdelantado+" Error");
                    }else{
                        System.out.println("Error");
                    }
                   
               }
               if(colum==9){
                   descCorta=cell.getStringCellValue();
                   if(descCorta.length() <= 10){
                       System.out.println(descCorta+"Dato ok");
                   }else if (descCorta.isEmpty()){
                        System.out.println(descCorta+" Error");
                    }else{
                        System.out.println("Error");
                    }
                   
               }
                if(colum==10){
                    costProd= cell.getNumericCellValue();
                   if(costProd.intValue() != 10){
                       System.out.println(costProd+" Dato ok");
                   }else if (costProd==null){
                        System.out.println(costProd+" Error");
                    }else{
                        System.out.println("Error");
                    }
                   
               }
               if(colum==11){
                   descCorta=cell.getStringCellValue();
                   if(descCorta.length()!= 10){
                       System.out.println(descCorta+" Dato ok");
                   }else if (descCorta==null){
                        System.out.println(descCorta+" Error");
                    }else{
                        System.out.println("Error");
                    }
                   
               }
               if(colum==12){
                   descCorta=cell.getStringCellValue();
                   if(descCorta.length()!= 10){
                       System.out.println(descCorta+"Dato ok");
                   }else if (descCorta==null){
                        System.out.println(descCorta+" Error");
                    }else{
                        System.out.println("Error");
                    }
                   
               }
               if(colum==13){
                   descCorta=cell.getStringCellValue();
                   if(descCorta.length()!= 10){
                       System.out.println(descCorta+"Dato ok");
                   }
                   
               }
               if(colum==14){
                   descCorta=cell.getStringCellValue();
                   if(descCorta.length()!= 10){
                       System.out.println(descCorta+"Dato ok");
                   }
                   
               }
               if(colum==15){
                   descCorta=cell.getStringCellValue();
                   if(descCorta.length()!= 10){
                       System.out.println(descCorta+"Dato ok");
                   }
                   
               }
               if(colum==16){
                   
               }
               if(colum==17){
                   
               }
               if(colum==18){
                   
               }
                if(colum==19){
                   
               }
               if(colum==20){
                   
               }
               if(colum==21){
                   
               }
               if(colum==22){
                   
               }
               if(colum==23){
                   
               }
               if(colum==24){
                   
               }
               if(colum==25){
                   
               }
               if(colum==26){
                   
               }
               if(colum==27){
                   
               }
                if(colum==28){
                   
               }
  
        }
        
        
        
    }

   
    public static void main(String[] args) {
        LeerArch readXls = new LeerArch();
        readXls.readXLSFile("C:/Users/VS1XFI7/Desktop/JAQUE/Formatos/Productos/productos.xls");
    }

   
}
