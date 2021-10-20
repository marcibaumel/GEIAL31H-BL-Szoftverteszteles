package Exceptions;

public class AjtokSzamaNemMegfelelo extends Exception {
    public AjtokSzamaNemMegfelelo(int ajtokSzama) {
        super(String.valueOf(ajtokSzama));
    }
}
