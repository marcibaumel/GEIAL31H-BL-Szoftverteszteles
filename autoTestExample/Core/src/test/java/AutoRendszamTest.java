import Exceptions.RendszamNemMegfelelo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RunWith(Parameterized.class)
public class AutoRendszamTest {

    //TODO to finish
    @Parameterized.Parameters
    public static Collection data(){
        List data = new ArrayList<>();
        data.add(new Object[] {"AAAA-123"});
        data.add(new Object[] {"ABC-1234"});
        data.add(new Object[] {"ABQ-1234"});
        data.add(new Object[] {"aaa-1234"});
        data.add(new Object[] {"ABC-ABC"});
        data.add(new Object[] {"123-123"});
        return data;
    }

    String rendszam;

    public AutoRendszamTest(String rendszam){
        this.rendszam = rendszam;
    }

    @Test(expected = RendszamNemMegfelelo.class)
    public void testRendszam() throws RendszamNemMegfelelo {
        Auto auto = new Auto();
        System.out.println("A vizsgált rendszám:" + rendszam);
        auto.setRendszam(rendszam);
    }
}