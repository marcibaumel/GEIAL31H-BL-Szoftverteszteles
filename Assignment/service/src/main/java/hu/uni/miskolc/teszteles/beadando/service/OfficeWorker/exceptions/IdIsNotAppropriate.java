package hu.uni.miskolc.teszteles.beadando.service.OfficeWorker.exceptions;

public class IdIsNotAppropriate extends Exception{
    public IdIsNotAppropriate(int format) {
        super(Integer.toString(format));
    }
}
