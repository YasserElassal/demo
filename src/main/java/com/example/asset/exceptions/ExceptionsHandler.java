package com.example.asset.exceptions;

import com.example.asset.model.ErrorResponseModel;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.yaml.snakeyaml.constructor.DuplicateKeyException;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionsHandler {
    private final static Logger myLog = LogManager.getLogger(ExceptionsHandler.class);

    @ExceptionHandler
    public ResponseEntity<ErrorResponseModel> handlingNotFoundException(UserNotFoundException ex) {

        ErrorResponseModel error = new ErrorResponseModel();

        error.setMessage("user with id ["+ex.getMessage()+" ] not found ");
        error.setStatus(HttpStatus.NOT_FOUND.value());
        error.setTimestamp(System.currentTimeMillis());
        myLog.error("user with id ["+ex.getMessage()+" ] not found ");
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);


    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseModel> handlingGeneralException(Exception ex) {

        ErrorResponseModel error = new ErrorResponseModel();

        error.setMessage(ex.getMessage());
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(System.currentTimeMillis());

        myLog.error("[general exception error]  " + ex);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);


    }
    @ExceptionHandler
    public ResponseEntity<ErrorResponseModel> handlingSqlException(SQLException ex) {

        ErrorResponseModel error = new ErrorResponseModel();

        error.setMessage("sql error ");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(System.currentTimeMillis());

        myLog.error("[general exception error]  " + ex);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);


    }

    @ExceptionHandler
    public ResponseEntity<ErrorResponseModel> handlingGeneralException(DuplicateKeyException ex) {

        ErrorResponseModel error = new ErrorResponseModel();

        error.setMessage("duplicated primary key ");
        error.setStatus(HttpStatus.BAD_REQUEST.value());
        error.setTimestamp(System.currentTimeMillis());

        myLog.error("[duplicated primary key ! ]  " + ex);

        return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);


    }

}
