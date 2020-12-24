package edu.miu.cs544.exception;

import java.util.Collection;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import edu.miu.cs544.service.response.ExceptionResponse;
import edu.miu.cs544.service.response.TicketResponse;

@ControllerAdvice
public class ControllerAdviceExceptionHandler extends ResponseEntityExceptionHandler {
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@ExceptionHandler(InvalidIdException.class)
	public ResponseEntity<ExceptionResponse> handleInvalidId(InvalidIdException ex) {
		logger.info("Start of handleInvalidId");
		String message = ex.getMessage();
		ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(), HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), message);
		logger.info("End of handleInvalidId");

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler(InvalidTicketRequestException.class)
	public ResponseEntity<Collection<TicketResponse>> handleInvalidTicketRequestException(InvalidTicketRequestException ex) {
		logger.info("Start of ResponseStatusException");
		String message = ex.getMessage();
		logger.info("End of ResponseStatusException");
		return ResponseEntity.ok(ex.getTickets());
	}
	
	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<String> handleResponseStatusException(ResponseStatusException ex) {
		logger.info("Start of ResponseStatusException");
		String message = ex.getReason();
		logger.info("End of ResponseStatusException");
		return new ResponseEntity<>(message, ex.getStatus());
	}
}


