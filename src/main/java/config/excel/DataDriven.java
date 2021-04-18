/*
package config.excel;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class DataDriven {
    //definir atributos
    private FileInputStream file;
    private XSSFWorkbook excel;
    private XSSFSheet hojaExcel;
    private String tituloCampoCasosDePrueba;

    public void prepararExcel(String rutaArchivo,String nombreHojaConDatos,String tituloCampoCasosDePrueba) throws IOException {
        //instanciar un objeto como una ruta de un archivo
         file = new FileInputStream(rutaArchivo);

        //Instanciar un objeto de tipo libro excel
        //Utilizando como argumento una ruta
         excel = new XSSFWorkbook(file);

         this.tituloCampoCasosDePrueba = tituloCampoCasosDePrueba;


        int sheets = excel.getNumberOfSheets();

        for(int i=0; i< sheets; i++){
            //System.out.println(excel.getSheetName(i).toString());
            if(excel.getSheetName(i).equalsIgnoreCase(nombreHojaConDatos)) {

                //Se instancia una hoja del libro
                hojaExcel = excel.getSheetAt(i);
            }

    }
    }

    public ArrayList<String> getData(String nombreCasoDePrueba) {
    //public static void main(String[] args) throws IOException {
        ArrayList<String> a = new ArrayList<String>();



                //Se instancia un iterador con las filas de la hoja con los datos
                Iterator<Row> filas = hojaExcel.iterator();

                //Se instancia la primera fila del iterador con las filas de la hoja con los datos
                Row primeraFila = filas.next();

                //Se instancia un iterador con las celdas de la primera fila
                Iterator<Cell> celda = primeraFila.cellIterator();

                int k=0;
                int columna =0;
                while(celda.hasNext()){
                    Cell celdaSelecciona = celda.next();
                    //System.out.println(celdaSelecciona.getStringCellValue());
                    if(celdaSelecciona.getStringCellValue().equalsIgnoreCase(tituloCampoCasosDePrueba)){
                        columna = k;
                    }
                    k++;
                }

                while(filas.hasNext()){
                    Row r = filas.next();

                    if(r.getCell(columna).getStringCellValue().equalsIgnoreCase(nombreCasoDePrueba)){
                        Iterator<Cell> cv = r.cellIterator();

                        while(cv.hasNext()){
                            Cell c = cv.next();
                            if(c.getCellType() == CellType.STRING){
                                a.add(c.getStringCellValue());
                            }else if(c.getCellType() == CellType.NUMERIC){
                                //System.out.println(NumberToTextConverter.toText(c.getNumericCellValue()));
                                a.add(NumberToTextConverter.toText(c.getNumericCellValue()));
                            }
                        }
                    }
                }
        return a;
    }
}
*/
