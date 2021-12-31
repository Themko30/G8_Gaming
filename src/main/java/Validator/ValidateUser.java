package main.java.Validator;

import main.java.Storage.Entity.Utente;

public interface ValidateUser {

  public void validateUtente(Utente utente) throws InvalidUserException;
}
