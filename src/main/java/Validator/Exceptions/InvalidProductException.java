package main.java.Validator.Exceptions;

public class InvalidProductException extends Exception {

    /**
     * Custom Excpetion per il prododtto non valido.
     */
    public InvalidProductException() {
        super("Invalid product");
    }
}
