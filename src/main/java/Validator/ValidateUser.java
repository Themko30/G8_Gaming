package main.java.Validator;

import main.java.Autenticazione.Utente;

public interface ValidateUser {
  public void validateUtente(Utente utente) throws InvalidUserException;
}
