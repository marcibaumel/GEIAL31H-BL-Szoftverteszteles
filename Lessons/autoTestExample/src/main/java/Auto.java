import Exceptions.AjtokSzamaNemMegfelelo;
import Exceptions.GyartasIdoNemMegfelelo;
import Exceptions.RendszamNemMegfelelo;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Auto implements HanggalRendelkezo {
    public static Map<String, Integer> hengerurtartalomErtekek;

    public Auto() {
    }

    static {
        hengerurtartalomErtekek = new HashMap<>();
        hengerurtartalomErtekek.put("1.0", 998);
        hengerurtartalomErtekek.put("1.2", 1199);
        hengerurtartalomErtekek.put("1.4", 1390);
        hengerurtartalomErtekek.put("1.6", 1560);
    }

    protected String gyarto;
    protected String modell;
    private Integer hengerurtartalom;
    private String rendszam;
    private Uzemanyag uzemanyag;
    private LocalDate gyartasiIdo;
    protected String szinHex;
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

    public void setHengerurtartalom(String hengerurtartalom){
		this.hengerurtartalom = hengerurtartalomErtekek.get(hengerurtartalom);
    }

    public String getRendszam() {
        return rendszam;
    }

    public void setRendszam(String rendszam) throws RendszamNemMegfelelo {
        String regex = "^([^a-z0-9Q]{3}-(?!000)[0-9]{3})$";
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

    protected void setGyartasiIdo(LocalDate gyartasiIdo) throws GyartasIdoNemMegfelelo {
        if (gyartasiIdo.isAfter(LocalDate.now()) || gyartasiIdo.isBefore(LocalDate.of(1885, 1, 1))) {
            throw new GyartasIdoNemMegfelelo(gyartasiIdo);
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
        if(ajtokSzama < 0 || ajtokSzama > 5){
            throw new AjtokSzamaNemMegfelelo(ajtokSzama);
        }
        else {
            this.ajtokSzama = ajtokSzama;
        }
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Auto auto = (Auto) o;
        return korozott == auto.korozott && ajtokSzama == auto.ajtokSzama && Objects.equals(gyarto, auto.gyarto) && Objects.equals(modell, auto.modell) && Objects.equals(hengerurtartalom, auto.hengerurtartalom) && Objects.equals(rendszam, auto.rendszam) && uzemanyag == auto.uzemanyag && Objects.equals(gyartasiIdo, auto.gyartasiIdo) && Objects.equals(szinHex, auto.szinHex) && Objects.equals(forgalmiSzama, auto.forgalmiSzama) && valto == auto.valto && kivitel == auto.kivitel;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gyarto, modell, hengerurtartalom, rendszam, uzemanyag, gyartasiIdo, szinHex, korozott, forgalmiSzama, valto, kivitel, ajtokSzama);
    }
}