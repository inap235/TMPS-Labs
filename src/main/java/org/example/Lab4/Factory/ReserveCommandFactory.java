package org.example.Lab4.Factory;

import org.example.Lab4.Command.LibraryCommand;
import org.example.Lab4.Command.ReserveCommand;
import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;

public class ReserveCommandFactory implements CommandFactory {

    @Override
    public String getName() {
        return "Reserve";
    }

    @Override
    public LibraryCommand create(Book book, Member member) {
        return new ReserveCommand(book, member);
    }
}
