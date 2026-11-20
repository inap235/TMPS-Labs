package org.example.Lab4.Command;

import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;
import org.example.Lab4.State.BookState;

// Common base for the commands on one book.
// It remembers the state before the action, so undo can put it back.
// The rules (who may borrow, return, ...) are NOT here: the subclasses only
// call the Book (the Receiver), which asks its State.
public abstract class BookCommand implements LibraryCommand {
    protected final Book book;
    protected final Member member;
    private BookState stateBefore;

    protected BookCommand(Book book, Member member) {
        this.book = book;
        this.member = member;
    }

    @Override
    public final void execute() {
        stateBefore = book.getState();
        perform();
    }

    @Override
    public final void undo() {
        if (stateBefore == null) {
            throw new IllegalStateException("The command was not executed.");
        }
        book.changeState(stateBefore);
    }

    // the one call to the receiver that each command makes
    protected abstract void perform();
}
