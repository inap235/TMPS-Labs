package org.example.Lab4.Command;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

// INVOKER: runs commands and keeps the successful ones in a stack,
// so the last action can be undone. It works only with LibraryCommand,
// so it does not know which concrete actions exist.
public class CommandHistory {
    private final Deque<LibraryCommand> done = new ArrayDeque<>();

    public boolean run(LibraryCommand command) {
        System.out.println("> " + command.describe());
        try {
            command.execute();
        } catch (IllegalStateException e) {
            System.out.println("  Rejected: " + e.getMessage());
            return false;           // a rejected command is not saved
        }
        done.push(command);
        return true;
    }

    public boolean undoLast() {
        if (done.isEmpty()) {
            System.out.println("  Nothing to undo.");
            return false;
        }
        LibraryCommand command = done.pop();
        System.out.println("< Undo: " + command.describe());
        command.undo();
        return true;
    }

    public int size() {
        return done.size();
    }

    // newest first
    public List<String> descriptions() {
        return done.stream().map(LibraryCommand::describe).toList();
    }
}
