package org.example.Lab4.State;

import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;

// The book is kept for one member: only that member can borrow it
// or cancel the reservation.
public class ReservedState implements BookState {
    private final Member reservedFor;

    public ReservedState(Member reservedFor) {
        this.reservedFor = reservedFor;
    }

    @Override
    public String getName() {
        return "Reserved";
    }

    @Override
    public String describe() {
        return "Reserved for " + reservedFor.name();
    }

    @Override
    public void borrow(Book book, Member member) {
        if (!member.equals(reservedFor)) {
            throw new IllegalStateException("'" + book.getTitle() + "' is reserved for " + reservedFor.name() + ".");
        }
        book.changeState(new BorrowedState(member));
    }

    @Override
    public void returnBook(Book book, Member member) {
        throw new IllegalStateException("'" + book.getTitle() + "' is not borrowed.");
    }

    @Override
    public void reserve(Book book, Member member) {
        throw new IllegalStateException("'" + book.getTitle() + "' is already reserved for " + reservedFor.name() + ".");
    }

    @Override
    public void cancelReservation(Book book, Member member) {
        if (!member.equals(reservedFor)) {
            throw new IllegalStateException("Only " + reservedFor.name() + " can cancel this reservation.");
        }
        book.changeState(new AvailableState());
    }
}
