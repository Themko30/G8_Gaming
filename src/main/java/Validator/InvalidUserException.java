package main.java.Validator;


public class InvalidUserException extends Exception{
  public InvalidUserException(){
    super("Invalid user");
  }
}
