package ua.nure.proposalservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.webjars.NotFoundException;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ResponseBody
@ControllerAdvice
public class ExceptionController {
    @ExceptionHandler(NoSuchElementException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Map<String, String> notFoundHandler(Exception exc) {
        Map<String, String> response = new LinkedHashMap<>();

        response.put(exc.getClass().getSimpleName(), "Proposal id wasn't found");
        response.put(
                "StackTrace",
                Arrays.stream(exc.getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.joining()));

        return response;
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Map<String, String> universalHandler(Exception exc) {
        Map<String, String> response = new LinkedHashMap<>();

        response.put(exc.getClass().getSimpleName(), exc.getMessage());
        response.put(
                "StackTrace",
                Arrays.stream(exc.getStackTrace())
                        .map(StackTraceElement::toString)
                        .collect(Collectors.joining()));

        return response;
    }
}
