package com.epa.m.clean.repository;

import com.epa.m.clean.model.Book;
import com.epa.m.clean.model.User;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CopyOnWriteArrayList;

public class LibraryRepository implements BookRepository {
    private final List<Book> booksRegistry = new CopyOnWriteArrayList<>();
    private final Map<Book, User> loaned = new HashMap<>();
    private final Map<Book, User> reserved = new HashMap<>();

    @Override
    public void addBook(Book book){
        if(!booksRegistry.contains(book)){
            booksRegistry.add(book);
        } else {
            throw new UnsupportedOperationException(String.format("Book with id %s already in the registry.", book.id()));
        }
    }

    @Override
    public Book findBookById(String bookId){
        return booksRegistry.stream()
                .filter(book -> bookId.equals(book.id()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(String.format("No book with id %s in the library", bookId)));
    }

    @Override
    public void addToLoanedBook(Book book, User loanedUser) {
        loaned.put(book, loanedUser);

    }

    @Override
    public void removeFromLoanedBook(Book book) {
        loaned.remove(book);

    }

    @Override
    public void addToReservedBook(Book book, User reservedUser) {
        reserved.put(book, reservedUser);

    }

    @Override
    public void removeFromReservedBook(Book book) {
        reserved.remove(book);
    }

    @Override
    public boolean isBookReserved(Book book) {
        return reserved.containsKey(book);
    }

    @Override
    public boolean isBookLoaned(Book book) {
        return loaned.containsKey(book);
    }

}