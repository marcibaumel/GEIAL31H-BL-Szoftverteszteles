package hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions;

public class TooLowSalary extends Exception{
    public TooLowSalary(double salary){
        super(String.valueOf(salary));
    }
}
