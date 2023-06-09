package com.project.mylist.exception;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND, reason = "Resource not found")
public class ResourceNotFoundException extends Exception {
   private static final long serialVersionUID = 1L;
   
   public ResourceNotFoundException(String msg) {
	    super(msg);
	  }
}
