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

    public static void main(String[] args) {
        LeerArch readXls = new LeerArch();
        //readXls.readXLSFile("C:/Users/VS1XFI7/Desktop/JAQUE/Formatos/Productos/producRevision.xls");
        readXls.readXLSFile("C:/Users/VS1XFI7/Desktop/JAQUE/Formatos/Productos/validacionp.xls");
        System.out.println(HEADER_PRODUCT + "  Esto es el header");
        System.out.println(REGIONES + "regiones");
        System.out.println(PRODUCTO + " productos");
        System.out.println(NAMESHEET + " nombres");
        System.out.println(datosC1 + " datos c1");

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
                    validRules(cell);
                }
                try {
                    ExcelFileToRead.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void validRules(HSSFCell cell) {
        
        int fila = cell.getRowIndex();
        int colum = cell.getColumnIndex();

        if (fila == 9) {
            validList = generaLista();
            validList.add(cell.getStringCellValue());
        }else if(fila >=10){
           //vaciarProduc(validList);
            //System.out.println("listaaa size:  "+validList.size()); ;
            validList.add(cell.getStringCellValue());
            
        }

        System.out.println(validList + "Nueva Lista");
    }
    
  
    //metodo generar n listas para la validacion
    public static List<String> generaLista() {
        return new ArrayList<String>();
    }

}
