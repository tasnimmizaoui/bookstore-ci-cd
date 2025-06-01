package com.example.bookstore.services;

import com.example.bookstore.models.Book;
import com.example.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    public Book getBookById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Book not found"));
    }

    public Book saveBook(Book book) {
        return bookRepository.save(book);
    }

    /**
     * Updates a book's stock level when an order is placed
     * @param bookId The ID of the book
     * @param quantity The quantity to reduce from stock
     * @return The updated book
     */
    @Transactional
    public Book updateStock(Long bookId, int quantity) {
        Book book = getBookById(bookId);

        if (book.getStock() < quantity) {
            throw new RuntimeException("Pas assez de stock disponible pour " + book.getTitle());
        }

        book.setStock(book.getStock() - quantity);
        return bookRepository.save(book);
    }
}