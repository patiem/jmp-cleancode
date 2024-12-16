package com.epa.m.clean;

import com.epa.m.clean.model.Book;
import com.epa.m.clean.model.User;
import com.epa.m.clean.service.LendingService;
import com.epa.m.clean.service.ReservationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import static org.mockito.Mockito.*;

class LibraryTest {
    @Mock
    private ReservationService reservationService;
    @Mock
    private LendingService lendingService;

    private Library library;
    private Book book;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        library = new Library(reservationService, lendingService);
        book = new Book("1");
        user = new User("user1");
    }

    @Test
    void checkOutBookSuccessfully() {
        doNothing().when(lendingService).checkOutBook(book, user);

        library.checkOutBook(book, user);

        verify(lendingService).checkOutBook(book, user);
    }

    @Test
    void checkOutBookCatchesException() {
        doThrow(new UnsupportedOperationException("Book is unavailable")).when(lendingService).checkOutBook(book, user);

        library.checkOutBook(book, user);

        verify(lendingService).checkOutBook(book, user);
    }

    @Test
    void returnBookSuccessfully() {
        doNothing().when(lendingService).returnBook(book);

        library.returnBook(book);

        verify(lendingService).returnBook(book);
    }

    @Test
    void returnBookCatchesException() {
        doThrow(new UnsupportedOperationException("Book cannot be returned")).when(lendingService).returnBook(book);

        library.returnBook(book);

        verify(lendingService).returnBook(book);
    }

    @Test
    void reserveBookSuccessfully() {
        doNothing().when(reservationService).reserveBook(book, user);

        library.reserveBook(book, user);

        verify(reservationService).reserveBook(book, user);
    }

    @Test
    void reserveBookCatchesException() {
        doThrow(new UnsupportedOperationException("Book cannot be reserved")).when(reservationService).reserveBook(book, user);

        library.reserveBook(book, user);

        verify(reservationService).reserveBook(book, user);
    }
}