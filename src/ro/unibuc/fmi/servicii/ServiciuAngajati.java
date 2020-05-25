package ro.unibuc.fmi.servicii;
import ro.unibuc.fmi.entitati.Angajat;
import ro.unibuc.fmi.persistenta.PersistentaAngajati;


import java.util.List;

public class ServiciuAngajati {
    private static ServiciuAngajati instance = null;
    private final PersistentaAngajati persistentaAngajati = PersistentaAngajati.getInstance();

    private ServiciuAngajati() {
    }

    public static ServiciuAngajati getInstance() {
        if (instance == null) {
            instance = new ServiciuAngajati();
        }
        return instance;
    }


    public Angajat saveAngajat(String cnp, String codBanca, String nume, String prenume, String codAngajat) {
        Angajat angajat = new Angajat();
        angajat.setCnp(cnp);
        angajat.setCodBanca(codBanca);
        angajat.setNume(nume);
        angajat.setPrenume(prenume);
        angajat.setCodAngajat(codAngajat);

        return persistentaAngajati.saveAngajat(angajat);
    }

    public Angajat findAngajat(String cnp) {
        return persistentaAngajati.findAngajat(cnp);
    }

    public List<Angajat> findAllAngajati(String codBanca) {
        return persistentaAngajati.findAllAngajati(codBanca);
    }

    public Angajat updateAngajat(Angajat angajat) {
        return persistentaAngajati.updateAngajat(angajat);
    }

    public boolean deleteAngajat(Angajat angajat) {
        return persistentaAngajati.deleteAngajat(angajat.getCnp());
    }


}
