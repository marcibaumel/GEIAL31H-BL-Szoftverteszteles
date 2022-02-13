package hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.exceptions;

public class EmailIsUsed extends Exception{
    public EmailIsUsed(String email){
        super("This email address is used: "+email);
    }
}