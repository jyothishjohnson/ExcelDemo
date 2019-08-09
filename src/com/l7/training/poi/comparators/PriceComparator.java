package com.l7.training.poi.comparators;

import com.l7.training.poi.pojo.Book;

import java.util.Comparator;

public class PriceComparator implements Comparator<Book> {
    @Override
    public int compare(Book o1, Book o2) {
        return Double.compare(o1.getPrice(),o2.getPrice());
    }
}
