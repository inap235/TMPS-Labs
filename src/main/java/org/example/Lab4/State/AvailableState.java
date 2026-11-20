package org.example.Lab4.State;

import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;

// The book is on the shelf: anyone can borrow or reserve it.
public class AvailableState implements BookState {

    @Override
    public String getName() {
        return "Available";
    }

    @Override
    public String describe() {
        return "Available";
    }

    @Override
    public void borrow(Book book, Member member) {
        book.changeState(new BorrowedState(member));
    }

    @Override
    public void returnBook(Book book, Member member) {
        throw new IllegalStateException("'" + book.getTitle() + "' is not borrowed.");
    }

    @Override
    public void reserve(Book book, Member member) {
        book.changeState(new ReservedState(member));
    }

    @Override
    public void cancelReservation(Book book, Member member) {
        throw new IllegalStateException("'" + book.getTitle() + "' is not reserved.");
    }
}
