/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebas;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.apache.poi.poifs.macros.VBAMacroReader;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


/**
 *
 * @author jaqueline
 */

public class LeeExcel {

  
    public LeeExcel(File fileName){
        List cellData = new ArrayList();
        XSSFSheet hssfSheet;
        try {
            
            FileInputStream fileInputStream= new FileInputStream(fileName);
            XSSFWorkbook workBoook= new XSSFWorkbook(fileInputStream);
            int numSheet= workBoook.getNumberOfSheets();
            boolean res = workBoook.isMacroEnabled();
            System.out.println("resultado de macro"+res);
            
            
            for(int i=0; i<numSheet-1; i++){
                
                System.out.println();
                System.out.println("contador: "+i);
                int count=i-1;
                hssfSheet= workBoook.getSheetAt(i);
                Iterator rowIterator= hssfSheet.rowIterator();
                
      
            
                    while(rowIterator.hasNext()){
                        XSSFRow hssRow=(XSSFRow) rowIterator.next();

                        Iterator iterator=hssRow.cellIterator();
                        List cellTemp = new ArrayList();
                        while (iterator.hasNext()){
                            XSSFCell hssfCell =(XSSFCell) iterator.next();
                    
                            cellTemp.add(hssfCell);
                        }
                        cellData.add(cellTemp);
                    }

            }
     

            
        } catch (Exception e) {
            e.printStackTrace();
        }
        obtener(cellData);
        
    }
    
    private void obtener(List cellDataList){
        
        for(int i=0; i<cellDataList.size();i++){
            List cellTempList=(List) cellDataList.get(i);
            if(cellDataList.isEmpty()){
                
            }
            else{
                System.out.println(cellTempList+"  aqui estan los datos ");
            }
            
            
            for(int j=0;j< cellTempList.size();j++){
                XSSFCell hssfCell=(XSSFCell) cellTempList.get(j);
                
                String stringCellValString = hssfCell.toString();
                
                if(stringCellValString.equals(null)){
                    System.out.println(stringCellValString+" ");
                }
                else{
                    
                }
                
                       
            }
            System.out.println();
            
        }
        
        
    }
    
     public static void main(String[] args) {
        // TODO code application logic here
        
        File f= new File("C:/Users/VS1XFI7/Desktop/JAQUE/p.xlsx");
        String nombre=f.getName();
        System.out.println("nombre del excel: "+nombre);  
        
              try {
            if(f.exists()){
            LeeExcel obj= new LeeExcel(f);
            //System.out.println("pase por aqui ");
        }
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        //Prueba
    }
     
    private void validaDatos(List datos){
        
     
        
        
    }
           
    
    
    
}
