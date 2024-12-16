package com.epa.m.clean.service;

import com.epa.m.clean.model.Book;
import com.epa.m.clean.model.User;
import com.epa.m.clean.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LibraryReservationService implements ReservationService {

    static final Logger log = LoggerFactory.getLogger(LibraryReservationService.class);
    private final BookRepository bookRepository;

    public LibraryReservationService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void reserveBook(Book book, User user) {
        if(!bookRepository.isBookLoaned(book) || bookRepository.isBookReserved(book)) {
            bookRepository.addToReservedBook(book, user);
            log.info("Book: {} reserved for the user {}", book.id(), user.id());
        } else {
            throw new UnsupportedOperationException(String.format("Book: %s cannot be currently reserved.", book.id()));
        }
    }
}
