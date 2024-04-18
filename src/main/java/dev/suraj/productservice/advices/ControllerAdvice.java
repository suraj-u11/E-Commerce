package dev.suraj.productservice.advices;

import dev.suraj.productservice.dtos.ErrorDto;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

@org.springframework.web.bind.annotation.ControllerAdvice
public class ControllerAdvice {

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity<ErrorDto> handleNullPointerException() {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setMessage("Some Error Occured. Please try again after sometime!!!");

        return new ResponseEntity<ErrorDto>(errorDto, HttpStatusCode.valueOf(503));
    }
}
