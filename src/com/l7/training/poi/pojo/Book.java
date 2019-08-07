package com.l7.training.poi.pojo;

public class Book {

    private String title;
    private String author;
    private double price;

    @Override
    public String toString() {
        return title + " " + author + " " + price;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
