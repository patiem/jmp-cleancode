package com.epa.m.clean;

import com.epa.m.clean.model.Book;
import com.epa.m.clean.model.User;
import com.epa.m.clean.service.LendingService;
import com.epa.m.clean.service.ReservationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Library {

    private final ReservationService reservationService;
    private final LendingService lendingService;
    private final Logger log = LoggerFactory.getLogger(getClass());


    public Library(ReservationService reservationService, LendingService lendingService) {
        this.reservationService = reservationService;
        this.lendingService = lendingService;
    }


    public void checkOutBook(Book book, User user) {
        try {
            lendingService.checkOutBook(book, user);
        } catch  (IllegalArgumentException | UnsupportedOperationException ex) {
            log.error("Unsuccessful check out operation: {}", ex.getMessage());
        }

    }


    public void returnBook(Book book) {
        try {
            lendingService.returnBook(book);
        } catch  (IllegalArgumentException | UnsupportedOperationException ex) {
            log.error("Unsuccessful book return operation: {}", ex.getMessage());
        }
    }


    public void reserveBook(Book book, User user) {
        try {
            reservationService.reserveBook(book, user);
        } catch  (IllegalArgumentException | UnsupportedOperationException ex) {
            log.error("Unsuccessful book reservation operation: {}", ex.getMessage());
        }
    }
}