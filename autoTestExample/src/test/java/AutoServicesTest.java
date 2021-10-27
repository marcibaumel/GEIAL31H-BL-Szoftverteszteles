import Exceptions.AutoNemTalalhato;
import Exceptions.RendszamNemMegfelelo;
import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.AdditionalAnswers;
import org.mockito.AdditionalMatchers;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;


import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class AutoServicesTest {

    private AutoServices service;
    private Collection<Auto> autos;
    private AutoDao mock;

    @Before
    public void setUp() throws AutoNemTalalhato {
        mock = Mockito.mock(AutoDao.class);
        service = new AutoServices(mock);
        Auto auto1 = new Auto("Opel","Astra","1.2", "ABC-123", Uzemanyag.BENZIN, LocalDate.of(2017, 5,12), "#FFFFFF", false, "123456EE", Valto.MANUALIS_5_FOKOZAT, Kivitel.KOMBI, 5);
        Auto auto2 = new Auto("Kia","Oicanto","1.0", "ABC-222", Uzemanyag.BENZIN, LocalDate.of(2012, 8,30), "#FFFFFF", true, "123456EE", Valto.MANUALIS_5_FOKOZAT, Kivitel.HATCHBACK, 4);
        Auto auto3 = new Auto("Rönó","Tália","1.2", "ABC-333", Uzemanyag.DIESEL, LocalDate.of(2009, 5,12), "#00000", false, "123456EE", Valto.AUTOMATA, Kivitel.SEDAN, 5);
        autos = new ArrayList<>();
        autos.add(auto1);
        autos.add(auto2);
        autos.add(auto3);
        Mockito.when(mock.readAutoById(org.mockito.Mockito.anyString())).thenThrow(new AutoNemTalalhato()).thenThrow(new AutoNemTalalhato());
        Mockito.doReturn(auto1).when(mock).readAutoById("ABC-123");
        Mockito.doReturn(auto2).when(mock).readAutoById("ABC-222");
        Mockito.doReturn(auto3).when(mock).readAutoById("ABC-333");
        Mockito.when(mock.readAllAuto()).thenReturn(autos);
        //Mockito.doThrow(RendszamNemMegfelelo.class).when(mock).readAutoById(AdditionalMatchers.not(Mockito.matches("\\w\w\w-\\d\d\d")));
        //Mockito.doThrow(RendszamNemMegfelelo.class)

    }


    @Test
    public void getAllAutoTestWithCorrectData(){
        int actualSize = 3;
        assertEquals(service.getAllAuto().size(), actualSize);
        for(Auto a: autos){
            MatcherAssert.assertThat(autos, Matchers.hasItem(a));
        }
    }

    @Test(expected = RendszamNemMegfelelo.class)
    public void TestRosszRendszamLekerdezesnel() throws AutoNemTalalhato, RendszamNemMegfelelo {
        service.getAutoById("kiscica");

    }

    @Test
    public void testNemJoAuto() throws RendszamNemMegfelelo {
        Auto auto1 = new Auto("Opel","Astra","1.2", "ABC-123", Uzemanyag.BENZIN, LocalDate.of(600, 5,12), "#FFFFFF", false, "123456EE", Valto.MANUALIS_5_FOKOZAT, Kivitel.KOMBI, 5);
        service.addAuto(auto1);
    }

    @Test
    public void testDuplum() throws RendszamNemMegfelelo{
        Auto auto1 = new Auto("Opel","Astra","1.2", "ABC-123", Uzemanyag.BENZIN, LocalDate.of(2017, 5,12), "#FFFFFF", false, "123456EE", Valto.MANUALIS_5_FOKOZAT, Kivitel.KOMBI, 5);
        service.addAuto(auto1);
    }

    @Test
    public void existingKorozottAutoTest(){
        assertNotEquals(service.getAllAuto().size(), 0);
    }

    @Test
    public void testAutoMasolat(){
        Auto autoCopy = new Auto("Opel","Astra","1.2", "ABC-123", Uzemanyag.BENZIN, LocalDate.of(2017, 5,12), "#FFFFFF", false, "123456EE", Valto.MANUALIS_5_FOKOZAT, Kivitel.KOMBI, 5);
        MatcherAssert.assertThat(autos, Matchers.hasItem(autoCopy));
    }

    @Test
    public void testAutoByRendszam() throws AutoNemTalalhato{

    }


    @AfterClass
    public void verify(){
        Mockito.verify(mock).createAuto(org.mockito.Matchers.);
    }

}