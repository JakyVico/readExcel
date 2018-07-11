/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pruebas;

import com.fasterxml.jackson.databind.util.JSONPObject;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.print.DocFlavor;
import jdk.nashorn.internal.runtime.Version;
import netscape.javascript.JSObject;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import static org.apache.poi.ss.usermodel.Cell.CELL_TYPE_BLANK;
import org.apache.poi.util.ArrayUtil;

/**
 *
 * @author VS1XFI7
 */
public class LeerArch {

    String nameSheetTwo;
    String nameSheetOne;

    private static List<String> HEADER_PRODUCT = new ArrayList<String>();
    private static List<String> REGIONES = new ArrayList<String>();
    private static List PRODUCTO = new ArrayList();
    private static List<String> NAMESHEET = new ArrayList<String>();
    private static List datosC1 = new ArrayList();
    private static String nombre, fechaEfectiv, observaciones, ext, tel, version;
    private static Date fechaElab;
    private static String tipoCargo, carcProd;
    private static List<String> validList;
    private static List<String> valoresAprovisionamiento;

    public static void main(String[] args) {
        LeerArch readXls = new LeerArch();
        //readXls.readXLSFile("C:/Users/VS1XFI7/Desktop/JAQUE/Formatos/Productos/producRevision.xls");
        readXls.readXLSFile("C:/Users/VS1XFI7/Desktop/JAQUE/Formatos/Productos/validacionp.xls");
        //System.out.println(HEADER_PRODUCT + "  Esto es el header");
        //System.out.println(REGIONES + "regiones");
        //System.out.println(PRODUCTO + " productos");
        //System.out.println(NAMESHEET + " nombres");
        //System.out.println(datosC1 + " datos c1");
        //System.out.println("aaaa" + validList + "listasssssss");
      
       
    }

