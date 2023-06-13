package be.rubus.microstream.micronaut.example.controller;

import be.rubus.microstream.micronaut.example.dto.CreateUser;
import be.rubus.microstream.micronaut.example.model.Book;
import be.rubus.microstream.micronaut.example.model.User;
import be.rubus.microstream.micronaut.example.service.UserBookService;
import be.rubus.microstream.micronaut.example.service.UserService;
import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.*;
import io.micronaut.json.tree.JsonNode;
import io.micronaut.json.tree.JsonObject;
import jakarta.inject.Inject;

import java.net.URI;
import java.util.Collection;
import java.util.List;
import java.util.Optional;


@Controller("/user")
public class UserController {


    @Inject
    private UserService userService;

    @Inject
    private UserBookService userBookService;

    @Get
    public Collection<User> getAll() {
        return userService.getAll();
    }

    @Get("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public HttpResponse<User> getById(@PathVariable("id") String id) {
        Optional<User> byId = userService.getById(id);

        return byId.map(HttpResponse::ok)
                .orElseGet(() -> HttpResponse.notFound());

    }

    @Get("/by/{email}")
    public HttpResponse<User> findBy(@PathVariable("email") String email) {
        Optional<User> byEmail = userService.findByEmail(email);

        return byEmail.map(HttpResponse::ok)
                .orElseGet(() -> HttpResponse.notFound());
    }

    @Post
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public HttpResponse<User> addUser(CreateUser user, HttpRequest<?> request) {
        User savedUser = userService.add(user);
        URI uri = URI.create(request.getPath() + "/" + savedUser.getId());
        // Return a response with status created and the URI for the newly created resource
        return HttpResponse.created(uri).body(savedUser);
    }


    @Delete("/{id}")
    public HttpResponse<Void> deleteUser(@PathVariable("id") String id) {
        userService.removeById(id);
        return HttpResponse.noContent();
    }


    @Patch("/{id}")
    public HttpResponse<User> updateEmail(@PathVariable("id") String id, JsonObject json) {
        JsonNode email = json.get("email");
        if (email == null || email.getStringValue().isBlank()) {
            return HttpResponse.status(HttpStatus.PRECONDITION_FAILED);
        }

        User user = userService.updateEmail(id, email.getStringValue());
        return HttpResponse.ok(user);
    }

    @Get("/{id}/book")
    public HttpResponse<List<Book>> getUserBooks(@PathVariable("id") String id) {
        Optional<User> byId = userService.getById(id);
        if (byId.isEmpty()) {
            return HttpResponse.notFound();
        }
        return HttpResponse.ok(byId.get().getBooks());
    }

    @Post(value = "/{id}/book/{isbn}")
    public void addBookToUser(@PathVariable("id") String id, @PathVariable("isbn") String isbn) {
        userBookService.addBookToUser(id, isbn);
    }
}
