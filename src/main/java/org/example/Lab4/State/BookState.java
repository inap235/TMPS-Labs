package org.example.Lab4.State;

import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;

// STATE interface: every state answers every action.
// An allowed action moves the book to the next state (book.changeState(...)),
// a forbidden one throws IllegalStateException and the book stays as it was.
public interface BookState {
    String getName();

    // name with details, for example "Borrowed by Ana"
    String describe();

    void borrow(Book book, Member member);

    void returnBook(Book book, Member member);

    void reserve(Book book, Member member);

    void cancelReservation(Book book, Member member);
}