    public void readXLSFile(String fileName) {
        InputStream ExcelFileToRead = null;
        HSSFWorkbook workbook = null;
        String typePlan;
        int numSheet = 0;
        List TempList = null;
        //String nameSheet=null;
        List names = null;
        HEADER_PRODUCT = new ArrayList();
        PRODUCTO = new ArrayList();

        try {
            ExcelFileToRead = new FileInputStream(fileName);
            workbook = new HSSFWorkbook(ExcelFileToRead);
            numSheet = workbook.getNumberOfSheets();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (int i = 0; i < numSheet; i++) {
            HSSFSheet sheet = workbook.getSheetAt(i);
            NAMESHEET.add(workbook.getSheetName(i));
            HSSFRow row;
            HSSFCell cell;
            Iterator rows = sheet.rowIterator();
            while (rows.hasNext()) {
                row = (HSSFRow) rows.next();
                Iterator cells = row.cellIterator();
                while (cells.hasNext()) {
                    cell = (HSSFCell) cells.next();
                    //validaPlan(cell);
                    readFormat(cell);
                }
                try {
                    ExcelFileToRead.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //metodo agrgar a las listas los headers 
    public static void readFormat(HSSFCell cell) {
        int fila = cell.getRowIndex();
        int colum = cell.getColumnIndex();
        //Validacion para productos hoja uno:::::::::::::::::::::::::::::::::::::::::
        if (NAMESHEET.size() == 1) {
            if (NAMESHEET.get(0).equalsIgnoreCase("Formato")) {
                if (fila == 9) {
                    validList = generaLista();
                    // validList.add(cell.getStringCellValue());
                    //System.out.println(validList + "Lista generada");
                    HEADER_PRODUCT.add(cell.getStringCellValue());
                    //System.out.println("header"+HEADER_PRODUCT);
                } else if (fila >= 10) {
                    if (validList.size() < HEADER_PRODUCT.size()) {
                        validList.add(cell.getStringCellValue());
                        // System.out.println("HEADER SIZEEE"+HEADER_PRODUCT.size());
                        // System.out.println("Daata"+validList.size());
                        // System.out.println("Daata"+validList);
                    } else {
                        validaDatos(validList);
                        validList = generaLista();
                        validList.add(cell.getStringCellValue());
                    }
                }

            }
        }

        /*
   //Validar hoja dos 
        if(NAMESHEET.size()==2 && NAMESHEET.get(1).equalsIgnoreCase("Aprovisionamiento")){
                //System.err.println(NAMESHEET+"  nam");
                //System.err.println("datosss  "+cell.getStringCellValue()+" Fila: "+fila+" Columna: "+colum);
            if (fila == 1) {
                validList = generaLista();
                // validList.add(cell.getStringCellValue());
                //System.out.println(validList + "Lista generada");
                HEADER_PRODUCT.add(cell.getStringCellValue());
                //System.out.println("header"+HEADER_PRODUCT);
            } else if (fila >= 1) {              
                 if(validList.size()<HEADER_PRODUCT.size()){
                     validList.add(cell.getStringCellValue());
                     System.out.println("HEADER SIZEEE"+HEADER_PRODUCT.size());
                      System.out.println("Daata"+validList.size());
                      System.out.println("Daata"+validList);
                 }else{
                     validaDatos(validList);
                     validList = generaLista();
                     validList.add(cell.getStringCellValue());
                 }
            }
                
            if (fila == 8) {
                validList = generaLista();
                // validList.add(cell.getStringCellValue());
                //System.out.println(validList + "Lista generada");
                HEADER_PRODUCT.add(cell.getStringCellValue());
                //System.out.println("header"+HEADER_PRODUCT);
            } else if (fila >= 8) {              
                 if(validList.size()<HEADER_PRODUCT.size()){
                     validList.add(cell.getStringCellValue());
                     System.out.println("HEADER SIZEEE"+HEADER_PRODUCT.size());
                      System.out.println("Daata"+validList.size());
                      System.out.println("Daata"+validList);
                 }else{
                     validaDatos(validList);
                     validList = generaLista();
                     validList.add(cell.getStringCellValue());
                 }
            }
        }*/
    }

    private static void validaDatos(List<String> validList1) {
        
        String dato="PRORRATEO X(1)";
        int position=convierteDatoToPosicion(dato) ;
        
        //System.err.println("header"+HEADER_PRODUCT);
        if (validList1.get(0).isEmpty()) {
            //  System.err.println("No tiene dato principal");
        } else {
            //System.out.println("list:::::::::::::::::" + validList);
            String operador = "<";
            int posicion = 7-1;
            int valor = 8;
            
            //reglaLongitud(operador, valor,posicion,validList1);
            
            String caracter = "CR";
            int posicionCont=7-1;
            //reglaContenido(caracter,posicionCont,validList1);
            
          int posicionDep1=12-1;
          int posicionDep2=16-1;
          String caracter1="SERAD";
          String caracter2="MS";
          
          //reglaDependencia(posicionDep1,posicionDep2,caracter1,caracter2,validList1);
          
          int enteros=3;
          int decimales=2;
          int posicionDecimal=11-1;
          
          //pendiente
          //reglasEnterosDecimales(enteros,decimales,posicionDecimal,validList1);
          
          
          
        }
    }

    private static void reglaLongitud(String operador, int valor, int posicion, List<String> validList1) {
        
        
        if (operador.equals("<")) {
            if(validList1.get(posicion).length()<valor){
                System.out.println("Regla correcta longitud");
            }else{
                System.out.println("No cumple regla longitud");
            }
           
        } else if (operador.equals(">")) {
            if(validList1.get(posicion).length()>valor){
                System.out.println("Regla correcta longitud");
            }else{
                System.out.println("No cumple regla longitud");
            }
           
        } else if (operador.equals("=")) {
            if(validList1.get(posicion).length()==valor){
                System.out.println("Regla correcta longitud");
            }else{
                System.out.println("No cumple regla longitud");
            }
            
        }
    }

    private static void reglaContenido(String caracter, int posicionCont, List<String> validList1) {
        
        if(validList1.get(posicionCont).equals(caracter)){
            System.out.println("Regla de contenido exitosa");
        }else{
            System.out.println("no cumple la regla ");
        }
        
        
    }

    private static void reglaDependencia(int posicionDep1, int posicionDep2, String caracter1, String caracter2, List<String> validList1) {
      
        if(validList1.get(posicionDep1).equals(caracter1)&& validList1.get(posicionDep2).equals(caracter2) ){
             System.out.println("Regla exitosa");
        }else{
             System.out.println(validList1.get(posicionDep1)+"  El valor de esta columna no correcponde con el valor de.  "+validList1.get(posicionDep2));
        }
    }

    private static void reglasEnterosDecimales(int enteros, int decimales, int posicionDecimal, List<String> validList1) {
        System.out.println(validList1.get(posicionDecimal)+"::::: "); 
   
    }
    
    //Es necesario o solo 
    private static int convierteDatoToPosicion(String dato) {
        //System.out.println(HEADER_PRODUCT.indexOf(dato)+ "indexxxx");
        int position=HEADER_PRODUCT.indexOf(dato);
        return position;
     }
    
    public static List<String> generaLista() {
        return new ArrayList<String>();
    }

}
