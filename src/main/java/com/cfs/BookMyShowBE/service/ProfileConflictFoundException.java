package com.cfs.BookMyShowBE.service;

public class ProfileConflictFoundException extends RuntimeException {
    public ProfileConflictFoundException(String msg){
        super(msg);
    }
}
