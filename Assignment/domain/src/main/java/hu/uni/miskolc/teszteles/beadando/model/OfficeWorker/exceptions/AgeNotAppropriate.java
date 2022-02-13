package hu.uni.miskolc.teszteles.beadando.model.OfficeWorker.exceptions;

import java.time.LocalDate;

public class AgeNotAppropriate extends Exception{
    public AgeNotAppropriate(LocalDate date){
        super(date.toString());
    }
}
