package org.example.Lab4.Domain;

import org.example.Lab4.Observer.BookObserver;
import org.example.Lab4.State.AvailableState;
import org.example.Lab4.State.BookState;

import java.util.ArrayList;
import java.util.List;

// STATE: Book is the Context - it does not decide what an action does,
//        it delegates every action to its current state object.
// OBSERVER: Book is also the Subject - every state change is announced
//        to the observers that are subscribed at that moment.
public class Book {
    private final String title;
    private BookState state = new AvailableState();
    private final List<BookObserver> observers = new ArrayList<>();

    public Book(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public BookState getState() {
        return state;
    }

    // ---- actions: delegated to the current state ----

    public void borrow(Member member) {
        state.borrow(this, member);
    }

    public void returnBook(Member member) {
        state.returnBook(this, member);
    }

    public void reserve(Member member) {
        state.reserve(this, member);
    }

    public void cancelReservation(Member member) {
        state.cancelReservation(this, member);
    }

    // Called by the states when a transition is allowed, and by undo
    // to put back a previous state. Every change is announced.
    public void changeState(BookState newState) {
        BookState oldState = state;
        state = newState;
        notifyObservers(oldState, newState);
    }

    // ---- observers ----

    public void subscribe(BookObserver observer) {
        if (!observers.contains(observer)) {
            observers.add(observer);
        }
    }

    public void unsubscribe(BookObserver observer) {
        observers.remove(observer);
    }

    public boolean isSubscribed(BookObserver observer) {
        return observers.contains(observer);
    }

    private void notifyObservers(BookState oldState, BookState newState) {
        // loop over a copy, so an observer may unsubscribe while being notified
        for (BookObserver observer : List.copyOf(observers)) {
            observer.onStateChanged(this, oldState, newState);
        }
    }

    @Override
    public String toString() {
        return "'" + title + "' [" + state.describe() + "]";
    }
}
