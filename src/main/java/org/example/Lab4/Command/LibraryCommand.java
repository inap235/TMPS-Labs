package org.example.Lab4.Command;

// COMMAND interface: a librarian action turned into an object,
// so it can be kept in a history and undone later.
public interface LibraryCommand {
    // throws IllegalStateException if the book's state does not allow the action
    void execute();

    void undo();

    String describe();
}
