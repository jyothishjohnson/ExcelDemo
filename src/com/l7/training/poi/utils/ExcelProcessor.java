package com.l7.training.poi.utils;

import com.l7.training.poi.exception.NullCellValueException;
import com.l7.training.poi.pojo.Book;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ExcelProcessor {
    public static void main(String[] args) {
        System.out.println("1.Do you wanna read from an Excel File?\n2.Do you wanna write to an Excel File");
        Scanner sc = new Scanner(System.in);
        int choice = sc.nextInt();
        switch (choice) {
            case 1:
                String filePathRead = "Book.xlsx";
                ExcelReader reader = new ExcelReader();
                List<Book> bookListRead = new ArrayList<>();
                try {
                    bookListRead = reader.readBooksFromExcel(filePathRead);

                }catch (NullCellValueException ne){
                    System.out.println(ne.getMessage());
                }
                catch (IOException e) {
                    e.printStackTrace();
                }

                if (!bookListRead.isEmpty()) {
                    for (Book book : bookListRead) {
                        System.out.println(book);
                    }
                }
                break;
            case 2:
                ArrayList<Book> bookList = null;
                try {
                    String filePath = "input.txt";
                    bookList = getBooksFromFile(filePath);
                    new ExcelWriter(bookList);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
            }

        }

    static ArrayList<Book> getBooksFromFile (String filePath) throws FileNotFoundException {
        ArrayList<Book> bookList = new ArrayList<>();
        File f = new File(filePath);
        Scanner sc = new Scanner(f);
        Book book = null;
        while (sc.hasNextLine()) {
            book = new Book();
            String bookString = sc.nextLine();
            String[] bookStringArray = bookString.split("-");
            book.setTitle(bookStringArray[0]);
            book.setAuthor(bookStringArray[1]);
            book.setPrice(Double.parseDouble(bookStringArray[2]));
            bookList.add(book);
        }
        sc.close();
        return bookList;
    }
}

