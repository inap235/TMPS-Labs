package org.example.Lab4.Factory;

import org.example.Lab4.Command.LibraryCommand;
import org.example.Lab4.Command.ReturnCommand;
import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;

public class ReturnCommandFactory implements CommandFactory {

    @Override
    public String getName() {
        return "Return";
    }

    @Override
    public LibraryCommand create(Book book, Member member) {
        return new ReturnCommand(book, member);
    }
}
