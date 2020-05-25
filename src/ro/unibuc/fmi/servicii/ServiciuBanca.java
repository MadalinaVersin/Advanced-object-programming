package ro.unibuc.fmi.servicii;
import ro.unibuc.fmi.entitati.Banca;
import ro.unibuc.fmi.persistenta.PersistentaBanci;

public class ServiciuBanca {
    private static ServiciuBanca instance;

    private final PersistentaBanci persistentaBanci = PersistentaBanci.getInstance();

    private ServiciuBanca() {
    }

    public static ServiciuBanca getInstance() {
        if (instance == null) {
            instance = new ServiciuBanca();
        }

        return instance;
    }

    public Banca saveBanca(String codBanca, String denumireBanca) {
        Banca banca = new Banca();
        banca.setCodBanca(codBanca);
        banca.setDenumireBanca(denumireBanca);

        return persistentaBanci.saveBanca(banca);
    }

    public Banca findBanca(String codBanca) {
        return persistentaBanci.findBanca(codBanca);
    }

    public Banca updateBanca(Banca banca) {
        return persistentaBanci.updateBanca(banca);
    }

    public boolean deleteBanca(Banca banca) {
        return persistentaBanci.deleteBanca(banca.getCodBanca());
    }


}
