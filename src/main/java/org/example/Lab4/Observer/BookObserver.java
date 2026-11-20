package org.example.Lab4.Observer;

import org.example.Lab4.Domain.Book;
import org.example.Lab4.State.BookState;

// OBSERVER interface: the book only knows this interface,
// never the concrete observer classes.
public interface BookObserver {
    void onStateChanged(Book book, BookState oldState, BookState newState);
}
