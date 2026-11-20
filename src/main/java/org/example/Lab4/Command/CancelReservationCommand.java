package org.example.Lab4.Command;

import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;

public class CancelReservationCommand extends BookCommand {

    public CancelReservationCommand(Book book, Member member) {
        super(book, member);
    }

    @Override
    protected void perform() {
        book.cancelReservation(member);
    }

    @Override
    public String describe() {
        return member.name() + " cancels the reservation of '" + book.getTitle() + "'";
    }
}
