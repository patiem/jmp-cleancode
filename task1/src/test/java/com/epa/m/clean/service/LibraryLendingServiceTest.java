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

class LibraryLendingServiceTest {
    @Mock
    private BookRepository bookRepository;

    private LibraryLendingService lendingService;
    private Book book;
    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        lendingService = new LibraryLendingService(bookRepository);
        book = new Book("1");
        user = new User("user1");
    }

    @Test
    void checkOutBookSuccessfully() {
        when(bookRepository.findBookById("1")).thenReturn(book);
        when(bookRepository.isBookLoaned(book)).thenReturn(false);

        lendingService.checkOutBook(book, user);

        verify(bookRepository).addToLoanedBook(book, user);
        verify(bookRepository, never()).removeFromReservedBook(book);
    }

    @Test
    void checkOutBookWhenBookIsReserved() {
        when(bookRepository.findBookById("1")).thenReturn(book);
        when(bookRepository.isBookLoaned(book)).thenReturn(false);
        when(bookRepository.isBookReserved(book)).thenReturn(true);

        lendingService.checkOutBook(book, user);

        verify(bookRepository).addToLoanedBook(book, user);
        verify(bookRepository).removeFromReservedBook(book);
    }

    @Test
    void checkOutBookThrowsExceptionWhenBookIsNotAvailable() {
        when(bookRepository.findBookById("1")).thenReturn(book);
        when(bookRepository.isBookLoaned(book)).thenReturn(true);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> lendingService.checkOutBook(book, user));
        assertTrue(exception.getMessage().contains("is on load"));
    }

    @Test
    void returnBookSuccessfully() {
        when(bookRepository.isBookLoaned(book)).thenReturn(true);

        lendingService.returnBook(book);

        verify(bookRepository).removeFromLoanedBook(book);
    }

    @Test
    void returnBookThrowsExceptionWhenBookIsNotLoaned() {
        when(bookRepository.isBookLoaned(book)).thenReturn(false);

        Exception exception = assertThrows(UnsupportedOperationException.class, () -> lendingService.returnBook(book));
        assertTrue(exception.getMessage().contains("is not on loan - cannot be returned"));
    }
}