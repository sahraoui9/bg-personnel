package bgpersonnel.budget.service.excel;

import bgpersonnel.budget.service.GeneratorHelper;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.lang.reflect.Field;
import java.util.List;

@Service
@Slf4j
public class ExcelGeneratorImpl<T> implements ExcelGenerator<T> {

    private Workbook workbook;

    private static void createHeaders(String[] headers, Sheet sheet) {
        // Create the first row for headers
        Row headerRow = sheet.createRow(0);
        for (int i = 0; i < headers.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headers[i]);
        }
    }

    @Override
    public ByteArrayInputStream generateExcel(List<T> data, String[] headers) {
        try {
            workbook = new SXSSFWorkbook(); // Use SXSSFWorkbook for batch writing
            Sheet sheet = workbook.createSheet("Sheet1");
            createHeaders(headers, sheet);
            write(data, sheet);

            // Write the workbook data to a byte array
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            workbook.write(byteArrayOutputStream);
            byteArrayOutputStream.toByteArray();

            // Close the workbook
            workbook.close();

            return new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        } catch (Exception e) {
            throw new RuntimeException("fail to import data to Excel file: " + e.getMessage());
        }
    }

    private void write(List<T> data, Sheet sheet) {
        int rowCount = 1;
        for (T record : data) {
            Row row = sheet.createRow(rowCount++);
            int columnCount = 0;
            for (Field field : record.getClass().getDeclaredFields()) {
                String fieldName = field.getName();
                // Get the value of the field from the object record
                Object fieldValue = GeneratorHelper.getFieldValueFromObject(record, fieldName);
                // Insert the value of the field in the Excel sheet cell
                Cell cell = row.createCell(columnCount++);
                cell.setCellValue(fieldValue != null ? String.valueOf(fieldValue) : "");
            }
        }
    }

}
