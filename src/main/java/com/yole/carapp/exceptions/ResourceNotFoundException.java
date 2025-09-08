package com.yole.carapp.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String resource, int id) {
        super(resource + " with ID " + id + " not found.");
    }
}