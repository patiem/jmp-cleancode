package com.epa.m.clean.service;

import com.epa.m.clean.model.Book;
import com.epa.m.clean.model.User;

public interface LendingService {
    void checkOutBook(Book book, User user);
    void returnBook(Book book);
}
