package com.example.bookstore.controllers;

import com.example.bookstore.models.Book;
import com.example.bookstore.models.CartItem;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Controller
public class BookController {
    private final List<Book> books = List.of(
            new Book("Clean Code", "Robert C. Martin", 25),
            new Book("Spring in Action", "Craig Walls", 30),
            new Book("Effective Java", "Joshua Bloch", 35)
    );

    @GetMapping("/books")
    public String showBooks(Model model) {
        System.out.println("📚 /books route triggered"); // Debug
        model.addAttribute("books", books);
        return "books";
    }

    @PostMapping("/cart/add")
    public String addToCart(@RequestParam String title, HttpSession session) {
        if (title == null) return "redirect:/books";

        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        Optional<Book> bookOpt = books.stream()
                .filter(b -> title.equals(b.getTitle()))
                .findFirst();

        if (bookOpt.isPresent()) {
            Book book = bookOpt.get();
            boolean found = false;

            for (CartItem item : cart) {
                if (item.getBook().getTitle().equals(book.getTitle())) {
                    item.incrementQuantity();
                    found = true;
                    break;
                }
            }

            if (!found) {
                cart.add(new CartItem(book));
            }
        }

        session.setAttribute("cart", cart);
        return "redirect:/books";
    }

    @GetMapping("/cart")
    public String viewCart(Model model, HttpSession session) {
        List<CartItem> cart = (List<CartItem>) session.getAttribute("cart");
        if (cart == null) cart = new ArrayList<>();

        double total = cart.stream().mapToDouble(CartItem::getTotalPrice).sum();

        model.addAttribute("cart", cart);
        model.addAttribute("total", total);
        return "cart";
    }


    @PostMapping("/order")
    public String placeOrder(HttpSession session) {
        session.removeAttribute("cart");
        return "order";
    }
}