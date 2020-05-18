package exception;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

@Controller
@ControllerAdvice
public class GlobalExceptionHandler{
	
	// Catch all exception
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ResponseMessage> exceptionHandler(Exception ex) {
		System.out.println(ex.getClass());
		ResponseMessage rm = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), ex.getMessage());
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.BAD_REQUEST);
	}
	
	// Catch Member validation exception
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ResponseMessage> exceptionHandler(MethodArgumentNotValidException ex) {
		StringBuilder sb = new StringBuilder();
		List<FieldError> errors = ex.getBindingResult().getFieldErrors();
		for(FieldError error: errors)
			sb.append("<").append(error.getDefaultMessage()).append("> ");
		
		ResponseMessage rm = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), sb.toString());
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.BAD_REQUEST);
	}
	
	// Catch invalid type or type cast exception
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ResponseMessage> exceptionHandler(MethodArgumentTypeMismatchException ex) {
		ResponseMessage rm = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), ex.getMessage());
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.BAD_REQUEST);
	}
	
	// Catch CustomException
	@ExceptionHandler(CustomException.class)
	public ResponseEntity<ResponseMessage> exceptionHandler(CustomException ex) {
		ResponseMessage rm = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), ex.getMessage());
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.BAD_REQUEST);
	}
	
	@GetMapping("error")
	public ResponseEntity<ResponseMessage> exceptionHandler(NoHandlerFoundException ex) {
		ResponseMessage rm = new ResponseMessage(HttpStatus.BAD_REQUEST.value(), HttpStatus.BAD_REQUEST.name(), "Invalid Web API URL");
		return new ResponseEntity<ResponseMessage>(rm, HttpStatus.BAD_REQUEST);
	}
	
}
