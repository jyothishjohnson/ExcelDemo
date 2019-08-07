package com.l7.training.poi.utils;

import com.l7.training.poi.exception.NullCellValueException;
import com.l7.training.poi.pojo.Book;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

class ExcelReader {

    List<Book> readBooksFromExcel(String filePath) throws IOException, NullCellValueException {

        List<Book> bookList = new ArrayList<>();
        FileInputStream fileInputStream = new FileInputStream(new File(filePath));

        Workbook workbook = getWorkBook(fileInputStream, filePath);
        Sheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.rowIterator();
        rowIterator.next();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();
            Iterator<Cell> cellIterator = row.cellIterator();
            Book book = new Book();
            while (cellIterator.hasNext()) {
                Cell cell = cellIterator.next();
                int column = cell.getColumnIndex();
                switch (column) {
                    //jyothish
                    case 0:
                        if (cell.getStringCellValue() != null) {
                            book.setTitle(cell.getStringCellValue());
                            break;
                        } else {
                            throw new NullCellValueException("No Values in the cell");
                        }
                    case 1:
                        if (cell.getStringCellValue() != null) {
                            book.setAuthor(cell.getStringCellValue());
                            break;
                        } else {
                            throw new NullCellValueException("No Values in the cell");
                        }
                    case 2:
                        if (cell.getStringCellValue() != null) {
                            book.setPrice(cell.getNumericCellValue());
                            break;
                        } else {
                            throw new NullCellValueException("No Values in the cell");
                        }
                }
            }
            bookList.add(book);
        }

        workbook.close();
        fileInputStream.close();
        return bookList;
    }

    private Workbook getWorkBook(FileInputStream fileInputStream, String filePath) throws IOException {

        Workbook workbook;
        if (filePath.endsWith("xls")) {
            workbook = new HSSFWorkbook(fileInputStream);
        } else if (filePath.endsWith("xlsx")) {
            workbook = new XSSFWorkbook(fileInputStream);
        } else {
            //TODO user defined exception
            throw new IllegalArgumentException("Not an Excel File");
        }
        return workbook;
    }
}
