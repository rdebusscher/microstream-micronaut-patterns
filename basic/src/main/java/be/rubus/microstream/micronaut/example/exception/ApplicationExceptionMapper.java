package be.rubus.microstream.micronaut.example.exception;


import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpResponse;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Singleton
public class ApplicationExceptionMapper implements ExceptionHandler<BusinessException, HttpResponse<Void>> {

    @Override
    public HttpResponse<Void> handle(HttpRequest request, BusinessException exception) {
        return HttpResponse.status(HttpStatus.PRECONDITION_FAILED);
    }
}