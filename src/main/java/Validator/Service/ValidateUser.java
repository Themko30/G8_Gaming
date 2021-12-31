package main.java.Validator.Service;

import main.java.Storage.Entity.Utente;
import main.java.Validator.Exceptions.InvalidUserException;

public interface ValidateUser {

  public void validateUtente(Utente utente) throws InvalidUserException;
}
