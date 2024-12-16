package com.epa.m.clean.service;

import com.epa.m.clean.model.Book;
import com.epa.m.clean.model.User;
import com.epa.m.clean.repository.BookRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

class LibraryReservationServiceTest {
    @Mock
    private BookRepository bookRepository;

    private LibraryReservationService reservationService;
    private Book book;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        reservationService = new LibraryReservationService(bookRepository);
        book = new Book("1");
        user = new User("user1");
    }

    @Test
    void reserveBookSuccessfullyWhenBookIsNotLoaned() {
        when(bookRepository.isBookLoaned(book)).thenReturn(false);
        when(bookRepository.isBookReserved(book)).thenReturn(false);

        reservationService.reserveBook(book, user);

        verify(bookRepository).addToReservedBook(book, user);
    }

    @Test
    void reserveBookSuccessfullyWhenBookIsAlreadyReserved() {
        when(bookRepository.isBookLoaned(book)).thenReturn(false);
        when(bookRepository.isBookReserved(book)).thenReturn(true);

        reservationService.reserveBook(book, user);

        verify(bookRepository).addToReservedBook(book, user);
    }

    @Test
    void reserveBookThrowsExceptionWhenBookIsLoaned() {
        when(bookRepository.isBookLoaned(book)).thenReturn(true);
        when(bookRepository.isBookReserved(book)).thenReturn(false);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> reservationService.reserveBook(book, user));
        assertTrue(exception.getMessage().contains("cannot be currently reserved"));
    }

    @Test
    void reserveBookDoesNotThrowExceptionWhenBookIsLoanedAndReserved() {
        when(bookRepository.isBookLoaned(book)).thenReturn(true);
        when(bookRepository.isBookReserved(book)).thenReturn(true);

        reservationService.reserveBook(book, user);

        verify(bookRepository).addToReservedBook(book, user);
    }
}