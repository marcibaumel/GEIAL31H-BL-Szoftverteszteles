import Exceptions.AutoNemTalalhato;
import Exceptions.RendszamNemMegfelelo;

import java.util.Collection;

public interface AutoDao {
    Collection<Auto> readAllAuto();
    Auto readAutoById(String rendszam) throws AutoNemTalalhato;
    void createAuto(Auto auto) throws RendszamNemMegfelelo;
    void updateAuto(Auto auto);
    void deleteAuto(Auto auto);
    void deleteAutoById(String rendszam);
}
