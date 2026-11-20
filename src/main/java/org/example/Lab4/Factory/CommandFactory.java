package org.example.Lab4.Factory;

import org.example.Lab4.Command.LibraryCommand;
import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;

// FACTORY METHOD (reused from Lab 2): each menu action has a factory
// that creates its command. The menu is built from a list of factories,
// so Main never names a concrete command class.
public interface CommandFactory {
    String getName();

    LibraryCommand create(Book book, Member member);
}
