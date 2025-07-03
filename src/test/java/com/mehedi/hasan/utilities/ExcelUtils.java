package com.mehedi.hasan.utilities;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelUtils {
    
    public Object[][] getExcelData() throws IOException {
        String excelFilePath = "C:\\Users\\mehed\\Documents\\workspace-spring-tool-suite-4-4.28.1.RELEASE\\ExamTest\\testdata\\testdata.xlsx";
        FileInputStream inputStream = new FileInputStream(new File(excelFilePath));

        XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
        XSSFSheet sheet = workbook.getSheetAt(0);

        int rows = sheet.getLastRowNum(); // no. of data rows (assumed 1st row is header)
        Object[][] data = new Object[rows][3];

        DataFormatter formatter = new DataFormatter();

        for (int i = 1; i <= rows; i++) { // starting from 1, assuming row 0 is header
            XSSFRow row = sheet.getRow(i);
            data[i - 1][0] = formatter.formatCellValue(row.getCell(0));  // username
            data[i - 1][1] = formatter.formatCellValue(row.getCell(1));  // password
            data[i - 1][2] = formatter.formatCellValue(row.getCell(2));  // expected
        }

        workbook.close();
        inputStream.close();

        return data;
    }
}
