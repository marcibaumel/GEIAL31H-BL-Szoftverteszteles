import Exceptions.AjtokSzamaNemMegfelelo;
import Exceptions.GyartasIdoNemMegfelelo;
import Exceptions.RendszamNemMegfelelo;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;

public class AutoTest {

    Auto auto;

    @Before
    public void initAuto(){
        auto = new Auto();
    }


    @Test
    public void testAjtokSzamaMegfelelo() throws AjtokSzamaNemMegfelelo {
        auto.setAjtokSzama(3);
    }

    @Test(expected = AjtokSzamaNemMegfelelo.class)
    public void testAjtokSzamaAlacsony() throws AjtokSzamaNemMegfelelo {
        auto.setAjtokSzama(-1);
    }

    @Test(expected = AjtokSzamaNemMegfelelo.class)
    public void testAjtokSzamaMaga() throws AjtokSzamaNemMegfelelo {
        auto.setAjtokSzama(30);
    }

    @Test
    public void testJoRendszam() throws RendszamNemMegfelelo {
        String rendszam = "ABC-123";
        auto.setRendszam(rendszam);
    }

    @Test(expected = RendszamNemMegfelelo.class)
    public void testNemMegfeleloRendszam() throws RendszamNemMegfelelo {
        String rendszam = "ABC123";
        auto.setRendszam(rendszam);
    }

    @Test(expected = RendszamNemMegfelelo.class)
    public void testTulHosszuSzamRendszam() throws RendszamNemMegfelelo {
        String rendszam = "ABC-12313131414";
        auto.setRendszam(rendszam);
    }

    @Test(expected = RendszamNemMegfelelo.class)
    public void testTulHosszuBetuRendszam() throws RendszamNemMegfelelo {
        String rendszam = "ABCDEF-123";
        auto.setRendszam(rendszam);
    }

    @Test(expected = RendszamNemMegfelelo.class)
    public void testCsakSzamRendszam() throws RendszamNemMegfelelo {
        String rendszam = "123-123";
        auto.setRendszam(rendszam);
    }

    @Test(expected = RendszamNemMegfelelo.class)
    public void testCsakBetuRendszam() throws RendszamNemMegfelelo {
        String rendszam = "ABC-ABC";
        auto.setRendszam(rendszam);
    }

    @Test(expected = RendszamNemMegfelelo.class)
    public void testKisbetusRendszam() throws RendszamNemMegfelelo {
        String rendszam = "abc-123";
        auto.setRendszam(rendszam);
    }

    @Test(expected = RendszamNemMegfelelo.class)
    public void testQBetusRendszam() throws RendszamNemMegfelelo {
        String rendszam = "ABQ-123";
        auto.setRendszam(rendszam);
    }

    @Test
    public void testGyartasiIdoJo() throws GyartasIdoNemMegfelelo {
        String gyartasIdo = "2021-07-12";
        auto.setGyartasiIdo(LocalDate.parse(gyartasIdo));
    }

    @Test(expected = GyartasIdoNemMegfelelo.class)
    public void testGyartasiIdoKorai() throws GyartasIdoNemMegfelelo {
        String gyartasIdo = "0202-07-12";
        auto.setGyartasiIdo(LocalDate.parse(gyartasIdo));
    }

    @Test (expected = GyartasIdoNemMegfelelo.class)
    public void testGyartasiIdoKésői() throws GyartasIdoNemMegfelelo {
        String gyartasIdo = "2031-07-12";
        auto.setGyartasiIdo(LocalDate.parse(gyartasIdo));
    }

    @Test
    public void testSzinkodSetter(){
        String szinkod = "#FFFFFF";
        auto.setSzinHex(szinkod);
        assertEquals(szinkod, auto.getSzinHex());
    }

    @Test
    public void testSzinkodGetter(){
        auto.szinHex = "#FFFFFF";
        assertEquals(auto.szinHex, auto.getSzinHex());
    }
}