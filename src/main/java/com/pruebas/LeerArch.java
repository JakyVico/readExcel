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
    private static int HEADER_READER = 12-1;
    private static String NAME_SHEET="Planes";
    
    //private static int HEADER_READER = 10-1;
    //private static String NAME_SHEET="Formato";

    public static void main(String[] args) {
        LeerArch readXls = new LeerArch();
        //readXls.readXLSFile("C:/Users/VS1XFI7/Desktop/JAQUE/Formatos/Planes/prubP.xls");
        readXls.readXLSFile("C:/Users/VS1XFI7/Desktop/JAQUE/Formatos/Productos/prubP.xls");
        //readXls.readXLSFile("C:/Users/VS1XFI7/Desktop/JAQUE/Formatos/Productos/validacionp.xls");
        System.out.println("  Esto es el header"+HEADER_PRODUCT+"size"+HEADER_PRODUCT.size());
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
                    prueba(cell);
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
        
       
        if (NAMESHEET.size() == 1) {
            if (NAMESHEET.get(0).equalsIgnoreCase(NAME_SHEET)) {
                if (fila == HEADER_READER) {
                    validList = generaLista();
                    HEADER_PRODUCT.add(cell.getStringCellValue());
                } else if (fila > HEADER_READER) {
                    if (validList.size() < HEADER_PRODUCT.size()) {
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                           int i = (int) cell.getNumericCellValue();
                           String temp = String.valueOf(i); 
                           validList.add(temp);   
                        } else  {
                           validList.add(cell.getStringCellValue()); 
                        }
                        // System.out.println(validList+"aaa"+validList.size());
                        //validList.add(cell.getStringCellValue());
                        // System.out.println("HEADER SIZEEE"+HEADER_PRODUCT.size());
                        // System.out.println("Daata"+validList.size());
                        //System.out.println("Daata" + validList + "SIZE  " + validList.size());
                    } else {
                        validaDatos(validList);
                        validList = generaLista();
                        if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
                            //System.out.println(cell.getStringCellValue() + "\t\t\t\t\t" + "Columna " + " " + cell.getColumnIndex() + " Fila: " + cell.getRowIndex());
                           int i = (int) cell.getNumericCellValue();
                           String temp = String.valueOf(i); 
                           validList.add(temp);
                    
                        }else{
                            validList.add(cell.getStringCellValue());
                            
                            
                        }
                         //System.out.println(validList+"bb"+validList.size());
/*
                        validaDatos(validList);
                        validList = generaLista();
                        validList.add(cell.getStringCellValue());
                        System.out.println("Daata" + validList + "SIZE  " + validList.size());*/
                    }
                }

            }
        }

    }

    private static void validaDatos(List<String> validList1) {

       // System.out.println(validList1 + "  " + validList1.size());
        String dato = "GRPPLN      X(20)";
        int position = convierteDatoToPosicion(dato);
       // System.out.println(position+"posi");
     
        
        //System.err.println("header"+HEADER_PRODUCT);
        if (validList1.get(0).isEmpty()) {
            //  System.err.println("No tiene dato principal");
        } else {
            //System.out.println("list:::::::::::::::::" + validList);
            String operador = "<";
            int valor = 8;

            //reglaLongitud(operador, valor,position,validList1);
            String caracter = "N";
             //reglaContenido(caracter,position,validList1);

            int posicionDep1 = 12 - 1;
            int posicionDep2 = 16 - 1;
            String caracter1 = "SERAD";
            String caracter2 = "MS";

            //reglaDependencia(posicionDep1,posicionDep2,caracter1,caracter2,validList1);
            int enteros = 3;
            int decimales = 2;
            int posicionDecimal = 11 - 1;

            //pendiente
            //reglasEnterosDecimales(enteros,decimales,posicionDecimal,validList1);
        }
    }

    private static void reglaLongitud(String operador, int valor, int posicion, List<String> validList1) {

        if (operador.equals("<")) {
            if (validList1.get(posicion).length() < valor) {
                System.out.println("Regla correcta longitud");
            } else {
                System.out.println("No cumple regla longitud");
            }

        } else if (operador.equals(">")) {
            if (validList1.get(posicion).length() > valor) {
                System.out.println("Regla correcta longitud");
            } else {
                System.out.println("No cumple regla longitud");
            }

        } else if (operador.equals("=")) {
            if (validList1.get(posicion).length() == valor) {
                System.out.println("Regla correcta longitud");
            } else {
                System.out.println("No cumple regla longitud");
            }

        }
    }

    private static void reglaContenido(String caracter, int posicionCont, List<String> validList1) {
        
        System.err.println(validList1.get(posicionCont)+"lalalal");
        if (validList1.get(posicionCont).equals(caracter)) {
            System.out.println("Regla de contenido exitosa" + validList1.get(posicionCont));
        } else {
            System.out.println("no cumple la regla  " + validList1.get(posicionCont));
        }

    }

    private static void reglaDependencia(int posicionDep1, int posicionDep2, String caracter1, String caracter2, List<String> validList1) {

        if (validList1.get(posicionDep1).equals(caracter1) && validList1.get(posicionDep2).equals(caracter2)) {
            System.out.println("Regla exitosa");
        } else {
            System.out.println(validList1.get(posicionDep1) + "  El valor de esta columna no correcponde con el valor de.  " + validList1.get(posicionDep2));
        }
    }

    private static void reglasEnterosDecimales(int enteros, int decimales, int posicionDecimal, List<String> validList1) {
        System.out.println(validList1.get(posicionDecimal) + "::::: ");

    }

    //Es necesario o solo 
    private static int convierteDatoToPosicion(String dato) {
        //System.out.println(HEADER_PRODUCT.indexOf(dato)+ "indexxxx");
        int position = HEADER_PRODUCT.indexOf(dato);

        return position;
    }

    public static List<String> generaLista() {
        return new ArrayList<String>();
    }

    private void prueba(HSSFCell cell) {
        if (cell.getCellType() == HSSFCell.CELL_TYPE_STRING) {
            System.out.println(cell.getStringCellValue() + "\t\t\t\t\t" + "Columna " + " " + cell.getColumnIndex() + " Fila: " + cell.getRowIndex());
        } else if (cell.getCellType() == HSSFCell.CELL_TYPE_NUMERIC) {
            System.out.println(cell.getNumericCellValue() + "\t\t\t\t\t" + "Columna " + " " + cell.getColumnIndex() + " Fila: " + cell.getRowIndex());
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
        }
    }

}
