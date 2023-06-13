package be.rubus.microstream.micronaut.example.controller;


import be.rubus.microstream.micronaut.example.model.Book;
import be.rubus.microstream.micronaut.example.service.BookService;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.PathVariable;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Inject;

import java.util.Collection;
import java.util.Optional;


@Controller("/book")
public class BookController {

    @Inject
    private BookService bookService;

    @Get()
@Produces(MediaType.APPLICATION_JSON)
    public Collection<Book> getAll() {
        return bookService.getAll();
    }


    @Get("{isbn}")
    public HttpResponse<Book> getByIsbn(@PathVariable("isbn") String isbn) {
        Optional<Book> byISBN = bookService.getBookByISBN(isbn);
        if (byISBN.isEmpty()) {
            return HttpResponse.notFound();
        } else {
            return HttpResponse.ok(byISBN.get());
        }
    }
}
