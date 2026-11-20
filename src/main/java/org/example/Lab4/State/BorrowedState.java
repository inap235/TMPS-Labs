package org.example.Lab4.State;

import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;

// The book is out: only the borrower can bring it back.
public class BorrowedState implements BookState {
    private final Member borrower;

    public BorrowedState(Member borrower) {
        this.borrower = borrower;
    }

    @Override
    public String getName() {
        return "Borrowed";
    }

    @Override
    public String describe() {
        return "Borrowed by " + borrower.name();
    }

    @Override
    public void borrow(Book book, Member member) {
        throw new IllegalStateException("'" + book.getTitle() + "' is already borrowed by " + borrower.name() + ".");
    }

    @Override
    public void returnBook(Book book, Member member) {
        if (!member.equals(borrower)) {
            throw new IllegalStateException("'" + book.getTitle() + "' was borrowed by " + borrower.name() + ", not by " + member.name() + ".");
        }
        book.changeState(new AvailableState());
    }

    @Override
    public void reserve(Book book, Member member) {
        throw new IllegalStateException("'" + book.getTitle() + "' is borrowed; it can be reserved after it is returned.");
    }

    @Override
    public void cancelReservation(Book book, Member member) {
        throw new IllegalStateException("'" + book.getTitle() + "' is not reserved.");
    }
}
