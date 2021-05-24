package com.fractgen.api.exception;

public class EmailNotVerifiedException  extends Exception{
  public EmailNotVerifiedException() {
    super();
  }

  public EmailNotVerifiedException(String message) {
    super(message);
  }
}
