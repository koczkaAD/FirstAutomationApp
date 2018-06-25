package com.selenium.utility.reader;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;

/**
 * Created by Dóra on 2018.06.25..
 */
public class ExcelReader {

    XSSFWorkbook workbook;
    XSSFSheet sheet;

    public ExcelReader(String path) {

        try {
            File src = new File(path);
            FileInputStream fis = new FileInputStream(src);
            workbook = new XSSFWorkbook(fis);
            fis.close();

        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private Object getData(String sheetName, int row, int col) {
        sheet = workbook.getSheet(sheetName);
        Object data = null;
        CellType cellType = sheet.getRow(row).getCell(col).getCellTypeEnum();

        switch (cellType) {
            case NUMERIC:
                data = sheet.getRow(row).getCell(col).getNumericCellValue();
                break;
            case STRING:
                data = sheet.getRow(row).getCell(col).getStringCellValue();
                break;
        }
        return data;
    }

    private String getData(int row, int col) {
        sheet = workbook.getSheetAt(0);
        String data = sheet.getRow(row).getCell(col).getStringCellValue();
        return data;
    }

    public void writeData(int row, int col, String data) {
        sheet = workbook.getSheetAt(0);
        sheet.getRow(row).getCell(col).setCellValue(data);
    }

    public int getRowCount() {
        int lastRow = workbook.getSheetAt(0).getLastRowNum();
        return lastRow + 1;
    }

    private int getRowCount(String sheetName) {
        int lastRow = workbook.getSheetAt(0).getLastRowNum();
        return lastRow + 1;
    }

    private int getColumnCount(String sheetName) {

        if (!isSheetExist(sheetName))
            return -1;

        sheet = workbook.getSheet(sheetName);
        XSSFRow row = sheet.getRow(0);

        if (row == null)
            return -1;

        return row.getLastCellNum();
    }

    private boolean isSheetExist(String sheetName) {
        int index = workbook.getSheetIndex(sheetName);

        if (index == -1) {
            index = workbook.getSheetIndex(sheetName.toUpperCase());
            if (index == -1)
                return false;
            else
                return true;
        } else
            return true;
    }


    public Object[][] passData(String sheetName) {
        Object[][] result;
        int rowCount = getRowCount(sheetName);
        int columnCount = getColumnCount(sheetName);

        result = new Object[rowCount - 1][columnCount];

        for (int i = 1; i < rowCount; i++) {
            for (int j = 0; j < columnCount; j++) {
                result[i - 1][j] = getData(sheetName, i, j);
            }
        }
        return result;
    }

}
