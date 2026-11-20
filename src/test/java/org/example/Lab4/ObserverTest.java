package org.example.Lab4;

import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;
import org.example.Lab4.Observer.ActivityLog;
import org.example.Lab4.Observer.MemberNotifier;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ObserverTest {

    private final Member ana = new Member("Ana");

    @Test
    void allSubscribersNotified() {
        Book book = new Book("Clean Code");
        ActivityLog log = new ActivityLog();
        MemberNotifier ion = new MemberNotifier(new Member("Ion"));
        book.subscribe(log);
        book.subscribe(ion);

        book.borrow(ana);

        assertEquals(List.of("Clean Code: Available -> Borrowed"), log.getEntries());
        assertEquals(List.of("'Clean Code' is now Borrowed by Ana"), ion.getInbox());
    }

    @Test
    void unsubscribedNotNotified() {
        Book book = new Book("Clean Code");
        MemberNotifier ion = new MemberNotifier(new Member("Ion"));
        book.subscribe(ion);
        book.borrow(ana);

        book.unsubscribe(ion);
        book.returnBook(ana);

        assertEquals(1, ion.getInbox().size(), "no message after unsubscribing");
    }

    @Test
    void rejectedActionNotAnnounced() {
        Book book = new Book("Clean Code");
        ActivityLog log = new ActivityLog();
        book.subscribe(log);

        try {
            book.returnBook(ana);
        } catch (IllegalStateException expected) {
            // the action is forbidden in the Available state
        }

        assertTrue(log.getEntries().isEmpty());
    }
}
