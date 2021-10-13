package teglalap;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class TeglalapTest {

    private Teglalap teglalap;
    @Before
    public void setUp() throws Exception {
        teglalap = new Teglalap();
    }

    @Test
    public void szamitTeglalap() {
        assertEquals(700, teglalap.szamitTeglalap(20, 35));
        assertEquals(1800, teglalap.szamitTeglalap(40, 45));
    }

    @Test(expected = IllegalArgumentException.class)
    public void szamitTeglalapRosszTest(){
        teglalap.szamitTeglalap(10,0);
    }
}