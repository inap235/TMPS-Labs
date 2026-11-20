package org.example.Lab4.Factory;

import org.example.Lab4.Command.CancelReservationCommand;
import org.example.Lab4.Command.LibraryCommand;
import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;

public class CancelReservationCommandFactory implements CommandFactory {

    @Override
    public String getName() {
        return "Cancel reservation";
    }

    @Override
    public LibraryCommand create(Book book, Member member) {
        return new CancelReservationCommand(book, member);
    }
}
