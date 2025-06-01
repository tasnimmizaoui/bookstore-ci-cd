package com.example.bookstore.models;

import jakarta.persistence.*;


import java.math.BigDecimal;

@Entity
@Table(name = "books")
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private BigDecimal price;

    private String description;

    private Integer stock;

    // Constructeurs
    public Book() {
    }
    public Book(String title, String author, BigDecimal price, String description, Integer stock) {
        this.title = title;
        this.author = author;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }


    public Book(Long id, String title, String author, BigDecimal price, String description, Integer stock) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.price = price;
        this.description = description;
        this.stock = stock;
    }

    // Getters
    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public Integer getStock() {
        return stock;
    }

    // Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}