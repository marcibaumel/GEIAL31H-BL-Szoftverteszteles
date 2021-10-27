import Exceptions.AutoNemTalalhato;
import Exceptions.RendszamNemMegfelelo;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;

import java.time.LocalDate;
import java.util.Collection;
import java.util.stream.Collectors;

public class AutoServices {

    private AutoDao dao;


    public AutoServices(AutoDao dao) {
        this.dao = dao;
    }

    public Auto getAutoById(String rendszam) throws AutoNemTalalhato, RendszamNemMegfelelo {
        return dao.readAutoById(rendszam);
    }

    public Collection<Auto> getAllAuto(){
       return dao.readAllAuto();
    }

    public Collection<Auto> getAllKorozottAuto(){
        Collection<Auto> autok = getAllAuto();
        Collection<Auto> korozott = autok.stream().filter(a -> a.isKorozott()).collect(Collectors.toList());
        return korozott;
    }

    public Collection<Auto> getAllAutoDatumKozott(LocalDate tol, LocalDate ig){
        Collection<Auto> autok = getAllAuto();
        Predicate<Auto> pred = a -> a.getGyartasiIdo().isAfter(tol) && a.getGyartasiIdo().isBefore(ig);
        CollectionUtils.filter(autok, pred);
        return autok;
    }

    public void addAuto(Auto auto) throws RendszamNemMegfelelo {
        dao.createAuto(auto);
    }

    public void deleteAuto(Auto auto){
        dao.deleteAuto(auto);
    }
}
