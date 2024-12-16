package com.epa.m.clean.service;

import com.epa.m.clean.model.Book;
import com.epa.m.clean.model.User;

public interface ReservationService {
    void reserveBook(Book book, User user);
}
