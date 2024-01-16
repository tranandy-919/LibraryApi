package com.li.rest.controller;

import com.li.rest.model.Book;
import com.li.rest.repo.BookRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ApiController {

    @Autowired
    private BookRepo bookRepo;

    @GetMapping("/")
    public String getPage() {
        return "Welcome";
    }

    @GetMapping("/books")
    public List<Book> getBooks() {
        return bookRepo.findAll();
    }

    @PostMapping("/save")
    public String saveBook(@RequestBody Book book) {
        bookRepo.save(book);
        return "Book saved to library";
    }

    @PutMapping("/update/{id}")
    public String updateBook(@PathVariable long id, @RequestBody Book book) {
        Book updatedBook = bookRepo.findById(id).get();
        updatedBook.setTitle(book.getTitle());
        updatedBook.setAuthorFirstName(book.getAuthorFirstName());
        updatedBook.setAuthorLastName(book.getAuthorLastName());
        updatedBook.setYear(book.getYear());
        bookRepo.save(updatedBook);
        return "Updated book details";
    }

    @DeleteMapping("delete/{id}")
    public String deleteBook(@PathVariable long id) {
        Book deleteBook = bookRepo.findById(id).get();
        bookRepo.delete(deleteBook);
        return "Deleted the book with id: " + id;
    }
}