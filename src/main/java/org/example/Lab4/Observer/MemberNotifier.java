package org.example.Lab4.Observer;

import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;
import org.example.Lab4.State.BookState;

import java.util.ArrayList;
import java.util.List;

// Sends a message to a member who follows a book
// (for example, to know when it becomes available again).
public class MemberNotifier implements BookObserver {
    private final Member member;
    private final List<String> inbox = new ArrayList<>();

    public MemberNotifier(Member member) {
        this.member = member;
    }

    public Member getMember() {
        return member;
    }

    public List<String> getInbox() {
        return List.copyOf(inbox);
    }

    @Override
    public void onStateChanged(Book book, BookState oldState, BookState newState) {
        String message = "'" + book.getTitle() + "' is now " + newState.describe();
        inbox.add(message);
        System.out.println("  [Notify " + member.name() + "] " + message);
    }
}
