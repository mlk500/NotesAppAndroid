package com.example.notesapp.infrastructure;

public class HttpErrorException extends Exception{
    private int errorCode;
    public HttpErrorException(int errorCode) {
            this.errorCode = errorCode;
            this.errorCode = errorCode;
        }
}
