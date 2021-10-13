package Exceptions;

import java.time.LocalDate;

public class GyartasIdoNemMegfelelo extends Exception{
    public GyartasIdoNemMegfelelo(LocalDate gyartasido){
        super(gyartasido.toString());
    }
}
