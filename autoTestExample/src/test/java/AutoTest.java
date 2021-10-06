import Exceptions.AjtokSzamaNemMegfelelo;
import org.junit.Test;

import static org.junit.Assert.*;

public class AutoTest {

    @Test
    public void sampleTest(){
        boolean test = true;
        assertTrue(test);
    }

    @Test
    public void testAjtokSzamaMegfelelo() throws AjtokSzamaNemMegfelelo {
        Auto auto = new Auto();
        auto.setAjtokSzama(3);
    }

    @Test(expected = AjtokSzamaNemMegfelelo.class)
    public void testAjtokSzamaAlacsony() throws AjtokSzamaNemMegfelelo {
        Auto auto = new Auto();
        auto.setAjtokSzama(-1);
    }

    @Test(expected = AjtokSzamaNemMegfelelo.class)
    public void testAjtokSzamaMaga() throws AjtokSzamaNemMegfelelo {
        Auto auto = new Auto();
        auto.setAjtokSzama(30);
    }
}