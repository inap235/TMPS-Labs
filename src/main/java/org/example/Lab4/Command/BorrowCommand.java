package org.example.Lab4.Command;

import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;

public class BorrowCommand extends BookCommand {

    public BorrowCommand(Book book, Member member) {
        super(book, member);
    }

    @Override
    protected void perform() {
        book.borrow(member);
    }

    @Override
    public String describe() {
        return member.name() + " borrows '" + book.getTitle() + "'";
    }
}
