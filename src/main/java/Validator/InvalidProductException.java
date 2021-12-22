package main.java.Validator;

public class InvalidProductException extends Exception{
    public InvalidProductException(){
        super("Invalid product");
    }
}
