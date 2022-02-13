package hu.uni.miskolc.teszteles;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

public class Auto implements HanggalRendelkezo {
	public static Map<String, Integer> hengerurtartalomErtekek;

	static {
		hengerurtartalomErtekek = new HashMap<>();
		hengerurtartalomErtekek.put("1.0", 998);
		hengerurtartalomErtekek.put("1.2", 1199);
		hengerurtartalomErtekek.put("1.4", 1390);
		hengerurtartalomErtekek.put("1.6", 1560);
	}

	private String gyarto;
	private String modell;
	private Integer hengerurtartalom;
	private String rendszam;
	private Uzemanyag uzemanyag;
	private LocalDate gyartasiIdo;
	private String szinHex;
	private boolean korozott;
	private String forgalmiSzama;
	private Valto valto;
	private Kivitel kivitel;
	private int ajtokSzama;

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

	public void setHengerurtartalom(String hengerurtartalom) 
		this.hengerurtartalom = hengerurtartalomErtekek.get(hengerurtartalom);
	}

	public String getRendszam() {
		return rendszam;
	}

	public void setRendszam(String rendszam) {
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

	protected void setGyartasiIdo(LocalDate gyartasiIdo) {
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

	protected void setAjtokSzama(int ajtokSzama) {
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

}
