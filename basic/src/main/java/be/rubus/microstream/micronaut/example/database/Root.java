package be.rubus.microstream.micronaut.example.database;

import be.rubus.microstream.micronaut.example.model.Book;
import be.rubus.microstream.micronaut.example.model.User;
import one.microstream.persistence.types.Persister;

import java.util.ArrayList;
import java.util.List;

public class Root {

    private transient Persister persister;

    private final List<User> users = new ArrayList<>();
    private final List<Book> books = new ArrayList<>();

    public List<User> getUsers() {
        return new ArrayList<>(users);
    }

    public User addUser(User user) {
        users.add(user);
        persister.store(users);
        return user;
    }

    /**
     * Since the User instance is already part of the User Collection, we just need
     * to make it is stored externally.
     *
     * @param user
     */
    public void updateUser(User user) {
        persister.store(user);
    }

    public void removeUser(User user) {
        users.remove(user);
        persister.store(users);
    }

    public List<Book> getBooks() {
        return new ArrayList<>(books);
    }

    public void addBook(Book book) {
        books.add(book);
        persister.store(books);
    }

    /**
     * User instance must already be part of the Object graph of the root managed by MicroStream.
     *
     * @param user
     * @param book
     */
    public void addBookToUser(User user, Book book) {
        user.addBook(book, persister);
    }

    public void setPersister(Persister persister) {
        this.persister = persister;
    }
}
