package com.epa.m.clean.repository;

import com.epa.m.clean.model.Book;
import com.epa.m.clean.model.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;


import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class LibraryRepositoryTest {
    private LibraryRepository libraryRepository;
    private Book book;
    private User user;

    @BeforeEach
    void setUp() {
        libraryRepository = new LibraryRepository();
        book = new Book("1");
        user = new User("user1");
    }

    @Test
    void addBookSuccessfully() {
        libraryRepository.addBook(book);
        assertEquals(book, libraryRepository.findBookById("1"));
    }

    @Test
    void addBookThrowsExceptionWhenBookAlreadyExists() {
        libraryRepository.addBook(book);
        Exception exception = assertThrows(UnsupportedOperationException.class, () -> libraryRepository.addBook(book));
        assertTrue(exception.getMessage().contains("already in the registry"));
    }

    @Test
    void findBookByIdSuccessfully() {
        libraryRepository.addBook(book);
        Book foundBook = libraryRepository.findBookById("1");
        assertNotNull(foundBook);
        assertEquals("1", foundBook.id());
    }

    @Test
    void findBookByIdThrowsExceptionWhenBookNotFound() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> libraryRepository.findBookById("2"));
        assertTrue(exception.getMessage().contains("No book with id 2 in the library"));
    }

    @Test
    void addToLoanedBookSuccessfully() {
        libraryRepository.addToLoanedBook(book, user);
        assertTrue(libraryRepository.isBookLoaned(book));
    }

    @Test
    void removeFromLoanedBookSuccessfully() {
        libraryRepository.addToLoanedBook(book, user);
        libraryRepository.removeFromLoanedBook(book);
        assertFalse(libraryRepository.isBookLoaned(book));
    }

    @Test
    void addToReservedBookSuccessfully() {
        libraryRepository.addToReservedBook(book, user);
        assertTrue(libraryRepository.isBookReserved(book));
    }

    @Test
    void removeFromReservedBookSuccessfully() {
        libraryRepository.addToReservedBook(book, user);
        libraryRepository.removeFromReservedBook(book);
        assertFalse(libraryRepository.isBookReserved(book));
    }

    @Test
    void isBookReservedReturnsTrue() {
        libraryRepository.addToReservedBook(book, user);
        assertTrue(libraryRepository.isBookReserved(book));
    }

    @Test
    void isBookReservedReturnsFalse() {
        assertFalse(libraryRepository.isBookReserved(book));
    }

    @Test
    void isBookLoanedReturnsTrue() {
        libraryRepository.addToLoanedBook(book, user);
        assertTrue(libraryRepository.isBookLoaned(book));
    }

    @Test
    void isBookLoanedReturnsFalse() {
        assertFalse(libraryRepository.isBookLoaned(book));
    }
}