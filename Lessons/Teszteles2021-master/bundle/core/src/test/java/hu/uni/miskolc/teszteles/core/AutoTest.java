package hu.uni.miskolc.teszteles.core;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import hu.uni.miskolc.teszteles.core.exceptions.AjtokSzamaNemMegfelelo;
import hu.uni.miskolc.teszteles.core.exceptions.GyartasiIdoNemMegfelelo;
import hu.uni.miskolc.teszteles.core.exceptions.RendszamNemMegfelelo;
import junit.framework.Assert;

public class AutoTest {

	Auto testAuto;
	
	@Before
	public void initAuto() {
		testAuto = new Auto();
	}

	@Test
	public void testAjtokSzamaMegfelelo() throws AjtokSzamaNemMegfelelo {
		Auto auto = new Auto();
		auto.setAjtokSzama(3);
	}
	
	@Test(expected = AjtokSzamaNemMegfelelo.class)
	public void testAjtokSzamaTulAlacsony() throws AjtokSzamaNemMegfelelo {
		Auto auto = new Auto();
		auto.setAjtokSzama(-1);
	}
	
	@Test(expected = AjtokSzamaNemMegfelelo.class)
	public void testAjtokSzamaTulMagas() throws AjtokSzamaNemMegfelelo {
		Auto auto = new Auto();
		auto.setAjtokSzama(6);
	}
	
	
	@Test
	public void testJoRendszam() throws RendszamNemMegfelelo {
		String rendszam = "ABC-123";
		testAuto.setRendszam(rendszam);
	}
	
	@Test(expected = RendszamNemMegfelelo.class)
	public void testKotojelNelkuliRendszam() throws RendszamNemMegfelelo {
		String rendszam = "ABC123";
		testAuto.setRendszam(rendszam);
	}
	
	@Test(expected = RendszamNemMegfelelo.class)
	public void testTulhosszuBetusRendszam() throws RendszamNemMegfelelo {
		String rendszam = "ABCA-123";
		testAuto.setRendszam(rendszam);
	}
	
	@Test(expected = RendszamNemMegfelelo.class)
	public void testTulhosszuSzamosRendszam() throws RendszamNemMegfelelo {
		String rendszam = "ABC-1234";
		testAuto.setRendszam(rendszam);
	}
	
	@Test(expected = RendszamNemMegfelelo.class)
	public void testCsakSzamokRendszam() throws RendszamNemMegfelelo {
		String rendszam = "111-123";
		testAuto.setRendszam(rendszam);
	}
	
	@Test(expected = RendszamNemMegfelelo.class)
	public void testCsakBetukRendszam() throws RendszamNemMegfelelo {
		String rendszam = "ABC-ABC";
		testAuto.setRendszam(rendszam);
	}
	
	
	@Test(expected = RendszamNemMegfelelo.class)
	public void testKisBetusRendszam() throws RendszamNemMegfelelo {
		String rendszam = "aaa-123";
		testAuto.setRendszam(rendszam);
	}
	
	@Test(expected = RendszamNemMegfelelo.class)
	public void testQbetuvelRendszam() throws RendszamNemMegfelelo {
		String rendszam = "AAQ-123";
		testAuto.setRendszam(rendszam);
	}
	
	
	@Test
	public void testJoGyartasiIdo() throws GyartasiIdoNemMegfelelo {
		String gyartasiIdo = "2021-04-17";
		testAuto.setGyartasiIdo(LocalDate.parse(gyartasiIdo));
	}
	
	@Test(expected = GyartasiIdoNemMegfelelo.class)
	public void testKoraiGyartasiIdo() throws GyartasiIdoNemMegfelelo {
		String gyartasiIdo = "0220-04-17";
		testAuto.setGyartasiIdo(LocalDate.parse(gyartasiIdo));
	}
	
	@Test(expected = GyartasiIdoNemMegfelelo.class)
	public void testKesoiGyartasiIdo() throws GyartasiIdoNemMegfelelo {
		String gyartasiIdo = "2022-04-17";
		testAuto.setGyartasiIdo(LocalDate.parse(gyartasiIdo));
	}
	
	@Test
	public void testSzinkodSetter() {
		String szinkod = "#FFFFFF";
		testAuto.setSzinHex(szinkod);
		assertEquals(szinkod, testAuto.szinHex);
	}
	
	@Test
	public void testSzinkodGetter() {
		testAuto.szinHex = "#FFFFFF";
		assertEquals(testAuto.szinHex, testAuto.getSzinHex());
	}
	
	
	
}
