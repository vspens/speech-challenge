package com.project.speech.handler;

import com.project.speech.response.ResponseHandler;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Vince Spencer Historia
 */
@ControllerAdvice
public class ValidationHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles validation messages for invalid parameters
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return ResponseEntity<Object>
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) ->{
            String fieldName = ((FieldError) error).getField();
            String message = error.getDefaultMessage();
            errors.put(fieldName, message);
        });
        return ResponseHandler.generateResponse("Validation error", HttpStatus.BAD_REQUEST, errors);
    }

    /**
     * Handles missing parameters
     * @param ex
     * @param headers
     * @param status
     * @param request
     * @return ResponseEntity<Object>
     */
    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
            MissingServletRequestParameterException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return ResponseHandler.generateResponse(ex.getMessage(), HttpStatus.BAD_REQUEST, null);
    }
}
