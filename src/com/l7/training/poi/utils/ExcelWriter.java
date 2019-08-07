package com.l7.training.poi.utils;

import com.l7.training.poi.pojo.Book;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

class ExcelWriter {
    ExcelWriter(ArrayList<Book> bookList) throws IOException {
        addBooksToExcel(bookList);
    }

    private void addBooksToExcel(ArrayList<Book> bookList) throws IOException {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet("bookDetails");
        Iterator<Book> iterator = bookList.iterator();

        int i = 0;
        while (iterator.hasNext()) {
            XSSFRow row = sheet.createRow(i);
            XSSFCell titleCell = row.createCell(0);
            XSSFCell authorCell = row.createCell(1);
            XSSFCell priceCell = row.createCell(2);
            titleCell.setCellValue(bookList.get(i).getTitle());
            authorCell.setCellValue(bookList.get(i).getAuthor());
            priceCell.setCellValue(bookList.get(i).getPrice());
            i++;
            iterator.next();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(new File("Output.xlsx"));
        workbook.write(fileOutputStream);
        System.out.println("Success");

        workbook.close();
        fileOutputStream.close();
    }
}
