package hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions;

public class NotValidEmailFormat extends Exception{
    public NotValidEmailFormat(String email){
        super(email.toString());
    }
}