package hu.uni.miskolc.teszteles.core;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import hu.uni.miskolc.teszteles.core.enums.Kivitel;
import hu.uni.miskolc.teszteles.core.enums.Uzemanyag;
import hu.uni.miskolc.teszteles.core.enums.Valto;
import hu.uni.miskolc.teszteles.core.exceptions.AjtokSzamaNemMegfelelo;
import hu.uni.miskolc.teszteles.core.exceptions.GyartasiIdoNemMegfelelo;
import hu.uni.miskolc.teszteles.core.exceptions.RendszamNemMegfelelo;

public class Auto implements HanggalRendelkezo {
	public static Map<String, Integer> hengerurtartalomErtekek;

	static {
		hengerurtartalomErtekek = new HashMap<>();
		hengerurtartalomErtekek.put("1.0", 998);
		hengerurtartalomErtekek.put("1.2", 1199);
		hengerurtartalomErtekek.put("1.4", 1390);
		hengerurtartalomErtekek.put("1.6", 1560);
	}

	protected String gyarto;
	protected String modell;
	protected Integer hengerurtartalom;
	protected String rendszam;
	protected Uzemanyag uzemanyag;
	protected LocalDate gyartasiIdo;
	protected String szinHex;
	protected boolean korozott;
	protected String forgalmiSzama;
	protected Valto valto;
	protected Kivitel kivitel;
	protected int ajtokSzama;

	@Override
	public void dudal() {
		System.out.println("Tütü");
	}

	public String getGyarto() {
		return gyarto;
	}

	protected void setGyarto(String gyarto) {
		this.gyarto = gyarto;
	}

	public String getModell() {
		return modell;
	}

	protected void setModell(String modell) {
		this.modell = modell;
	}

	public Integer getHengerurtartalom() {
		return hengerurtartalom;
	}

	public void setHengerurtartalom(String hengerurtartalom) {
		this.hengerurtartalom = hengerurtartalomErtekek.get(hengerurtartalom);
	}

	public String getRendszam() {
		return rendszam;
	}

	public void setRendszam(String rendszam) throws RendszamNemMegfelelo {
		String regex="^([^a-z0-9Q]{3}-(?!000)\\d{3})$";
		if(!rendszam.matches(regex)) {
			throw new RendszamNemMegfelelo(rendszam);
		}
		this.rendszam = rendszam;
	}

	public Uzemanyag getUzemanyag() {
		return uzemanyag;
	}

	public void setUzemanyag(Uzemanyag uzemanyag) {
		this.uzemanyag = uzemanyag;
	}

	public LocalDate getGyartasiIdo() {
		return gyartasiIdo;
	}

	protected void setGyartasiIdo(LocalDate gyartasiIdo) throws GyartasiIdoNemMegfelelo {
		if (gyartasiIdo.isAfter(LocalDate.now()) || gyartasiIdo.isBefore(LocalDate.of(1885, 1, 1))) {
			throw new GyartasiIdoNemMegfelelo(gyartasiIdo);
		}
		this.gyartasiIdo = gyartasiIdo;
	}

	public String getSzinHex() {
		return szinHex;
	}

	public void setSzinHex(String szinHex) {
		this.szinHex = szinHex;
	}

	public boolean isKorozott() {
		return korozott;
	}

	public void setKorozott(boolean korozott) {
		this.korozott = korozott;
	}

	public String getForgalmiSzama() {
		return forgalmiSzama;
	}

	public void setForgalmiSzama(String forgalmiSzama) {
		this.forgalmiSzama = forgalmiSzama;
	}

	public Valto getValto() {
		return valto;
	}

	public void setValto(Valto valto) {
		this.valto = valto;
	}

	public Kivitel getKivitel() {
		return kivitel;
	}

	protected void setKivitel(Kivitel kivitel) {
		this.kivitel = kivitel;
	}

	public int getAjtokSzama() {
		return ajtokSzama;
	}

	protected void setAjtokSzama(int ajtokSzama) throws AjtokSzamaNemMegfelelo {
		if (ajtokSzama < 0 || ajtokSzama > 5) {
			throw new AjtokSzamaNemMegfelelo(ajtokSzama);
		}
		this.ajtokSzama = ajtokSzama;
	}

	public Auto(String gyarto, String modell, String hengerurtartalom, String rendszam, Uzemanyag uzemanyag,
			LocalDate gyartasiIdo, String szinHex, boolean korozott, String forgalmiSzama, Valto valto, Kivitel kivitel,
			int ajtokSzama) {
		super();
		this.gyarto = gyarto;
		this.modell = modell;
		setHengerurtartalom(hengerurtartalom);
		this.rendszam = rendszam;
		this.uzemanyag = uzemanyag;
		this.gyartasiIdo = gyartasiIdo;
		this.szinHex = szinHex;
		this.korozott = korozott;
		this.forgalmiSzama = forgalmiSzama;
		this.valto = valto;
		this.kivitel = kivitel;
		this.ajtokSzama = ajtokSzama;
	}

	public Auto() {
		// TODO Auto-generated constructor stub
	}

	@Override
	public int hashCode() {
		return Objects.hash(ajtokSzama, forgalmiSzama, gyartasiIdo, gyarto, hengerurtartalom, kivitel, korozott, modell,
				rendszam, szinHex, uzemanyag, valto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Auto other = (Auto) obj;
		return ajtokSzama == other.ajtokSzama && Objects.equals(forgalmiSzama, other.forgalmiSzama)
				&& Objects.equals(gyartasiIdo, other.gyartasiIdo) && Objects.equals(gyarto, other.gyarto)
				&& Objects.equals(hengerurtartalom, other.hengerurtartalom) && kivitel == other.kivitel
				&& korozott == other.korozott && Objects.equals(modell, other.modell)
				&& Objects.equals(rendszam, other.rendszam) && Objects.equals(szinHex, other.szinHex)
				&& uzemanyag == other.uzemanyag && valto == other.valto;
	}
	
	

}