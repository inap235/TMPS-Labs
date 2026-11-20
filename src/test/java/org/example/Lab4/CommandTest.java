package org.example.Lab4;

import org.example.Lab4.Command.BorrowCommand;
import org.example.Lab4.Command.CommandHistory;
import org.example.Lab4.Command.ReserveCommand;
import org.example.Lab4.Command.ReturnCommand;
import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CommandTest {

    private final Member ana = new Member("Ana");
    private final Member ion = new Member("Ion");

    @Test
    void undoRestoresState() {
        Book book = new Book("Clean Code");
        CommandHistory history = new CommandHistory();

        assertTrue(history.run(new BorrowCommand(book, ana)));
        assertEquals("Borrowed by Ana", book.getState().describe());

        assertTrue(history.undoLast());
        assertEquals("Available", book.getState().describe());
    }

    @Test
    void undoGoesBackToReserved() {
        // undo must return to the exact previous state, not just "Available"
        Book book = new Book("Clean Code");
        CommandHistory history = new CommandHistory();

        history.run(new ReserveCommand(book, ana));
        history.run(new BorrowCommand(book, ana));
        history.undoLast();
        assertEquals("Reserved for Ana", book.getState().describe());

        history.undoLast();
        assertEquals("Available", book.getState().describe());
        assertFalse(history.undoLast(), "nothing left to undo");
    }

    @Test
    void rejectedNotInHistory() {
        Book book = new Book("Clean Code");
        CommandHistory history = new CommandHistory();
        history.run(new BorrowCommand(book, ana));

        assertFalse(history.run(new ReturnCommand(book, ion)));
        assertEquals(1, history.size(), "only the successful command is saved");
        assertEquals("Borrowed by Ana", book.getState().describe());
    }
}
