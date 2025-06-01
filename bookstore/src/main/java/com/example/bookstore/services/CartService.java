package com.example.bookstore.services;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

@Service
@Scope(value = WebApplicationContext.SCOPE_SESSION, proxyMode = ScopedProxyMode.TARGET_CLASS)
public class CartService {

    private final Cart cart = new Cart();

    @Autowired
    private BookService bookService;

    public Cart getCart() {
        return cart;
    }

    public void addToCart(Long bookId, int quantity) {
        Book book = bookService.getBookById(bookId);
        cart.addItem(book, quantity);
    }

    public void removeFromCart(Long bookId) {
        cart.removeItem(bookId);
    }

    public void updateQuantity(Long bookId, int quantity) {
        cart.updateQuantity(bookId, quantity);
    }

    public void clearCart() {
        cart.clear();
    }
}