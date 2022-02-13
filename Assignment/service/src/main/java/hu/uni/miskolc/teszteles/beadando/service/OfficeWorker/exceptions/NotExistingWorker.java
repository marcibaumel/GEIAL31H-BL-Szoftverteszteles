package hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.exceptions;

public class NotExistingWorker extends RuntimeException{
    public NotExistingWorker(String format) {
        super(format.toString());
    }
}