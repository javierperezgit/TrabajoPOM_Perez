package Unidad2.utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
/*
* Utilidad para el manejo de los datos de prueba. Como funciona:
*  1) representar los datos de prueba en un excel
*  2) agregar excel al proyecto
*  3) referenciar ruta del excel como property
*  4) obtener los datos en base al nombre del caso de prueba
* */
public class DataDriven {
    public static ArrayList<String> prepararData(String nombreCP) {
        //Objeto de tipo file
        FileInputStream file;
        ArrayList<String> data = new ArrayList<>();

        try {
            file = new FileInputStream(PropertiesManager.obtenerProperty("rutaExcel"));
        }catch (FileNotFoundException ex){
            System.out.println("Fallo al crear el archivo...favor revisar ruta");
            System.out.println(ex.getMessage());
            file = null;
        }

        //instanciar un excel en base al archivo
        XSSFWorkbook excel;

        try{
            excel = new XSSFWorkbook(file);
        }catch (IOException ex){
            System.out.println("Error al crear el excel..favor revisar formato del archivo");
            System.out.println(ex.getMessage());
            excel = null;
        }

        int sheets = excel.getNumberOfSheets();

        System.out.println("La cantidad de hojas del excel es: "+sheets);

        for (int i=0; i<sheets;i++){
            if(excel.getSheetName(i).equalsIgnoreCase(PropertiesManager.obtenerProperty("hojaExcel"))){
                //encontre la hoja
                XSSFSheet hojaExcel = excel.getSheetAt(i);

                Iterator<Row> filas = hojaExcel.iterator();

                Row fila = filas.next();

                Iterator<Cell> celda = fila.cellIterator();

                int k = 0;
                int columna = 0;

                while(celda.hasNext()){
                    Cell celdaSeleccionada = celda.next();

                    if(celdaSeleccionada.getStringCellValue().equalsIgnoreCase(PropertiesManager.obtenerProperty("TituloCasosPrueba"))){
                        //columna con los nombres de los casos
                        columna = k;
                    }
                    k++;

                }

                while(filas.hasNext()){

                    Row r = filas.next();

                    if(r.getCell(columna).getStringCellValue().equalsIgnoreCase(nombreCP)){

                        Iterator<Cell> cv = r.cellIterator();

                        while (cv.hasNext()){

                            Cell c = cv.next();

                            if(c.getCellType() == CellType.STRING){
                                //System.out.println(c.getStringCellValue());
                                data.add(c.getStringCellValue());
                            }else if(c.getCellType() == CellType.NUMERIC){
                                //System.out.println(NumberToTextConverter.toText(c.getNumericCellValue()));
                                data.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }

                        }
                    }
                }

            }
        }
        return data;
    }
}
