package hu.uni.miskolc.teszteles.core.exceptions;

public class AjtokSzamaNemMegfelelo extends Exception {

	public AjtokSzamaNemMegfelelo(int ajtokSzama) {
		super(String.valueOf(ajtokSzama));
	}

}
