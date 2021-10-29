package com.restdemo.adapters.rest.resource;

import com.restdemo.adapters.rest.dto.ErrorDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * Class ExceptionHandlerResource.
 */
@Slf4j
@ControllerAdvice
public class ExceptionHandlerResource extends ResponseEntityExceptionHandler {

  /**
   * Handle MethodArgumentNotValidException error.
   * @param ex MethodArgumentNotValidException
   * @param headers headers
   * @param status status
   * @param request request
   * @return ResponseEntity
   */
  @Override
  protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    var error = new ErrorDto();
    error.setCode(Integer.toString(HttpStatus.BAD_REQUEST.value()));
    error.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());
    List<FieldError> fieldErrors = ex.getBindingResult().getFieldErrors();
    List<ObjectError> globalErrors = ex.getBindingResult().getGlobalErrors();
    List<String> errors = new ArrayList<>();
    for (FieldError fieldError : fieldErrors) {
      errors.add(fieldError.getField() + ", " + fieldError.getDefaultMessage());
    }
    for (ObjectError objectError : globalErrors) {
      errors.add(objectError.getObjectName() + ", " + objectError.getDefaultMessage());
    }
    error.setDescription(errors.toString());

    log.info(ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle HttpMessageNotReadableException error.
   * @param ex HttpMessageNotReadableException
   * @param headers headers
   * @param status status
   * @param request request
   * @return ResponseEntity
   */
  @Override
  protected ResponseEntity<Object> handleHttpMessageNotReadable(
      HttpMessageNotReadableException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    var error = new ErrorDto();
    error.setCode(Integer.toString(HttpStatus.BAD_REQUEST.value()));
    error.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());
    Throwable mostSpecificCause = ex.getMostSpecificCause();
    if (mostSpecificCause.getCause() != null) {
      String exceptionName = mostSpecificCause.getClass().getName();
      String message = mostSpecificCause.getMessage();
      error.setDescription(exceptionName + ": " + message);
    } else {
      error.setDescription(ex.getMessage());
    }

    log.info(ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle HttpMediaTypeNotSupportedException error.
   * @param ex HttpMediaTypeNotSupportedException
   * @param headers headers
   * @param status status
   * @param request request
   * @return ResponseEntity
   */
  @Override
  protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
      HttpMediaTypeNotSupportedException ex,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    var error = new ErrorDto();
    error.setCode(Integer.toString(HttpStatus.BAD_REQUEST.value()));
    error.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());
    error.setDescription(ex.getMessage());

    log.info(ex.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle ServletRequestBindingException error.
   * @param e ServletRequestBindingException
   * @param headers headers
   * @param status status
   * @param request request
   * @return ResponseEntity
   */
  @Override
  protected ResponseEntity<Object> handleServletRequestBindingException(
      ServletRequestBindingException e,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    var error = new ErrorDto();
    error.setCode(Integer.toString(HttpStatus.BAD_REQUEST.value()));
    error.setTitle(HttpStatus.BAD_REQUEST.getReasonPhrase());
    error.setDescription(e.getMessage());

    log.info(e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
  }

  /**
   * Handle HttpRequestMethodNotSupportedException error.
   * @param e HttpRequestMethodNotSupportedException
   * @param headers header
   * @param status status
   * @param request request
   * @return ResponseEntity
   */
  @Override
  protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
      HttpRequestMethodNotSupportedException e,
      HttpHeaders headers,
      HttpStatus status,
      WebRequest request) {
    var error = new ErrorDto();
    error.setCode(Integer.toString(HttpStatus.METHOD_NOT_ALLOWED.value()));
    error.setTitle(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
    error.setDescription(e.getMessage());

    log.info(e.getMessage());
    return new ResponseEntity<>(error, HttpStatus.METHOD_NOT_ALLOWED);
  }

  /**
   * Handle NoSuchElementException error.
   * @param e NoSuchElementException
   * @return ResponseEntity
   */
  @ExceptionHandler(NoSuchElementException.class)
  public ResponseEntity<Object> handleNotFoundElementException(NoSuchElementException e) {
    var error = new ErrorDto();
    error.setCode(Integer.toString(HttpStatus.NOT_FOUND.value()));
    error.setTitle(HttpStatus.NOT_FOUND.getReasonPhrase());
    error.setDescription(e.getMessage());

    log.info(error.toString());
    return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
  }
}
