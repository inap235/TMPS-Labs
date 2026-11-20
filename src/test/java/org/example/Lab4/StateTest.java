package org.example.Lab4;

import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StateTest {

    private final Member ana = new Member("Ana");
    private final Member ion = new Member("Ion");

    @Test
    void validTransitions() {
        Book book = new Book("Clean Code");
        assertEquals("Available", book.getState().getName());

        book.reserve(ana);
        assertEquals("Reserved", book.getState().getName());
        book.borrow(ana);
        assertEquals("Borrowed", book.getState().getName());
        book.returnBook(ana);
        assertEquals("Available", book.getState().getName());
    }

    @Test
    void rejectsForbidden() {
        Book book = new Book("Clean Code");

        assertThrows(IllegalStateException.class, () -> book.returnBook(ana));
        assertEquals("Available", book.getState().getName(), "a rejected action must not change the state");

        book.borrow(ana);
        assertThrows(IllegalStateException.class, () -> book.borrow(ion));
        assertThrows(IllegalStateException.class, () -> book.returnBook(ion));
        assertEquals("Borrowed by Ana", book.getState().describe());
    }

    @Test
    void reservedOnlyForReserver() {
        Book book = new Book("Clean Code");
        book.reserve(ana);

        assertThrows(IllegalStateException.class, () -> book.borrow(ion));
        assertThrows(IllegalStateException.class, () -> book.cancelReservation(ion));
        assertEquals("Reserved for Ana", book.getState().describe());
    }
}
