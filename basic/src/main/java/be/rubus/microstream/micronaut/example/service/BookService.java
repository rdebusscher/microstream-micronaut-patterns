package be.rubus.microstream.micronaut.example.service;

import be.rubus.microstream.micronaut.example.database.Root;
import be.rubus.microstream.micronaut.example.model.Book;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import one.microstream.storage.types.StorageManager;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Singleton
public class BookService extends AbstractService{

    public List<Book> getAll() {
        return root.getBooks();
    }

    public Optional<Book> getBookByISBN(String isbn) {
        return root.getBooks().stream()
                .filter(b -> b.getIsbn().equals(isbn))
                .findAny();
    }

}
