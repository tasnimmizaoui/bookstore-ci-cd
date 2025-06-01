package com.example.bookstore.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Cart {

    private List<CartItem> items = new ArrayList<>();

    // Getter et Setter pour items
    public List<CartItem> getItems() {
        return items;
    }

    public void setItems(List<CartItem> items) {
        this.items = items;
    }

    public void addItem(Book book, int quantity) {
        // Check if the book is already in the cart
        for (CartItem item : items) {
            if (item.getBookId().equals(book.getId())) {
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }

        // If not, add a new item
        CartItem newItem = new CartItem();
        newItem.setBookId(book.getId());
        newItem.setTitle(book.getTitle());
        newItem.setAuthor(book.getAuthor());
        newItem.setPrice(book.getPrice());
        newItem.setQuantity(quantity);
        items.add(newItem);
    }

    public void removeItem(Long bookId) {
        items.removeIf(item -> item.getBookId().equals(bookId));
    }

    public void updateQuantity(Long bookId, int quantity) {
        for (CartItem item : items) {
            if (item.getBookId().equals(bookId)) {
                item.setQuantity(quantity);
                return;
            }
        }
    }

    public void clear() {
        items.clear();
    }

    public BigDecimal getTotal() {
        return items.stream()
                .map(CartItem::getSubtotal)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public int getItemCount() {
        return items.stream()
                .mapToInt(CartItem::getQuantity)
                .sum();
    }
}