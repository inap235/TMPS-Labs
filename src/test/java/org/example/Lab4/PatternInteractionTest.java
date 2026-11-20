package org.example.Lab4;

import org.example.Lab4.Command.CommandHistory;
import org.example.Lab4.Command.LibraryCommand;
import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;
import org.example.Lab4.Factory.BorrowCommandFactory;
import org.example.Lab4.Factory.CommandFactory;
import org.example.Lab4.Observer.ActivityLog;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

// Factory -> Command -> State -> Observer, with the real classes.
class PatternInteractionTest {

    @Test
    void commandChangesStateAndNotifies() {
        Book book = new Book("Refactoring");
        ActivityLog log = new ActivityLog();
        book.subscribe(log);
        CommandFactory borrow = new BorrowCommandFactory();
        LibraryCommand command = borrow.create(book, new Member("Maria"));

        new CommandHistory().run(command);

        assertEquals("Borrowed by Maria", book.getState().describe());
        assertEquals(List.of("Refactoring: Available -> Borrowed"), log.getEntries());
    }

    @Test
    void undoIsAnnouncedToo() {
        Book book = new Book("Refactoring");
        ActivityLog log = new ActivityLog();
        book.subscribe(log);
        CommandHistory history = new CommandHistory();

        history.run(new BorrowCommandFactory().create(book, new Member("Maria")));
        history.undoLast();

        assertEquals(List.of("Refactoring: Available -> Borrowed", "Refactoring: Borrowed -> Available"),
                log.getEntries());
    }
}
