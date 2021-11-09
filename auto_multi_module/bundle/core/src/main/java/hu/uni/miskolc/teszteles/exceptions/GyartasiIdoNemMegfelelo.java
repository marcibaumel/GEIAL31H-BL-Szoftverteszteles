package hu.uni.miskolc.teszteles.exceptions;

import java.time.LocalDate;

public class GyartasiIdoNemMegfelelo extends Exception {

	public GyartasiIdoNemMegfelelo(LocalDate gyartasiIdo) {
		super(gyartasiIdo.toString());
	}

}
