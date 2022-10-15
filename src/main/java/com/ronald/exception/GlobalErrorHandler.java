package com.ronald.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalErrorHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleAllExceptions(ModelNotFoundException ex, WebRequest req){
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(),ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.INTERNAL_SERVER_ERROR);
    }
    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleModelNotFound(ModelNotFoundException ex, WebRequest req){
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(),ex.getMessage(), req.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
    }
    //Sirve para Validar los campos que se mandan en la peticion
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        String message = ex.getBindingResult().getFieldErrors().stream()
                            .map(err->err.getField()+": "+err.getDefaultMessage()).collect(Collectors.joining(" "));
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(),message, request.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.BAD_REQUEST);
        //return super.handleMethodArgumentNotValid(ex, headers, status, request);
    }
    //Sirve para validar las rutas que no se encuentran
    //En properties Agregar lo siguiente
    //spring.mvc.throw-exception-if-no-handler-found=true
    //spring.mvc.static-path-pattern=/static
    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        ErrorResponse res = new ErrorResponse(LocalDateTime.now(),"NOT FOUND", request.getDescription(false));
        return new ResponseEntity<>(res, HttpStatus.NOT_FOUND);
        //return super.handleNoHandlerFoundException(ex, headers, status, request);
    }
}
