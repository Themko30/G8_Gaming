package main.java.Validator.Exceptions;


public class InvalidUserException extends Exception {

  /**
   * Custom Exception per l'utente non validato correttamente.
   */
  public InvalidUserException() {
    super("Invalid user");
  }
}
