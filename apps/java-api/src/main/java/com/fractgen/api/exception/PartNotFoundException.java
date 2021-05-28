package com.fractgen.api.exception;

public class PartNotFoundException extends Exception{
  public PartNotFoundException() {
    super();
  }

  public PartNotFoundException(String message) {
    super(message);
  }
}
