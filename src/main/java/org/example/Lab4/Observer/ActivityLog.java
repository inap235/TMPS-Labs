package org.example.Lab4.Observer;

import org.example.Lab4.Domain.Book;
import org.example.Lab4.State.BookState;

import java.util.ArrayList;
import java.util.List;

// Keeps a record of every change, for the librarian.
public class ActivityLog implements BookObserver {
    private final List<String> entries = new ArrayList<>();

    public List<String> getEntries() {
        return List.copyOf(entries);
    }

    @Override
    public void onStateChanged(Book book, BookState oldState, BookState newState) {
        String entry = book.getTitle() + ": " + oldState.getName() + " -> " + newState.getName();
        entries.add(entry);
        System.out.println("  [Log] " + entry);
    }
}
