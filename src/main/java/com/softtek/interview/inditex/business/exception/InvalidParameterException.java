package com.softtek.interview.inditex.business.exception;

public class InvalidParameterException extends Exception{

  private static final long serialVersionUID = 1L;
  private String message;

  public InvalidParameterException() {

  }

  public InvalidParameterException(String message) {
    this.message = message;
  }

  public String getMessage() {
    return message;
  }
}