package com.crud.crud.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
// import org.springframework.http.HttpStatus;



@ControllerAdvice
public class GlobalExeptionHandler {
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleNotFound(RuntimeException e){ // catch on runtime with a clear message
        // service throws RuntimeException -> ExceptionHandler catches it and returns a 404
        return ResponseEntity.status(404).body(e.getMessage());
    }

    // thats magic, when a service throws an error (the error itself gets handled by the exception with the same class)
    // in the controller we throwed a runtime exception and here we made a handler for it that automatically 
    // knows that this is the method for it and execute it based on the given error
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<String> handleValidation(MethodArgumentNotValidException e){
        String errorMessage =e.getBindingResult().getFieldErrors().get(0).getDefaultMessage();

        return ResponseEntity.badRequest().body(errorMessage);
    }


    
}
