/*
 * package com.example.interviewbit.ecommerce.controller;
 * 
 * import org.springframework.http.ResponseEntity; import
 * org.springframework.web.bind.annotation.ControllerAdvice; import
 * org.springframework.web.bind.annotation.ExceptionHandler; import
 * org.springframework.web.context.request.WebRequest; import
 * org.springframework.web.servlet.mvc.method.annotation.
 * ResponseEntityExceptionHandler;
 * 
 * import
 * com.example.interviewbit.ecommerce.exception.UserAlreadyExistsException;
 * 
 * @ControllerAdvice public class ControllerExceptionHandler extends
 * ResponseEntityExceptionHandler {
 * 
 * @ExceptionHandler(value = {UserAlreadyExistsException.class}) protected
 * ResponseEntity<Object> handleConflict(RuntimeException ex, WebRequest
 * request) {
 * 
 * } }
 */