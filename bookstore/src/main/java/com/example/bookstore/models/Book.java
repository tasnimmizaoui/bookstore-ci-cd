package com.example.bookstore.models;

public class Book {
    private String title;
    private String author;
    private double price;

    public Book(String name, String author, double price) {
        this.title = name;
        this.author = author;
        this.price = price;
    }
    public Book() {

    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String name) {
        this.title = name;
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
