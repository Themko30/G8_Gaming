package main.java.Validator;

import java.util.regex.Pattern;

public class ValidatorIndirizzo {

    public void checkIndirizzo(String indirizzo, int cap, String paese) throws InvalidIndirizzoException {


        Pattern pattern = Pattern.compile("[A-Za-z]+[ ][A-Za-z]+[,][ ]?[0-9A-Za-z]+");
        if(!(pattern.matcher(indirizzo).matches())){
            throw new InvalidIndirizzoException();
        }

        String cap1 = ""+cap;
        String init = "00010";
        String end = "97100";
        if(cap1.compareTo(init) < 0 || cap1.compareTo(end) > 0 || cap1.length() !=5)
            throw new InvalidIndirizzoException();

        pattern = Pattern.compile("[A-Za-z ]+");
        if(!(pattern.matcher(paese).matches())){
            throw new InvalidIndirizzoException();
        }
    }
}
