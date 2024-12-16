package com.epa.m.clean.repository;

import com.epa.m.clean.model.Book;
import com.epa.m.clean.model.User;

import java.util.List;

public interface BookRepository {

    void addBook(Book book);
    Book findBookById(String bookId);
    void addToLoanedBook(Book book, User user);
    void removeFromLoanedBook(Book book);
    void addToReservedBook(Book book, User user);
    void removeFromReservedBook(Book book);

    boolean isBookReserved(Book book);
    boolean isBookLoaned(Book book);
}