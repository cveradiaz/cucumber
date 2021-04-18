package config.excel;


import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;

public class Metodos {

    private static final String fileName = "C:\\Users\\Claudio.Vera\\Desktop\\TSOFT\\cucumber-base\\src\\test\\resources\\datos\\DataDriven.xlsx";

    public static void main(String[] args) {
        getValor(1);
    }

    public static String getValor(int columna)  {
        int FIRST_ROW_TO_GET = 1;
        FileInputStream file = null;
        XSSFWorkbook workbook = null;
        try {
            file = new FileInputStream(
                    new File(fileName));
            workbook = new XSSFWorkbook(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        XSSFSheet sheet = workbook.getSheetAt(0);
        String cell1 = "";
        Row row = sheet.getRow(2);
        Cell cell0 = row.getCell(columna);
        cell1 = cell0.getStringCellValue();
        /*if (cell0 != null) {
            cell1 = cell0.getStringCellValue();
        for (int i = FIRST_ROW_TO_GET; i <= sheet.getLastRowNum(); i++) {
            Row row = sheet.getRow(i);
            Cell cell0 = row.getCell(columna);
            if (cell0 != null) {
                cell1 = cell0.getStringCellValue();
            }
            //System.out.println(cell1);
        }*/
        return cell1;
    }

}
