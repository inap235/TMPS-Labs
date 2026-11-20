package org.example.Lab4.Factory;

import org.example.Lab4.Command.BorrowCommand;
import org.example.Lab4.Command.LibraryCommand;
import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;

public class BorrowCommandFactory implements CommandFactory {

    @Override
    public String getName() {
        return "Borrow";
    }

    @Override
    public LibraryCommand create(Book book, Member member) {
        return new BorrowCommand(book, member);
    }
}
