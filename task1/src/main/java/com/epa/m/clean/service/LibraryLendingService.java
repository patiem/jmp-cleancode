package com.epa.m.clean.service;

import com.epa.m.clean.model.Book;
import com.epa.m.clean.model.User;
import com.epa.m.clean.repository.BookRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Objects;

public class LibraryLendingService implements LendingService {

    private static final Logger log = LoggerFactory.getLogger(LibraryLendingService.class);
    private final BookRepository bookRepository;

    public LibraryLendingService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public void checkOutBook(Book book, User user) {
        if(isAvailable (book)){
            bookRepository.addToLoanedBook(book, user);
            log.info("Book: {} checked out to user {}", book.id(), user.id());
            if (bookRepository.isBookReserved(book)){
                bookRepository.removeFromReservedBook(book);
                log.info("Reservation of the book: {} for the user {} canceled.", book.id(), user.id());
            }
        } else {
            throw new UnsupportedOperationException(String.format("Book: %s is on load.", book.id()));
        }
    }

    private boolean isAvailable(Book book) {
        return Objects.nonNull(bookRepository.findBookById(book.id()))
                && !bookRepository.isBookLoaned(book);
    }

    @Override
    public void returnBook(Book book) {
        if(bookRepository.isBookLoaned(book)) {
            bookRepository.removeFromLoanedBook(book);
            log.info("Book: {} returned.", book.id());
        } else {
            throw new UnsupportedOperationException(String.format("Book with id: %s is not on loan - cannot be returned.", book.id()));
        }
    }
}
