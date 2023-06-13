package be.rubus.microstream.micronaut.example.dto;

import io.micronaut.core.annotation.Introspected;
import io.micronaut.serde.annotation.Serdeable;
import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;


@Serdeable
public class CreateUser {

    private final String name;
    private String email;

    @JsonbCreator
    public CreateUser(@JsonbProperty("name") String name, @JsonbProperty("email") String email) {
        this.name = name;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
