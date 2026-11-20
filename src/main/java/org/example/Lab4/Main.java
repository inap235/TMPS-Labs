package org.example.Lab4;

import org.example.Lab4.Command.CommandHistory;
import org.example.Lab4.Command.LibraryCommand;
import org.example.Lab4.Domain.Book;
import org.example.Lab4.Domain.Member;
import org.example.Lab4.Factory.BorrowCommandFactory;
import org.example.Lab4.Factory.CancelReservationCommandFactory;
import org.example.Lab4.Factory.CommandFactory;
import org.example.Lab4.Factory.ReserveCommandFactory;
import org.example.Lab4.Factory.ReturnCommandFactory;
import org.example.Lab4.Observer.ActivityLog;
import org.example.Lab4.Observer.BookObserver;
import org.example.Lab4.Observer.MemberNotifier;

import java.util.List;
import java.util.Scanner;

public class Main {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        List<Book> books = List.of(new Book("Clean Code"), new Book("Design Patterns"), new Book("Refactoring"));
        List<Member> members = List.of(new Member("Ana"), new Member("Ion"), new Member("Maria"));
        List<String> bookTitles = books.stream().map(Book::getTitle).toList();
        List<String> memberNames = members.stream().map(Member::name).toList();
        // one notifier per member; the user decides at runtime which books each member follows
        List<MemberNotifier> notifiers = members.stream().map(MemberNotifier::new).toList();
        // to add a new action, write its command and factory and add the factory to this list
        List<CommandFactory> actions = List.of(new BorrowCommandFactory(), new ReturnCommandFactory(),
                new ReserveCommandFactory(), new CancelReservationCommandFactory());

        BookObserver log = new ActivityLog();
        books.forEach(book -> book.subscribe(log));
        CommandHistory history = new CommandHistory();

        System.out.println("=== City Library ===");
        while (true) {
            printBooks(books);
            printMenu(actions);
            String choice = scanner.next().trim().toLowerCase();

            if (choice.equals("q")) {
                System.out.println("Bye!");
                return;
            } else if (choice.equals("u")) {
                history.undoLast();
            } else if (choice.equals("h")) {
                System.out.println("History (newest first): " + history.descriptions());
            } else if (choice.equals("f") || choice.equals("x")) {
                MemberNotifier notifier = notifiers.get(pick("Member", memberNames));
                Book book = books.get(pick("Book", bookTitles));
                if (choice.equals("f")) {
                    book.subscribe(notifier);
                    System.out.println("  " + notifier.getMember().name() + " now follows '" + book.getTitle() + "'.");
                } else {
                    book.unsubscribe(notifier);
                    System.out.println("  " + notifier.getMember().name() + " no longer follows '" + book.getTitle() + "'.");
                }
            } else if (choice.matches("\\d+") && Integer.parseInt(choice) >= 1 && Integer.parseInt(choice) <= actions.size()) {
                CommandFactory action = actions.get(Integer.parseInt(choice) - 1);
                Book book = books.get(pick("Book", bookTitles));
                Member member = members.get(pick("Member", memberNames));
                LibraryCommand command = action.create(book, member);
                history.run(command);
            } else {
                System.out.println("Unknown option.");
            }
        }
    }

    private static void printBooks(List<Book> books) {
        System.out.println();
        System.out.println("Books:");
        for (int i = 0; i < books.size(); i++) {
            System.out.println("  " + (i + 1) + ") " + books.get(i));
        }
    }

    private static void printMenu(List<CommandFactory> actions) {
        StringBuilder menu = new StringBuilder("Actions:");
        for (int i = 0; i < actions.size(); i++) {
            menu.append("  ").append(i + 1).append(") ").append(actions.get(i).getName());
        }
        System.out.println(menu);
        System.out.println("         u) Undo  h) History  f) Follow a book  x) Unfollow a book  q) Quit");
    }

    // shows the names and asks for a number between 1 and names.size()
    private static int pick(String what, List<String> names) {
        StringBuilder prompt = new StringBuilder(what + ":");
        for (int i = 0; i < names.size(); i++) {
            prompt.append("  ").append(i + 1).append(") ").append(names.get(i));
        }
        System.out.println(prompt);
        while (true) {
            if (scanner.hasNextInt()) {
                int n = scanner.nextInt();
                if (n >= 1 && n <= names.size()) {
                    return n - 1;
                }
            } else {
                scanner.next();
            }
            System.out.println("Please enter a number from 1 to " + names.size() + ".");
        }
    }
}
